package com.example.bookshoppingapp.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookshoppingapp.model.entity.UserBoughtItem;

import java.util.ArrayList;
import java.util.List;

public class BoughtItemDao {

    /**根据uid获取BoughtIteList**/
    public List<UserBoughtItem> getUserBoughtItem(String uid, Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getReadableDatabase();
        String[] params = new String[]{String.valueOf(uid)};
        List<UserBoughtItem> userBoughtItemList = null;

        try {
            userBoughtItemList = new ArrayList<UserBoughtItem>();
            Cursor cursor = db.query(
                    DataBaseHelper.USER_BOUGHT_ITEM_TABLE,
                    new String[]{"*"},
                    "userId=?",
                    params,
                    "",
                    "",
                    "");
            while (cursor.moveToNext()) {
                String itemId = cursor.getString(cursor.getColumnIndex("itemId"));
                String userId = cursor.getString(cursor.getColumnIndex("userId"));
                String bookId = cursor.getString(cursor.getColumnIndex("bookId"));

                int num = cursor.getInt(cursor.getColumnIndex("num"));
                float cost = cursor.getFloat(cursor.getColumnIndex("cost"));


                UserBoughtItem userBoughtItem = new UserBoughtItem();

                userBoughtItem.setItemId(itemId);
                userBoughtItem.setUserId(userId);
                userBoughtItem.setNum(num);
                userBoughtItem.setBookId(bookId);
                userBoughtItem.setCost(cost);

                userBoughtItemList.add(userBoughtItem);

            }
        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return userBoughtItemList;

    }



    /**获取cartItem数量**/
    public int getuserBoughtItemNum( Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getReadableDatabase();

        int count = 0;
        try {
            Cursor cursor = db.rawQuery("select * from " + DataBaseHelper.USER_BOUGHT_ITEM_TABLE, null);

            count = cursor.getCount();

        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return count;

    }


    public boolean insertUserBoughtItem(UserBoughtItem item, Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookId", item.getBookId());
        contentValues.put("itemId", item.getItemId());
        contentValues.put("userId",  item.getUserId());
        contentValues.put("cost",  item.getCost());
        contentValues.put("num",  item.getNum());

        long row = db.insert(DataBaseHelper.USER_BOUGHT_ITEM_TABLE,
                null,
                contentValues);

        if(row == -1){
            return false;
        }else {
            return true;
        }
    }


}
