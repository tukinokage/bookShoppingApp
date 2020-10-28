package com.example.bookshoppingapp.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/********请用getDataBaseHelper获取实例
 * @Example  使用表名直接调用 DataBaseHelper.xxx_TABLE ***************/

public class DataBaseHelper extends SQLiteOpenHelper {
    /*******数据库名*****/
    public  static final String DATABASE_NAME = "BookShopping.db";
    /*****************/

    /*******表名*****/
    public static final String BOOK_ITEM_INFO_TABLE = "bookiteminfo";
    public static final String SHOPPING_CART_ITEM_TABLE = "shoppingcartitem";
    public static final String USER_BOUGHT_ITEM_TABLE = "userboughtitem";
    public static final String USER_INFO_TABLE = "userinfo";
    public static final String BROWSING_HISTORY_TABLE = "browsinghistory";

    /******************/



    private static final int DATABASE_VERSION = 1;

        private static final String BOOK_ITEM_INFO_SQL =
            "create table "+
                            BOOK_ITEM_INFO_TABLE + "(" +
                            "bookId varchar(64) PRIMARY KEY not null," +
                            "bookName varchar(64)," +
                            "bookAuthor varchar(64)," +
                            "introduction TEXT," +
                            "iconPath varchar(64)," +
                            "bookType varchar(8)," +
                            "salesVolume INTEGER," +
                            "cost float" +
                            ");" ;

    private static final String SHOPPING_CART_ITEM_SQL =
            "create table "+
                    SHOPPING_CART_ITEM_TABLE + "(" +
                  "itemId varchar(64) PRIMARY KEY not null," +
                    "userId varchar(64) not null ," +
                    "bookId varchar(64)  not null," +
                    "num INTEGER" +
                    ");" ;


    private static final String USER_BOUGHT_ITEM_SQL =
            "create table "+
                            USER_BOUGHT_ITEM_TABLE + "(" +
                            "itemId varchar(64)  PRIMARY KEY not null," +
                            "bookId varchar(64) not null," +
                            "userId varchar(64) not null," +
                            "num INTEGER," +
                            "cost float" +
                            ");" ;


    private static final String USER_INFO_SQL =
            "create table "+
                            USER_INFO_TABLE + "(" +
                            "userId varchar(64) PRIMARY KEY not null," +
                            "userName varchar(64)  not null," +
                            "userPassword varchar(64)  not null," +

                            "iconPath varchar(64)" +
                            ");" ;

    private static final String BROWSING_HISTORY_SQL =
            "create table "+
                            BROWSING_HISTORY_TABLE + "(" +
                            "historyId varchar(64) PRIMARY KEY not null," +
                            "userId varchar(64) not null, " +
                            "bookId varchar(64) not null," +
                            "date Date" +
                            ");" ;







    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BOOK_ITEM_INFO_SQL);
       db.execSQL(USER_BOUGHT_ITEM_SQL);
       db.execSQL(SHOPPING_CART_ITEM_SQL);
       db.execSQL(USER_INFO_SQL);
       db.execSQL(BROWSING_HISTORY_SQL); ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private volatile static DataBaseHelper dataBaseHelper = null;
    public static synchronized DataBaseHelper getDataBaseHelper(Context context){
        if(dataBaseHelper == null){
            dataBaseHelper = new DataBaseHelper(context);
        }

        return dataBaseHelper;
    }


}
