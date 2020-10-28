package com.example.bookshoppingapp.presenter;

import androidx.fragment.app.Fragment;

import com.example.bookshoppingapp.dao.BookInfoDao;
import com.example.bookshoppingapp.dao.BoughtItemDao;
import com.example.bookshoppingapp.dao.ShoppingCartItemDao;
import com.example.bookshoppingapp.model.entity.BookItemInfo;
import com.example.bookshoppingapp.model.listViewEntity.CartItem;
import com.example.bookshoppingapp.model.entity.ShoppingCartItem;
import com.example.bookshoppingapp.model.entity.UserBoughtItem;
import com.example.bookshoppingapp.ui.fragment.shoppingcartfragment.ShoppingCartFragment;
import com.example.bookshoppingapp.util.ArithUtil;
import com.example.bookshoppingapp.util.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartFragmentPresenterImpl implements ShoppingCartFragmentPresenter {

    /**每个presenter必写部分,
     * 获取对应activity or fragment
     * 用于执行更新activity or fragment的ui方法***/

    ShoppingCartFragment fragment;

    public ShoppingCartFragmentPresenterImpl(Fragment fragment){
        this.fragment = (ShoppingCartFragment) fragment;
    }

    /********************************/

    @Override
    public void doSomething() {

    }

    @Override
    public void addItemNum(List<CartItem> itemList, int position) {
        int num = itemList.get(position).getNum() + 1;

        float cost = itemList.get(position).getCost();
        float singleCost = itemList.get(position).getSinglePrice();
        float newcost =  ArithUtil.add(cost, singleCost);

        updateShoppingCartDBandListView(itemList, position, num, newcost);

    }

    @Override
    public void subItemNum(List<CartItem> itemList, int position) {

        int num = itemList.get(position).getNum() - 1;

        float cost = itemList.get(position).getCost();
        float singleCost = itemList.get(position).getSinglePrice();
        float newcost =  ArithUtil.sub(cost, singleCost);

        updateShoppingCartDBandListView(itemList, position, num, newcost);

    }

    @Override
    public void updateItemCost(List<CartItem> itemList, int position) {

    }

    @Override
    public void deleteItem(List<CartItem> itemList) {

        ShoppingCartItemDao shoppingCartItemDao = new ShoppingCartItemDao();

       ArrayList<CartItem> checkedItems = new ArrayList<CartItem>();
        for(int i = 0; i < itemList.size(); i++){
            if(itemList.get(i).isChecked()){
                checkedItems.add(itemList.get(i));
                int i1 = shoppingCartItemDao.deleteCartByCartId(itemList.get(i).getItemId(), fragment.getActivity());
                if(i1 == 0){
                    fragment.showToast(itemList.get(i).getBookName() + "删除失败");
                    continue;
                }
                itemList.remove(i);
                i--;
            }
        }

        if(checkedItems.isEmpty()){
            fragment.showToast("没有选中购物车！");
        }else {
            fragment.updateShoppingCartListView(itemList);
        }

    }

    //结算
    @Override
    public void sumbitResult(List<CartItem> itemList) {
        BoughtItemDao boughtItemDao = new BoughtItemDao();
        ShoppingCartItemDao shoppingCartItemDao = new ShoppingCartItemDao();
        BookInfoDao bookInfoDao = new BookInfoDao();

        //存储当前选中的
        ArrayList<CartItem> checkedItems = new ArrayList<CartItem>();

        int id = boughtItemDao.getuserBoughtItemNum( fragment.getActivity());
        int faultNum = 0;
        for(int i = 0; i < itemList.size(); i++){
            if(itemList.get(i).isChecked()){
                checkedItems.add(itemList.get(i));

                UserBoughtItem boughtItem = new UserBoughtItem();
                boughtItem.setCost(itemList.get(i).getCost());
                boughtItem.setNum(itemList.get(i).getNum());
                boughtItem.setBookId(itemList.get(i).getBookId());
                boughtItem.setItemId(String.valueOf(++id));
                boughtItem.setUserId(UserInfoUtil.getUserId(fragment.getActivity()));

                boolean result = boughtItemDao.insertUserBoughtItem(boughtItem, fragment.getActivity());
                if(!result){
                    fragment.showWisdomHUDError(itemList.get(i).getBookName() + "购买失败");
                    faultNum++;
                    continue;
                }
                //删除购物车记录
                shoppingCartItemDao.deleteCartByCartId(itemList.get(i).getItemId(), fragment.getActivity());
                //增加销量
                BookItemInfo bookItemInfo = bookInfoDao.getBookInfo(itemList.get(i).getBookId(), fragment.getActivity());
                bookInfoDao.updateBookSalesNum(bookItemInfo.getBookId(),
                        bookItemInfo.getSalesVolume() + itemList.get(i).getNum(),
                        fragment.getActivity());

                itemList.remove(i);
                i--;
            }
        }

        if(checkedItems.isEmpty()){
            fragment.showToast("没有购买任何书本！");
        }else {
            if(faultNum == 0){
                fragment.showWisdomHUDSucceed("购买成功");
                fragment.updateShoppingCartListView(itemList);
            }
        }

    }


    //更新多选的费用
    @Override
    public void sumbitResult(List<CartItem> itemList, int position) {
        fragment.updateShoppingAllCost(sumTotalPrice(itemList));
    }

    //初始化获取所有购物车项目
    @Override
    public void getAllShoppingCart(List<CartItem> itemList) {

        ShoppingCartItemDao shoppingCartItemDao = new ShoppingCartItemDao();
        BookInfoDao bookInfoDao = new BookInfoDao();

        List<ShoppingCartItem> shoppingCartItemList = shoppingCartItemDao
                .getshoppingCartItem(UserInfoUtil.getUserId(fragment.getActivity()), fragment.getActivity());

        for (ShoppingCartItem i:
             shoppingCartItemList) {
            BookItemInfo bookItemInfo = bookInfoDao.getBookInfo(i.getBookId(), fragment.getActivity());
            CartItem cartItem = new CartItem();

            cartItem.setChecked(false);
            cartItem.setNum(i.getNum());
            cartItem.setItemId(i.getItemId());
            cartItem.setBookName(bookItemInfo.getBookName());
            cartItem.setBookId(bookItemInfo.getBookId());
            cartItem.setIconPath(bookItemInfo.getIconPath());
            cartItem.setSinglePrice(bookItemInfo.getCost());
            cartItem.setCost(ArithUtil.mul(bookItemInfo.getCost(), (float)i.getNum()));
            itemList.add(cartItem);
        }

        fragment.updateShoppingCartListView(itemList);

    }


    private float sumTotalPrice(List<CartItem> itemList){
        float result = 0;

        for (CartItem i:
             itemList) {
            if(i.isChecked()){
                result = ArithUtil.add(result, i.getCost());
            }
        }

        return result;

    }

    private void updateShoppingCartDBandListView(List<CartItem> itemList, int position, int newNum, float newCost){
        try {
            //listview列表恢复用
            CartItem backupCartItem = itemList.get(position).clone();

            itemList.get(position).setNum(newNum);
            itemList.get(position).setCost(newCost);

            ShoppingCartItemDao dao = new ShoppingCartItemDao();
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.setNum(itemList.get(position).getNum());
            //shoppingCartItem.setUserId(UserInfoUtil.getUserId(fragment.getActivity()));

            shoppingCartItem.setUserId(UserInfoUtil.getUserId(fragment.getActivity()));
            shoppingCartItem.setItemId(itemList.get(position).getItemId());
            shoppingCartItem.setBookId(itemList.get(position).getBookId());

            int row = dao.updateCartItem(shoppingCartItem, fragment.getActivity());
            if (row == 0){
                fragment.showToast("访问失败");
                itemList.set(position, backupCartItem);
            }else {
                fragment.updateShoppingAllCost(sumTotalPrice(itemList));
                fragment.updateShoppingCartListView(itemList);
            }


        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            fragment.showToast("出错了！");
        }

    }






}
