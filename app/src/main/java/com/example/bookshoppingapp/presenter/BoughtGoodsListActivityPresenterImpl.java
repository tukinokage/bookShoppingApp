package com.example.bookshoppingapp.presenter;

import android.app.Activity;

import com.example.bookshoppingapp.dao.BookInfoDao;
import com.example.bookshoppingapp.dao.BoughtItemDao;
import com.example.bookshoppingapp.model.entity.BookItemInfo;
import com.example.bookshoppingapp.model.listViewEntity.BoughtHistoryListViewItem;
import com.example.bookshoppingapp.model.entity.UserBoughtItem;
import com.example.bookshoppingapp.ui.activity.BoughtGoodsListActivity;
import java.util.List;

public class BoughtGoodsListActivityPresenterImpl implements BoughtGoodsListActivityPresenter {
    /**每个presenter必写部分,
     * 获取对应activity or fragment
     * 用于执行更新activity or fragment的ui方法***/

    BoughtGoodsListActivity activity;

    public BoughtGoodsListActivityPresenterImpl( Activity activity){
        this.activity = (BoughtGoodsListActivity) activity;
    }

    @Override
    public void loadList(List<BoughtHistoryListViewItem> itemsList, String userId) {
        BoughtItemDao boughtItemDao = new BoughtItemDao();
        BookInfoDao bookInfoDao = new BookInfoDao();

        List<UserBoughtItem> userBoughtItemList = boughtItemDao.getUserBoughtItem(userId, activity);
        for(UserBoughtItem item:userBoughtItemList){
            BookItemInfo bookItemInfo = bookInfoDao.getBookInfo(item.getBookId(), activity);

            BoughtHistoryListViewItem listViewItem = new BoughtHistoryListViewItem();

            listViewItem.setCost(item.getCost());
            listViewItem.setItemId(item.getItemId());
            listViewItem.setNum(item.getNum());
            listViewItem.setBookName(bookItemInfo.getBookName());
            listViewItem.setIconPath(bookItemInfo.getIconPath());

            itemsList.add(listViewItem);
        }

        activity.updateShoppingCartListView(itemsList);

    }

    /********************************/



}
