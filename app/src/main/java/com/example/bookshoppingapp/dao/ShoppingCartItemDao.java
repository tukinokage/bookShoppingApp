package com.example.bookshoppingapp.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bookshoppingapp.model.entity.ShoppingCartItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItemDao {

    /**根据uid获取cartItemList**/
    public List<ShoppingCartItem> getshoppingCartItem(String uid, Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getReadableDatabase();
        String[] params = new String[]{String.valueOf(uid)};
        List<ShoppingCartItem> shoppingCartItemList = null;

        try {

            shoppingCartItemList = new ArrayList<ShoppingCartItem>();
            Cursor cursor = db.query(
                    DataBaseHelper.SHOPPING_CART_ITEM_TABLE,
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
              

                ShoppingCartItem shoppingCartItem = new ShoppingCartItem();

                shoppingCartItem.setItemId(itemId);
                shoppingCartItem.setUserId(userId);
                shoppingCartItem.setNum(num);
                shoppingCartItem.setBookId(bookId);

                shoppingCartItemList.add(shoppingCartItem);

            }
        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return shoppingCartItemList;

    }



    /**根据bookId和userId获取cartItem**/
    public ShoppingCartItem getCartItem(String bookId, String userId, Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getReadableDatabase();
        String[] params = new String[]{String.valueOf(bookId), userId};
        ShoppingCartItem shoppingCartItem = null;

        try {


            Cursor cursor = db.query(
                    DataBaseHelper.SHOPPING_CART_ITEM_TABLE,
                    new String[]{"*"},
                    "bookId=? and userId=?",
                    params,
                    "",
                    "",
                    "");

            while (cursor.moveToNext()) {
                shoppingCartItem = new ShoppingCartItem();
                String itemId = cursor.getString(cursor.getColumnIndex("itemId"));
                String uId = cursor.getString(cursor.getColumnIndex("userId"));
                String bId = cursor.getString(cursor.getColumnIndex("bookId"));

                int num = cursor.getInt(cursor.getColumnIndex("num"));

                shoppingCartItem.setItemId(itemId);
                shoppingCartItem.setUserId(uId);
                shoppingCartItem.setNum(num);
                shoppingCartItem.setBookId(bId);


            }
        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return shoppingCartItem;

    }




    /**根据uid获取cartItem数量**/
    public int getshoppingCartItemNum(Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getReadableDatabase();

        int count = 0;
        try {
            Cursor cursor = db.rawQuery("select * from "
                    + DataBaseHelper.SHOPPING_CART_ITEM_TABLE, null);

            count = cursor.getCount();

        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return count;

    }


    //修改记录,修改失败返回0
    public int updateCartItem(ShoppingCartItem shoppingCartItem, Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getWritableDatabase();
        String[] paramsValues = new String[]{String.valueOf(shoppingCartItem.getItemId()),
                                                        shoppingCartItem.getUserId(),
                                                        shoppingCartItem.getBookId(),
                                                        String.valueOf(shoppingCartItem.getNum())};
        String[] paramsName = new String[]{"itemId", "userId", "bookId", "num"};
        String where = "itemId" + "=?";
        int rowNum = 0;

        try {

            ContentValues contentValues = new ContentValues();
            for(int i = 0; i < paramsName.length; i++){
                contentValues.put(paramsName[i], paramsValues[i]);
            }
            rowNum = db.update(DataBaseHelper.SHOPPING_CART_ITEM_TABLE,
                    contentValues,
                    where,
                    new String[]{shoppingCartItem.getItemId()});
        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return rowNum;

    }


    //删除
    public int deleteCartByCartId(String cartId, Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getWritableDatabase();
        String where = "itemId" + "=?";
        int rowNum = 0;
        try {
           rowNum = db.delete(DataBaseHelper.SHOPPING_CART_ITEM_TABLE,
                    where,
                    new String[]{cartId}
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return rowNum;
    }


    //添加
    /** @return -1 失败*/
    public boolean insertShoppingCart(ShoppingCartItem shoppingCartItem, Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getWritableDatabase();
        String[] paramsValues = new String[]{String.valueOf(shoppingCartItem.getItemId()),
                shoppingCartItem.getUserId(),
                shoppingCartItem.getBookId(),
                String.valueOf(shoppingCartItem.getNum())} ;
        String[] paramsName = new String[]{"itemId", "userId", "bookId", "num"};
        long row = -1;

        try {
            ContentValues contentValues = new ContentValues();
            for(int i = 0; i < paramsName.length; i++){
                contentValues.put(paramsName[i], paramsValues[i]);
            }
            row = db.insert(DataBaseHelper.SHOPPING_CART_ITEM_TABLE,
                    null,
                    contentValues
                    );
        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        if(row == -1){
            return false;
        }else {
            return true;
        }
    }


}

