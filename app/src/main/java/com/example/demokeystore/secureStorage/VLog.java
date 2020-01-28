package com.example.demokeystore.secureStorage;


import android.util.Log;

import com.example.demokeystore.BuildConfig;

import java.util.Date;

public final class VLog {

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
