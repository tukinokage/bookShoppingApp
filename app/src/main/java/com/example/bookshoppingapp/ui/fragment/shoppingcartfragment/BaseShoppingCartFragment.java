package com.example.bookshoppingapp.ui.fragment.shoppingcartfragment;

import com.example.bookshoppingapp.model.listViewEntity.CartItem;

import java.util.List;

/*
*@
* **/
public interface BaseShoppingCartFragment {
    /** *****此处写ui方法*
     ** 在对应fragment实现****** **/
    void showToast(String dialogText);
    void showWisdomHUDSucceed(String dialogText);
    void showWisdomHUDError(String dialogText);

    void updateShoppingCartListView(List<CartItem> cartItemList);
    void updateShoppingAllCost(float cost);

}
