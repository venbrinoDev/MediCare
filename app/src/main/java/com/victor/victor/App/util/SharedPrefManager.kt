package com.victor.victor.App.util

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {
    var sharedPreferences: SharedPreferences = context.getSharedPreferences(Constant.SHARED_PREF_HOLDER_KEY,Context.MODE_PRIVATE);
    var editor: SharedPreferences.Editor = sharedPreferences.edit();



    fun saveString(value:String,key:String){
        editor.putString(key,value);
        editor.apply()
    }

    fun saveInt(value: Int,key: String){
        editor.putInt(key,value);
        editor.apply()
    }

    fun saveBoolean(key: String, value:Boolean){
        editor.putBoolean(key,value)
        editor.apply()
    }

    fun getString(key:String,defValue:String="empty"):String?{

        return sharedPreferences.getString(key,defValue)
    }

    fun getInt(key: String,defValue:Int=0):Int{

        return sharedPreferences.getInt(key,defValue)
    }

    fun getBoolean(key: String,defValue:Boolean=false):Boolean{

        return sharedPreferences.getBoolean(key,defValue)
    }


}