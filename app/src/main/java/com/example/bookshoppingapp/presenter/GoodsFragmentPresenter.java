package com.example.bookshoppingapp.presenter;

import com.example.bookshoppingapp.model.entity.BookItemInfo;
import com.example.bookshoppingapp.model.listViewEntity.BookTypeListItem;

import java.util.List;

public interface GoodsFragmentPresenter {
   /******编写逻辑操作方法*
    * 在对应实现类中实现方法
    * *****/
    void doSomething();

    //传入list地址进行类型切换显示

    /**@Param position 书本类型指针
     * @Param bookList当前显示的书本列表
     * @Param typeItemList 显示的类型列表**/
    void switchType(List<BookTypeListItem> typeItemList, int position);

    void updateBrowsingHistory(List<BookItemInfo> typeItemList, int position);

}
