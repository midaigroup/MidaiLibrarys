package com.lucio.library.util;

import android.text.TextUtils;

import com.lucio.library.widget.SAToast;

import java.text.DecimalFormat;

/**
 * 字符串转换工具
 * Created by zhaoyi on 2016/1/29.
 */
public class StringFormatUtil {

    /**
     * 将字符串转成半密文显示
     *
     * @param source
     * @param type
     * @return
     */
    public static final int TYPE_3_4 = 34;
    public static final int TYPE_4_4 = 44;
    public static final int TYPE_0_4 = 4;
    public static final int TYPE_1_0 = 10;
    public static final int TYPE_4_0_4 = 404;

    public static String formatNum(String source, int type) {
        StringBuffer sb = new StringBuffer();
        try {
            // 身份证
            if (type == TYPE_4_4) {
                sb.append(source.substring(0, 4));
                sb.append("****");
                sb.append(source.subSequence(source.length() - 4, source.length()));
            }

            // 银行卡
            if (type == TYPE_4_0_4) {
                sb.append(source.substring(0, 4));
                sb.append(" **** ");
                sb.append(source.subSequence(source.length() - 4, source.length()));
            }

            // 银行卡2
            if (type == TYPE_0_4) {
                sb.append("**** **** ");
                sb.append(source.subSequence(source.length() - 4, source.length()));
            }

            // 手机号
            if (type == TYPE_3_4) {
                sb.append(source.substring(0, 3));
                sb.append("****");
                sb.append(source.substring(7, 11));
            }

            // 姓名
            if (type == TYPE_1_0) {
                sb.append(source.substring(0, 1));
                sb.append("**");
            }
        } catch (Exception e) {
            return "";
        }
        return sb.toString();
    }

    /**
     * 将字符串转成两位小数的金额形式
     */
    public static String formatMoney(String s) throws Exception {
        double d = Double.valueOf(s);
        if (d < 0L) {
            throw new RuntimeException("金额错误");
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(d);
    }

    /**
     * 将字符串转成三位一逗号的表示形式
     */
    public static String formatStringByThree(String s) {
        int l = s.length();
        StringBuilder sb = new StringBuilder();
        int i = l % 3;
        sb.append(s.substring(0, i));
        int n = 1;
        while (l >= 3) {
            sb.append(",");
            sb.append(s.substring(3 * n - 3 + i, 3 * n + i));
            n++;
            l -= 3;
        }
        if (i == 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    /**
     * 将字符串转换成整数形式
     */
    public static int formatStringToInt(String s) {
        int i = 0;
        if (!TextUtils.isEmpty(s)) {
            try {
                double d = Double.valueOf(s);
                i = (int) (d);
            } catch (Exception e) {
            }
        }
        return i;
    }

    /**
     * 将金额字符串去掉小数点
     */
    public static String formatMoneyWithoutDecimal(String s) {
        if (null != s) {
            String arr [] = s.split("\\.");
            if (arr.length != 0 && arr.length == 2) {
                return arr[0];
            }
        }
        return s;
    }
}
