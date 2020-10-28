package com.example.bookshoppingapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bookshoppingapp.R;
import com.example.bookshoppingapp.adapter.BrowsingHistoryListAdapter;
import com.example.bookshoppingapp.model.listViewEntity.BrowsingHistoryListViewItem;
import com.example.bookshoppingapp.presenter.BrowsingHistoryListActivityPresenter;
import com.example.bookshoppingapp.presenter.BrowsingHistoryListActivityPresenterImpl;
import com.example.bookshoppingapp.util.ActivityCollectorUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrowsingHistoryListActivity extends BaseActivity implements BrowsingHistoryListActivityBase {

    //
    String userId;
    //list
    List<BrowsingHistoryListViewItem> listViewItems;

    //view
    @BindView(R.id.browsing_history_acitvity_listview)
    ListView browsingHistoryListView;

    @BindView(R.id.top_bar_back_btn_Textview)
    TextView backBtn;

    @BindView(R.id.top_bar_title_Textview)
    TextView titleTextView;

    //adapter
    BrowsingHistoryListAdapter browsingHistoryListAdapter;

    //presenter
    BrowsingHistoryListActivityPresenter browsingHistoryListActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browsing_history_detail_list);

        ButterKnife.bind(this);

        ActivityCollectorUtil.addActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

       userId =  getIntent().getStringExtra("userId");
       listViewItems = new ArrayList<BrowsingHistoryListViewItem>();

    browsingHistoryListActivityPresenter = new BrowsingHistoryListActivityPresenterImpl(this);
       //初始化listview
        browsingHistoryListAdapter = new BrowsingHistoryListAdapter(listViewItems, this);
        browsingHistoryListView.setAdapter(browsingHistoryListAdapter);

       //加载界面数据
        titleTextView.setText("浏览记录");
        browsingHistoryListActivityPresenter.loadList(listViewItems, userId);
    }


    @Override
    public void updateListView(List LoadList) {
        if(!LoadList.isEmpty()){
          browsingHistoryListAdapter.setBooksList(LoadList);
          browsingHistoryListAdapter.notifyDataSetChanged();
        }
    }


    @Override
    void initListener() {
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowsingHistoryListActivity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
