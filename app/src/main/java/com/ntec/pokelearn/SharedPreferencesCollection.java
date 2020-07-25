package com.ntec.pokelearn;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesCollection {
    private Context context;

    public SharedPreferencesCollection(Context context) { this.context = context; }

    public final String PREFS_NAME = "sharedPrefs";

    public boolean checkPrefsIsEmpty(String prefName) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        String prefExist = sharedPref.getString(prefName, null);

        if (prefExist == null) { return true; }
        return false;
    }

    //store string
    public void setStr(String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStr(String key) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(key, null);
    }
}
