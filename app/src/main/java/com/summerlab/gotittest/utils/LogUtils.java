package com.summerlab.gotittest.utils;


public class LogUtils {

    public static void v(String tag, String msg) {
        if (Constants.DEBUG) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (Constants.DEBUG) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (Constants.DEBUG) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (Constants.DEBUG) {
            android.util.Log.e(tag, msg);
        }
    }
}
