package com.example.demokeystore.secureStorage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.demokeystore.secureStorage.store.IValueStore;


public class ValueStore implements IValueStore {

    private SharedPreferences mSharedPreferences;

    public ValueStore(Context context) {
        mSharedPreferences = context.getSharedPreferences("idemia-preferences", Context.MODE_PRIVATE);
    }

    public void store(String key, Boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (value == null) {
            editor.putBoolean(key, false);
        } else {
            editor.putBoolean(key, value);
        }
        editor.commit();
    }

    public void store(String key, Integer value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (value == null) {
            editor.putInt(key, 0);
        } else {
            editor.putInt(key, value);
        }
        editor.commit();
    }

    public void store(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (value == null) {
            editor.putString(key, "");
        } else {
            editor.putString(key, value);
        }
        editor.commit();
    }

    public void store(String key, Long value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (value == null) {
            editor.putLong(key, 0);
        } else {
            editor.putLong(key, value);
        }
        editor.commit();
    }

    public void clear(String key) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public Boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public Integer getInteger(String key) {
        return getInteger(key, 0);
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public Long getLong(String key) {
        return getLong(key, 0);
    }

    public Boolean getBoolean(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public Integer getInteger(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public Long getLong(String key, long defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }

    public boolean has(String key) {
        return mSharedPreferences.contains(key);
    }
}
