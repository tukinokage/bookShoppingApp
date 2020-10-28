package com.example.bookshoppingapp.presenter;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.example.bookshoppingapp.dao.BookInfoDao;
import com.example.bookshoppingapp.dao.BrowsHistoryDao;
import com.example.bookshoppingapp.model.BookTypeEnum;
import com.example.bookshoppingapp.model.entity.BookItemInfo;
import com.example.bookshoppingapp.model.listViewEntity.BookTypeListItem;
import com.example.bookshoppingapp.model.entity.BrowsingHistory;
import com.example.bookshoppingapp.ui.fragment.goodsfragment.GoodsFragment;
import com.example.bookshoppingapp.util.DateUtil;
import com.example.bookshoppingapp.util.UserInfoUtil;

import java.util.List;

public class GoodsFragmentPresenterImpl implements GoodsFragmentPresenter {

    /**每个presenter必写部分,
     * 获取对应activity or fragment
     * 用于执行更新activity or fragment的ui方法***/

    GoodsFragment fragment;

    public GoodsFragmentPresenterImpl(Fragment fragment){
        this.fragment = (GoodsFragment) fragment;
    }


    /********************************/

    @Override
    public void doSomething() {
        fragment.showToast("尼玛撕了");
    }

    @Override
    public void switchType( List<BookTypeListItem> typeItemList, int position) {
        BookInfoDao bookDao = new BookInfoDao();
        List<BookItemInfo> bookList = null;

        BookTypeEnum typeEnum = typeItemList.get(position).getBookTypeEnum();
        if(typeEnum == BookTypeEnum.POPULAR){
            bookList = bookDao.getBookInfoByPopular(fragment.getActivity());
        }else {
            bookList = bookDao.getBookInfoByType(typeEnum.getTypeStr(), fragment.getActivity());
        }

        fragment.updateBookListView(bookList);
    }

    @Override
    public void updateBrowsingHistory(List<BookItemInfo> ItemList, int position) {
        BrowsHistoryDao browsHistoryDao = new BrowsHistoryDao();
        BrowsingHistory browsingHistory = new BrowsingHistory();

        int historyId = browsHistoryDao.getBrowsingHistoryNum(fragment.getActivity());
        browsingHistory.setBookId(ItemList.get(position).getBookId());
        browsingHistory.setUserId(UserInfoUtil.getUserId(fragment.getActivity()));
        browsingHistory.setHistoryId(String.valueOf(historyId + 1));
        browsingHistory.setDate(DateUtil.getDate());
        Log.d("DateUtil.getDate()", DateUtil.getDate());

        boolean b = browsHistoryDao.insertBrowsingHistory(browsingHistory, fragment.getActivity());
        if (!b){
            Log.e("浏览记录新增","失败");
        }
    }


}
