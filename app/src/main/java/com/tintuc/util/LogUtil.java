package com.tintuc.util;

import android.util.Log;

/**
 * Created by Hp on 2/6/2018.
 */

public class LogUtil {

    static boolean isLogEnable = true;

    public static void d(String tag, String msg) {
        if (isLogEnable) {
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isLogEnable) {
            Log.v(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isLogEnable) {
            Log.e(tag, msg);
        }
    }
}
