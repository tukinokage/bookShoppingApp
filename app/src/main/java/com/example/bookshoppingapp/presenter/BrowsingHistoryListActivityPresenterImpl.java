package com.example.bookshoppingapp.presenter;

import android.app.Activity;
import android.util.Log;

import com.example.bookshoppingapp.dao.BookInfoDao;
import com.example.bookshoppingapp.dao.BrowsHistoryDao;
import com.example.bookshoppingapp.model.entity.BookItemInfo;
import com.example.bookshoppingapp.model.entity.BrowsingHistory;
import com.example.bookshoppingapp.model.listViewEntity.BrowsingHistoryListViewItem;
import com.example.bookshoppingapp.ui.activity.BrowsingHistoryListActivity;

import java.util.List;

public class BrowsingHistoryListActivityPresenterImpl implements BrowsingHistoryListActivityPresenter {
    /**每个presenter必写部分,
     * 获取对应activity or fragment
     * 用于执行更新activity or fragment的ui方法***/

    BrowsingHistoryListActivity activity;

    public BrowsingHistoryListActivityPresenterImpl(Activity activity){
        this.activity = (BrowsingHistoryListActivity) activity;
    }

    @Override
    public void loadList(List<BrowsingHistoryListViewItem> itemsList, String userId) {
        BrowsHistoryDao browsHistoryDao = new BrowsHistoryDao();
        BookInfoDao bookInfoDao = new BookInfoDao();

        List<BrowsingHistory> browsingHistorieList = browsHistoryDao.getBrowsingHistory(userId, activity);
        for(BrowsingHistory item:browsingHistorieList){
            BookItemInfo bookItemInfo = bookInfoDao.getBookInfo(item.getBookId(), activity);

            BrowsingHistoryListViewItem listViewItem = new BrowsingHistoryListViewItem();

            listViewItem.setItemId(item.getHistoryId());
            listViewItem.setDate(item.getDate());
            listViewItem.setBookId(bookItemInfo.getBookId());
            listViewItem.setBookName(bookItemInfo.getBookName());
            listViewItem.setIconPath(bookItemInfo.getIconPath());
            Log.d(listViewItem.getBookName(), listViewItem.getDate());

            itemsList.add(listViewItem);
        }

        activity.updateListView(itemsList);

    }

    /********************************/



}
