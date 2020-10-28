package com.example.bookshoppingapp.dao;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bookshoppingapp.dao.DataBaseHelper;
import com.example.bookshoppingapp.model.entity.UserInfo;

public class UseDB_register {

    private static UseDB_register useDB_register;

    private DataBaseHelper dBaseHelper;
    public UseDB_register(Context context){

        dBaseHelper = new DataBaseHelper(context);

    }

    public UserInfo login_SQL(String username, String password){

        SQLiteDatabase sdb = dBaseHelper.getReadableDatabase();
        String sql = "select * from userinfo where userName=? and userPassword=?";
        Cursor cursor = sdb.rawQuery(sql,new String[]{username,password});
        UserInfo userInfo = null;

        while (cursor.moveToNext()){

            userInfo = new UserInfo();

            String id = cursor.getString(cursor.getColumnIndex("userId"));
            String userName = cursor.getString(cursor.getColumnIndex("userName"));
            String userPassword = cursor.getString(cursor.getColumnIndex("userPassword"));
            String iconPath = cursor.getString(cursor.getColumnIndex("iconPath"));
            userInfo.setUserId(id);
            userInfo.setIconPath(iconPath);
            userInfo.setUserName(userName);
            userInfo.setUserPassword(userPassword);

        }
        cursor.close();

        return userInfo;

    }

    public boolean register_SQL(UserInfo userInfo){

        SQLiteDatabase sdb = dBaseHelper.getReadableDatabase();
        String sql = "Insert into userinfo(userId,userName,userPassword) values(?,?,?)";
        Object obj[] = {userInfo.getUserId(), userInfo.getUserName(), userInfo.getUserPassword()};
        sdb.execSQL(sql,obj);
        return true;

    }

    public synchronized static UseDB_register getInstance(Context context) {


        if (useDB_register == null) {
            useDB_register = new UseDB_register(context);
        }
        return useDB_register;

    }

    /**获取数量**/
    public int getuserAllNum(Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getReadableDatabase();

        int count = 0;
        try {
            Cursor cursor = db.rawQuery("select * from " + DataBaseHelper.USER_INFO_TABLE , null);

            count = cursor.getCount();

        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return count;
    }




}
