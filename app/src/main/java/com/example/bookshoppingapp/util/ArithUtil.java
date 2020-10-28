package com.example.bookshoppingapp.util;

import java.math.BigDecimal;

public class ArithUtil{
    private static final int DEF_DIV_SCALE=10;

    private ArithUtil(){}
    //精确的加法算法
    public static float add(float d1,float d2){
        BigDecimal b1=new BigDecimal(Float.toString(d1));
        BigDecimal b2=new BigDecimal(Float.toString(d2));
        return b1.add(b2).floatValue();

    }
    //精确的减法算法
    public static float sub(float d1,float d2){
        BigDecimal b1=new BigDecimal(Float.toString(d1));
        BigDecimal b2=new BigDecimal(Float.toString(d2));
        return b1.subtract(b2).floatValue();

    }

    //精确的乘法算法
    public static float mul(float d1, float d2){
        BigDecimal b1=new BigDecimal(Float.toString(d1));
        BigDecimal b2=new BigDecimal(Float.toString(d2));
        return b1.multiply(b2).floatValue();

    }

}