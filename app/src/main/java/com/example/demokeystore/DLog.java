package com.example.demokeystore;


import android.util.Log;

import java.util.Date;

public final class DLog {

    public static void d(final String tag, final String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void w(final String tag, final String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void e(final String tag, final String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void i(final String tag, final String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }
}
