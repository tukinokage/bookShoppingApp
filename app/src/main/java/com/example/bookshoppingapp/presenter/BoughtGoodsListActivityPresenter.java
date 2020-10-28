package com.example.bookshoppingapp.presenter;

import com.example.bookshoppingapp.model.listViewEntity.BoughtHistoryListViewItem;

import java.util.List;

public interface BoughtGoodsListActivityPresenter {

    void loadList(List<BoughtHistoryListViewItem> itemsList, String userId);
}
