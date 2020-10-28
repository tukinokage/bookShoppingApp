package com.example.bookshoppingapp.util;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**********Toast使用该ToastTool***********/

public class ToastTool {

    /**********主线程使用该方法***********/
        public static void showToastOnMainThread(String text, Context context){

            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

        }



    /**********子线程使用该方法***********/
    public static void showToastOnSlaverThread(String text, Context context){
        Looper.prepare();
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }

}
