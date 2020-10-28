package com.example.bookshoppingapp.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookshoppingapp.model.entity.BrowsingHistory;
import com.example.bookshoppingapp.util.DateUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BrowsHistoryDao {

    /**根据uid获取BoughtIteList**/
    public List<BrowsingHistory> getBrowsingHistory(String uid, Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getReadableDatabase();
        String[] params = new String[]{String.valueOf(uid)};
        List<BrowsingHistory> BrowsingHistoryList = null;

        try {
            BrowsingHistoryList = new ArrayList<BrowsingHistory>();
            Cursor cursor = db.query(
                    DataBaseHelper.BROWSING_HISTORY_TABLE,
                    new String[]{"*"},
                    "userId=?",
                    params,
                    "",
                    "",
                    "date desc");
            while (cursor.moveToNext()) {
                String itemId = cursor.getString(cursor.getColumnIndex("historyId"));
                String userId = cursor.getString(cursor.getColumnIndex("userId"));
                String bookId = cursor.getString(cursor.getColumnIndex("bookId"));

                String date = cursor.getString(cursor.getColumnIndex("date"));


                BrowsingHistory browsingHistory = new BrowsingHistory();

               browsingHistory.setHistoryId(itemId);
               browsingHistory.setUserId(userId);
               browsingHistory.setBookId(bookId);
               browsingHistory.setDate(date);

                BrowsingHistoryList.add(browsingHistory);

            }
        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return BrowsingHistoryList;

    }



    /**获取数量**/
    public int getBrowsingHistoryNum(Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getReadableDatabase();

        int count = 0;
        try {
            Cursor cursor = db.rawQuery("select * from " + DataBaseHelper.BROWSING_HISTORY_TABLE, null);

            count = cursor.getCount();

        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return count;

    }


    public boolean insertBrowsingHistory(BrowsingHistory item, Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookId", item.getBookId());
        contentValues.put("historyId", item.getHistoryId());
        contentValues.put("userId",  item.getUserId());
        contentValues.put("date",  item.getDate());

        long row = db.insert(DataBaseHelper.BROWSING_HISTORY_TABLE,
                null,
                contentValues);

        if(row == -1){
            return false;
        }else {
            return true;
        }
    }

}
