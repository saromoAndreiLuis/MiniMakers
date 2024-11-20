package com.example.minimakers;

import android.content.Context;

public class SharedPreferences {
    private static final String PREF_NAME = "MiniMakers";
    private android.content.SharedPreferences sharedPreferences;
    private android.content.SharedPreferences.Editor editor;

    public SharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }



}