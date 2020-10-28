package com.example.bookshoppingapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bookshoppingapp.R;
import com.example.bookshoppingapp.adapter.BoughtGoodsListAdapter;
import com.example.bookshoppingapp.model.listViewEntity.BoughtHistoryListViewItem;
import com.example.bookshoppingapp.presenter.BoughtGoodsListActivityPresenter;
import com.example.bookshoppingapp.presenter.BoughtGoodsListActivityPresenterImpl;
import com.example.bookshoppingapp.util.ActivityCollectorUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BoughtGoodsListActivity extends BaseActivity implements BoughtGoodsListActivityBase {

    //
    String userId;
    //list
    List<BoughtHistoryListViewItem> listViewItems;

    //view
    @BindView(R.id.bought_goods_list_acitvity_listview)
    ListView boughtHistoryListView;

    @BindView(R.id.top_bar_back_btn_Textview)
    TextView backBtn;

    @BindView(R.id.top_bar_title_Textview)
     TextView titleTextView;


    //adapter
    BoughtGoodsListAdapter boughtGoodsListAdapter;


    //presenter
    BoughtGoodsListActivityPresenter boughtGoodsListActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boought_goods_list);

        ButterKnife.bind(this);

        ActivityCollectorUtil.addActivity(this);
    }



    @Override
    protected void onStart() {
        super.onStart();

       userId =  getIntent().getStringExtra("userId");
       listViewItems = new ArrayList<BoughtHistoryListViewItem>();
       boughtGoodsListActivityPresenter = new BoughtGoodsListActivityPresenterImpl(this);

       //初始化listview
        boughtGoodsListAdapter = new BoughtGoodsListAdapter(listViewItems, this);
        boughtHistoryListView.setAdapter(boughtGoodsListAdapter);

       //加载界面数据
        titleTextView.setText("购买记录");
        boughtGoodsListActivityPresenter.loadList(listViewItems, userId);
    }


    @Override
    public void updateShoppingCartListView(List LoadList) {
        if(!LoadList.isEmpty()){
          boughtGoodsListAdapter.setBooksList(LoadList);
          boughtGoodsListAdapter.notifyDataSetChanged();
        }
    }


    @Override
    void initListener() {

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BoughtGoodsListActivity.this.finish();
            }
        });

    }


}
