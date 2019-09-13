package com.example.user.projetz.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.user.projetz.model.Forfait;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Admin on 23/02/2018.
 */

public class SharedPreference {
    public static void shareArrayList(ArrayList<Forfait> list, String key, Context context){
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=pref.edit();
        Gson gson= new Gson();
        String json= gson.toJson(list);
        editor.putString(key, json);
        editor.apply();

    }
    public static ArrayList<Forfait> getArrayList(String key, Context context){
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson= new Gson();
        String json=pref.getString(key,null);
        Type type=new TypeToken<ArrayList<Forfait>>(){}.getType();
        return gson.fromJson(json,type) ;
    }
}
