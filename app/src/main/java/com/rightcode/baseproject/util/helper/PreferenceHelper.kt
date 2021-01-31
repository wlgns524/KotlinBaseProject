package com.rightcode.baseproject.util.helper

import android.app.Activity
import android.content.Context
import javax.inject.Singleton

@Singleton
open class PreferenceHelper constructor(
    private val mContext: Context
) {

    private val mPackageName: String = mContext.packageName

    enum class PreferenceKey {
        ConnectServer,
        Token
    }


    fun put(key: PreferenceKey, value: String?): Boolean {
        val pref =
            mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(key.name, value)
        return editor.commit()
    }

    fun put(key: String?, value: String?): Boolean {
        val pref =
            mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(key, value)
        return editor.commit()
    }

    fun put(key: PreferenceKey, value: Boolean): Boolean {
        val pref =
            mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean(key.name, value)
        return editor.commit()
    }

    fun put(key: PreferenceKey, value: Int): Boolean {
        val pref =
            mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt(key.name, value)
        return editor.commit()
    }

    fun put(key: PreferenceKey, value: Long): Boolean {
        val pref =
            mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putLong(key.name, value)
        return editor.commit()
    }

    fun put(key: PreferenceKey, value: Long?): Boolean {
        if (value == null) {
            return false
        }
        val pref =
            mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putLong(key.name, value)
        return editor.commit()
    }

    operator fun get(key: PreferenceKey, defaultValue: String): String? {
        val pref =
            mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
        return pref.getString(key.name, defaultValue)
    }

    operator fun get(key: String?, defaultValue: String): String? {
        val pref =
            mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
        return pref.getString(key, defaultValue)
    }

    operator fun get(key: PreferenceKey, defaultValue: Int): Int {
        val pref =
            mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
        return pref.getInt(key.name, defaultValue)
    }

    fun getLong(key: PreferenceKey, defaultValue: Long): Long {
        val pref =
            mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
        return pref.getLong(key.name, defaultValue)
    }

    fun getLong(key: PreferenceKey): Long? {
        val pref =
            mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
        val value = pref.getLong(key.name, -1)
        return if (value > 0) value else null
    }

    operator fun get(key: PreferenceKey, defaultValue: Boolean): Boolean {
        val pref =
            mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
        return pref.getBoolean(key.name, defaultValue)
    }

    val all: Map<String, *>
        get() {
            val pref =
                mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
            return pref.all
        }

    fun remove(key: PreferenceKey) {
        remove(key.name)
    }

    fun remove(key: String) {
        val pref =
            mContext.getSharedPreferences(mPackageName, Activity.MODE_PRIVATE)
        val editor = pref.edit()
        editor.remove(key)
        editor.commit()
    }

    companion object {
        @Volatile
        private var mPreferenceHelper: PreferenceHelper? = null
        fun getInstance(context: Context): PreferenceHelper? {
            if (mPreferenceHelper == null) {
                synchronized(PreferenceHelper::class.java) {
                    if (mPreferenceHelper == null) {
                        mPreferenceHelper =
                            PreferenceHelper(context)
                    }
                }
            }
            return mPreferenceHelper
        }
    }

}
