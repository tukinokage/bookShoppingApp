package com.example.bookshoppingapp.presenter;

import com.example.bookshoppingapp.model.listViewEntity.BrowsingHistoryListViewItem;

import java.util.List;

public interface BrowsingHistoryListActivityPresenter {

    void loadList(List<BrowsingHistoryListViewItem> itemsList, String userId);
}
