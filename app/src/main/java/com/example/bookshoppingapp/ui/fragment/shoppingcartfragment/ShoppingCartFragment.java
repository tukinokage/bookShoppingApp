package com.example.bookshoppingapp.ui.fragment.shoppingcartfragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.bookshoppingapp.R;
import com.example.bookshoppingapp.adapter.GoodsCartItemAdapter;
import com.example.bookshoppingapp.model.listViewEntity.CartItem;
import com.example.bookshoppingapp.presenter.ShoppingCartFragmentPresenter;
import com.example.bookshoppingapp.presenter.ShoppingCartFragmentPresenterImpl;
import com.example.bookshoppingapp.ui.fragment.BaseFragment;
import com.example.bookshoppingapp.util.ToastTool;
import com.example.jianfeng.wisdomprogresshud.WisdomHUDStatus;
import com.example.jianfeng.wisdomprogresshud.WisdomProgressHUD;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/*************购物车页**********/
public class ShoppingCartFragment extends BaseFragment implements BaseShoppingCartFragment {


    /***********本部分可初始化控件
     * or
     * 变量
     * **************/
    @BindView(R.id.fragment_shopping_cart_goods_listView)
    ListView cartItemListView;

    @BindView(R.id.fragment_shopping_cart_money_textView)
    TextView allCostTextView;

    @BindView(R.id.fragment_shopping_cart_submit_btn)
    Button submitBtn;

    @BindView(R.id.fragment_shopping_cart_delete_btn)
    Button deleteBtn;


    static List<CartItem> cartItemsList;
    static List<Integer> positions;

    GoodsCartItemAdapter cartItemAdapter;


    static ShoppingCartFragmentPresenter shoppingCartFragmentPresenter;
     /*组件初始化示例：
    @BindView(R.id.test_btn)
    Button button;
    */
    /*************************/


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_shopingcart, container, false);
        Log.d("shoppping", "fragment 创建");
         return root;
        /*此处不要做任何初始化*/
    }

    @Override
    public void onResume() {
        super.onResume();
        cartItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();

        /*以下部分可初始化数据*/

        //list
        cartItemsList = new ArrayList<>();

        //adapter
        cartItemAdapter = new GoodsCartItemAdapter(cartItemsList, getContext());
        cartItemListView.setAdapter(cartItemAdapter);

        //presenter初始化
        shoppingCartFragmentPresenter = new ShoppingCartFragmentPresenterImpl(this);
        shoppingCartFragmentPresenter.getAllShoppingCart(cartItemsList);
    }

    @Override
    protected void initListener() {
        /*
         * 示例：button.setlistener(new View.OnClikedListenser{....})
         * or
         * button.setListenser(new 内部监听器类)
         * */

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCartFragmentPresenter.sumbitResult(cartItemsList);
            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("确定购买吗")
                        .setMessage("请确认后购买")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                shoppingCartFragmentPresenter.deleteItem(cartItemsList);
                            }
                        })
                        .setNegativeButton("否", null)
                        .show();

            }
        });


    }


    @Override
    public void showToast(String dialogText) {
        ToastTool.showToastOnMainThread(dialogText, this.getContext());
    }

    @Override
    public void showWisdomHUDSucceed(String dialogText) {
        WisdomProgressHUD.start_onApplication(WisdomHUDStatus.Succee, getContext(), dialogText);
    }

    @Override
    public void showWisdomHUDError(String dialogText) {
        WisdomProgressHUD.start_onApplication(WisdomHUDStatus.Error, getContext(), dialogText);
    }


    //刷新listview
    @Override
    public void updateShoppingCartListView(List<CartItem> cartItemList) {
            cartItemAdapter.setCartItemList(cartItemList);
            cartItemAdapter.notifyDataSetChanged();
    }

    //刷新总花费数
    @Override
    public void updateShoppingAllCost(float cost) {
        allCostTextView.setText("¥ " + cost);
    }


    /*监听器可编写为内部类
     * public class testListener implements 监听器接口{..
     * ...}
     * */

    /**********************************监听器类**************************************/
    //结算btn
    public static class subBtnListener implements View.OnClickListener{
        int position;

        public subBtnListener(int position){
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            shoppingCartFragmentPresenter.subItemNum(cartItemsList, position);
        }
    }

  //改变数量
    public static class addBtnListener implements View.OnClickListener{
        int position;

        public addBtnListener(int position){
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            shoppingCartFragmentPresenter.addItemNum(cartItemsList, position);
        }
    }

    //勾选
    public static class checkListener implements CompoundButton.OnCheckedChangeListener {
        int position;

        public checkListener(int position){
            this.position = position;
        }



        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                cartItemsList.get(position).setChecked(isChecked);
                shoppingCartFragmentPresenter.sumbitResult(cartItemsList, position);


            /*if(isChecked){
                for (int p:
                     positions) {
                    if(p == position){
                       break;
                    }

                    positions.add(position);
                    shoppingCartFragmentPresenter.sumbitResult(cartItemsList, positions);

                }

            }else {
                    for(int i = 0; i < positions.size(); i++){
                        if(positions.get(i) == position){
                            positions.remove(i);
                            shoppingCartFragmentPresenter.sumbitResult(cartItemsList, positions);
                        }

                    }
                }*/
            }
        }
/************************************************************************************************/
        //回收静态类所占空间
    @Override
    public void onDestroy() {
        super.onDestroy();

        positions = null;
        shoppingCartFragmentPresenter = null;
        cartItemsList = null;

    }
}
