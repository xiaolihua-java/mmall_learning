package com.mmall.util;

import java.math.BigDecimal;

/**
 * Created by MAIBENBEN on 2018-07-25.
 */
public class BigDecimalUtil {

    public BigDecimalUtil() {
    }

    public static BigDecimal add(double v1,double v2){
        BigDecimal b1=new BigDecimal(Double.toString(v1));
        BigDecimal b2=new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal sub(double v1,double v2){
        BigDecimal b1=new BigDecimal(Double.toString(v1));
        BigDecimal b2=new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);//减法
    }

    public static BigDecimal mul(double v1,double v2){
        BigDecimal b1=new BigDecimal(Double.toString(v1));
        BigDecimal b2=new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);//乘法
    }

    public static BigDecimal div(double v1,double v2){
        BigDecimal b1=new BigDecimal(Double.toString(v1));
        BigDecimal b2=new BigDecimal(Double.toString(v2));
        return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);//除法,结果保留两位小数，四舍五入
    }
}
