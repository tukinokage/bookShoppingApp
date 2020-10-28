package com.example.bookshoppingapp.presenter;

import com.example.bookshoppingapp.model.listViewEntity.CartItem;

import java.util.List;

public interface ShoppingCartFragmentPresenter {
   /******编写逻辑操作方法*
    * 在对应实现类中实现方法
    * *****/
    void doSomething();


    /**@Param position 书本类型指针
     * @Param bookList当前显示的书本列表
     * @Param typeItemList 显示的类型列表**/
    void addItemNum(List<CartItem> itemList, int position);
    void subItemNum(List<CartItem> itemList, int position);
    void updateItemCost(List<CartItem> itemList, int position);

    void deleteItem(List<CartItem> itemList);
    void sumbitResult(List<CartItem> itemList);

   void sumbitResult(List<CartItem> itemList, int position);

   void getAllShoppingCart(List<CartItem> itemList);

}
