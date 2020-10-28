package com.example.bookshoppingapp.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookshoppingapp.model.entity.BookItemInfo;
import com.example.bookshoppingapp.model.entity.ShoppingCartItem;
import com.example.bookshoppingapp.ui.fragment.goodsfragment.GoodsFragment;
import com.example.bookshoppingapp.util.ToastTool;

import java.util.ArrayList;
import java.util.List;

public class BookInfoDao {
    /**根据id获取book**/
    public BookItemInfo getBookInfo(String id, Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getReadableDatabase();
        String[] params = new String[]{id};
        BookItemInfo bookInfo = null;

        try {
            Cursor cursor = db.query(
                    DataBaseHelper.BOOK_ITEM_INFO_TABLE,
                    new String[]{"*"},
                    "bookId=?",
                    params,
                    "",
                    "",
                    "");
            while (cursor.moveToNext()) {
                String bookId = cursor.getString(cursor.getColumnIndex("bookId"));
                String bookName = cursor.getString(cursor.getColumnIndex("bookName"));
                String bookAuthor = cursor.getString(cursor.getColumnIndex("bookAuthor"));
                String introduction = cursor.getString(cursor.getColumnIndex("introduction"));
                String iconPath = cursor.getString(cursor.getColumnIndex("iconPath"));
                String bookType = cursor.getString(cursor.getColumnIndex("bookType"));
                int salesVolume = cursor.getInt(cursor.getColumnIndex("salesVolume"));
                float cost = cursor.getFloat(cursor.getColumnIndex("cost"));

                bookInfo = new BookItemInfo();

                bookInfo.setSalesVolume(salesVolume);
                bookInfo.setCost(cost);
                bookInfo.setBookName(bookName);
                bookInfo.setBookAuthor(bookAuthor);
                bookInfo.setBookId(bookId);
                bookInfo.setBookType(bookType);
                bookInfo.setIntroduction(introduction);
                bookInfo.setIconPath(iconPath);

            }
        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return bookInfo;

    }

    /**根据type获取bookList**/
    public List<BookItemInfo> getBookInfoByType(String type, Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getReadableDatabase();
        String[] params = new String[]{type};
        List<BookItemInfo> bookInfoList = null;

        try {
            bookInfoList = new ArrayList<BookItemInfo>();
            Cursor cursor = db.query(
                    DataBaseHelper.BOOK_ITEM_INFO_TABLE,
                    new String[]{"*"},
                    "bookType=?",
                    params,
                    "",
                    "",
                    "");
            while (cursor.moveToNext()) {
                String bookId = cursor.getString(cursor.getColumnIndex("bookId"));
                String bookName = cursor.getString(cursor.getColumnIndex("bookName"));
                String bookAuthor = cursor.getString(cursor.getColumnIndex("bookAuthor"));
                String introduction = cursor.getString(cursor.getColumnIndex("introduction"));
                String iconPath = cursor.getString(cursor.getColumnIndex("iconPath"));
                String bookType = cursor.getString(cursor.getColumnIndex("bookType"));
                int salesVolume = cursor.getInt(cursor.getColumnIndex("salesVolume"));
                float cost = cursor.getFloat(cursor.getColumnIndex("cost"));

               BookItemInfo bookInfo = new BookItemInfo();

                bookInfo.setSalesVolume(salesVolume);
                bookInfo.setCost(cost);
                bookInfo.setBookName(bookName);
                bookInfo.setBookAuthor(bookAuthor);
                bookInfo.setBookId(bookId);
                bookInfo.setBookType(bookType);
                bookInfo.setIntroduction(introduction);
                bookInfo.setIconPath(iconPath);

                bookInfoList.add(bookInfo);

            }
        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return bookInfoList;

    }

    /**根据销量获取bookList**/
    public List<BookItemInfo> getBookInfoByPopular(Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getReadableDatabase();
        List<BookItemInfo> bookInfoList = null;

        try {
            bookInfoList = new ArrayList<BookItemInfo>();

            Cursor cursor = db.query(
                    DataBaseHelper.BOOK_ITEM_INFO_TABLE,
                    new String[]{"*"},
                    null,
                    null,
                    "",
                    "",
                    "salesVolume desc");
            while (cursor.moveToNext()) {
                String bookId = cursor.getString(cursor.getColumnIndex("bookId"));
                String bookName = cursor.getString(cursor.getColumnIndex("bookName"));
                String bookAuthor = cursor.getString(cursor.getColumnIndex("bookAuthor"));
                String introduction = cursor.getString(cursor.getColumnIndex("introduction"));
                String iconPath = cursor.getString(cursor.getColumnIndex("iconPath"));
                String bookType = cursor.getString(cursor.getColumnIndex("bookType"));
                int salesVolume = cursor.getInt(cursor.getColumnIndex("salesVolume"));
                float cost = cursor.getFloat(cursor.getColumnIndex("cost"));

                BookItemInfo bookInfo = new BookItemInfo();

                bookInfo.setSalesVolume(salesVolume);
                bookInfo.setCost(cost);
                bookInfo.setBookName(bookName);
                bookInfo.setBookAuthor(bookAuthor);
                bookInfo.setBookId(bookId);
                bookInfo.setBookType(bookType);
                bookInfo.setIntroduction(introduction);
                bookInfo.setIconPath(iconPath);

                bookInfoList.add(bookInfo);

            }
        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return bookInfoList;

    }


    //修改销量
    public int updateBookSalesNum(String bookId, int num, Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getWritableDatabase();
        String where = "bookId" + "=?";
        int rowNum = 0;

        try {

            ContentValues contentValues = new ContentValues();
           contentValues.put(" salesVolume", num);
            rowNum = db.update(DataBaseHelper.BOOK_ITEM_INFO_TABLE,
                    contentValues,
                    where,
                    new String[]{bookId});
        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return rowNum;
    }


}
