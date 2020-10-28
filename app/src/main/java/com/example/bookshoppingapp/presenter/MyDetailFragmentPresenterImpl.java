package com.example.bookshoppingapp.presenter;

import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.bookshoppingapp.ui.activity.BoughtGoodsListActivity;
import com.example.bookshoppingapp.ui.activity.BrowsingHistoryListActivity;
import com.example.bookshoppingapp.ui.fragment.mydetailfragment.MyDetailFragment;
import com.example.bookshoppingapp.util.ActivityCollectorUtil;
import com.example.bookshoppingapp.util.UserInfoUtil;

public class MyDetailFragmentPresenterImpl implements MyDetailFragmentPresenter {

    /**每个presenter必写部分,
     * 获取对应activity or fragment
     * 用于执行更新activity or fragment的ui方法***/

    MyDetailFragment fragment;

    public MyDetailFragmentPresenterImpl(Fragment fragment){
        this.fragment = (MyDetailFragment) fragment;
    }

    @Override
    public void checkBoughtGoods() {
        Intent intent = new Intent(fragment.getActivity(), BoughtGoodsListActivity.class);
        intent.putExtra("userId", UserInfoUtil.getUserId(fragment.getActivity()));
        fragment.turnActivity(intent);

    }

    @Override
    public void checkBrowsingHistory() {
        Intent intent = new Intent(fragment.getActivity(), BrowsingHistoryListActivity.class);
        intent.putExtra("userId", UserInfoUtil.getUserId(fragment.getActivity()));
        fragment.turnActivity(intent);
    }

    @Override
    public void quitLoginStatus() {
        ActivityCollectorUtil.finishAllActivity();
    }


    /********************************/





}
