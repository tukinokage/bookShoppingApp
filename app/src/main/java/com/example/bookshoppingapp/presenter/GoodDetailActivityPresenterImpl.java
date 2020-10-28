package com.example.bookshoppingapp.presenter;

import android.app.Activity;
import android.util.Log;

import com.example.bookshoppingapp.dao.ShoppingCartItemDao;
import com.example.bookshoppingapp.model.entity.ShoppingCartItem;
import com.example.bookshoppingapp.ui.activity.GoodDetailActivity;
import com.example.bookshoppingapp.util.UserInfoUtil;

public class GoodDetailActivityPresenterImpl implements GoodDetailActivityPresenter {
    /**每个presenter必写部分,
     * 获取对应activity or fragment
     * 用于执行更新activity or fragment的ui方法***/

    GoodDetailActivity activity;

    public GoodDetailActivityPresenterImpl( Activity activity){

        this.activity = (GoodDetailActivity) activity;
    }

    @Override
    public void addToShoppingCart(String bookId) {
        ShoppingCartItemDao shoppingCartItemDao = new ShoppingCartItemDao();
        ShoppingCartItem shoppingCartItem = shoppingCartItemDao
                .getCartItem(
                        bookId,
                        UserInfoUtil.getUserId(activity),
                        activity);

        boolean result = false;

        //新增
        if(shoppingCartItem == null){
            Log.e("DEBUG", "1");
            int itemId = shoppingCartItemDao.getshoppingCartItemNum(activity) + 1;

            shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.setBookId(bookId);
            shoppingCartItem.setItemId(String.valueOf(itemId));
            shoppingCartItem.setUserId(UserInfoUtil.getUserId(activity));
            shoppingCartItem.setNum(1);

            result = shoppingCartItemDao.insertShoppingCart(shoppingCartItem, activity);

            //已有
        }else {

            Log.e("DEBUG", "2");
            int newNum = shoppingCartItem.getNum() + 1;
            shoppingCartItem.setNum(newNum);

            int i = shoppingCartItemDao.updateCartItem(shoppingCartItem, activity);

            if(i != 0){
                result = true;
            }

        }


        if (result){
            activity.showWisdomHUDSucceed("添加成功！");
        }else {
            activity.showWisdomHUDError("添加失败!");
        }



    }

    /********************************/


}
