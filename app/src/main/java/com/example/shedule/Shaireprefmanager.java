package com.example.shedule;

import android.content.Context;
import android.content.SharedPreferences;

public class Shaireprefmanager {
    private static com.example.shedule.Shaireprefmanager mInstance;
    private static Context mctx;

    private static final String SHARED_PREF_NAME="mysharedpref12";
    private static final String KEY_USER_NAME="username";
    private static final String KEY_ID = "id";

    private Shaireprefmanager(Context context)
    {
        mctx=context;
    }


    public static synchronized com.example.shedule.Shaireprefmanager getInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance=new com.example.shedule.Shaireprefmanager(context);
        }
        return mInstance;
    }

    public boolean userLogin(String username,String userid)
    {
        SharedPreferences sharedPreferences=mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USER_NAME, username);
        editor.putString(KEY_ID,userid);
        editor.apply();return true;
    }


    public boolean isLoggedIn()
    {
        SharedPreferences sharedPreferences=mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_NAME, null) !=null)
        {
            return true;
        }
        return false;
    }

    public boolean Loogout()
    {
        SharedPreferences sharedPreferences=mctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

}
