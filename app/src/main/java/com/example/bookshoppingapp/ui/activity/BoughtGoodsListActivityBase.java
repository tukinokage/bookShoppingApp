package com.example.bookshoppingapp.ui.activity;

import com.example.bookshoppingapp.model.listViewEntity.BoughtHistoryListViewItem;

import java.util.List;

public interface BoughtGoodsListActivityBase {
       void updateShoppingCartListView(List<BoughtHistoryListViewItem> loadList);

}
