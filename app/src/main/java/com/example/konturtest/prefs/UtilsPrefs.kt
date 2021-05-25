package com.example.konturtest.prefs

import android.content.Context
import android.content.SharedPreferences
import java.util.*

object UtilsPrefs {

    private const val SHARED_PREFS_FILE = "contacts_settings"
    private const val KEY_DATE = "ru.kontur.LAST_LOAD"
    private const val NO_SETTING = -1L

    private var mPrefs : SharedPreferences? = null

    private fun getPrefs(context: Context) : SharedPreferences {
        if (mPrefs == null)
            mPrefs = context.getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE)

        return mPrefs!!
    }

    fun saveDate(context: Context)
    {
        getPrefs(context).apply {
            edit().apply{
                putLong(KEY_DATE, Date().time)
                apply()
            }
        }
    }

    private fun getDate(context: Context) : Long
    {
        getPrefs(context).apply {
            return getLong(KEY_DATE, NO_SETTING)
        }
    }

    fun itsLoadTime(context: Context) : Boolean
    {
        getDate(context).let {
            if (it == NO_SETTING)
                return true
            else
                return (Date().time - it)/1000 > 60
        }
    }

}