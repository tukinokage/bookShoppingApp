package com.example.bookshoppingapp.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/******该DAO为示例****/
/*

public class StudentDao {

//获取
    public Student getStudent(int id, Activity activity){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(activity).getReadableDatabase();
        String[] params = new String[]{String.valueOf(id)};
        Student student = null;

        try {
            Cursor cursor = db.query(
                    DataBaseHelper.STUDENT_TABLE_NAME,
                    new String[]{"*"},
                    "_id=?",
                    params,
                    "",
                    "",
                    "");
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int stuid = cursor.getInt(cursor.getColumnIndex("_id"));
                String sex = cursor.getString(cursor.getColumnIndex("sex"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));

                student = new Student();
                student.setAge(age);
                student.setStudentName(name);
                student.setSex(sex);
                student.setId(stuid);


            }
        }catch (SQLException e){
            Log.e("数据库数据读取错误:", e.getMessage());
        }

        return student;

    }

    */

/**失败返回false***/
/*

//添加
    public boolean insertStu(Student student, Context context){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(context).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sex", student.getSex());
        contentValues.put("age", student.getAge());
        contentValues.put("name",  student.getStudentName());

        long row = db.insert(DataBaseHelper.STUDENT_TABLE_NAME,
                null,
                contentValues);

        if(row == -1){
            return false;
        }else {
            return true;
        }
    }

//删除
    public void deleteStuById(int id, Context context){
        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(context).getWritableDatabase();
        String where = "_id" + "=?";
        db.delete(DataBaseHelper.STUDENT_TABLE_NAME,
                where,
                new String[]{Integer.toString(id)}
        );
    }


//修改
    public void updateStuById(String whereName,
                              int id,
                              String paramName,
                              String paramValue,
                              Context context){

        SQLiteDatabase db = DataBaseHelper.getDataBaseHelper(context).getWritableDatabase();
        String where = whereName + "=?";
        ContentValues contentValues = new ContentValues();
        contentValues.put(paramName, paramValue);
        db.update(DataBaseHelper.STUDENT_TABLE_NAME,
                contentValues,
                where,
                new String[]{Integer.toString(id)});


    }



}

*/


