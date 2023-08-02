package xyz.v.joyboy.utils

import android.content.Context


class MySp {

    companion object{
        val pref_name:String = "MYSPJB"

        fun insert(key:String,value:String,context: Context){
            val sharedPreference =  context.getSharedPreferences(pref_name,Context.MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString(key,value)
            editor.apply()
        }

        fun get(key:String,context: Context):String{
            val sharedPreference =  context.getSharedPreferences(pref_name,Context.MODE_PRIVATE)
            return sharedPreference.getString(key,"-1").toString()
        }

        fun delete(key:String,context: Context){
            val sharedPreference =  context.getSharedPreferences(pref_name,Context.MODE_PRIVATE)
            sharedPreference.edit().remove(key).apply()
        }

        fun clear(context: Context){
            val sharedPreference =  context.getSharedPreferences(pref_name,Context.MODE_PRIVATE)
            sharedPreference.edit().clear().apply()
        }

    }


}