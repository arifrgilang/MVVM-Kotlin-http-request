/*
 * *
 *  * Created by Arif R. Gilang on 12/27/19 7:36 AM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/27/19 7:36 AM
 *
 */

package com.arifrgilang.baut.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SharedPreferenceHelper {
    companion object{
        private const val PREF_TIME = "Pref time"
        private var prefs: SharedPreferences? = null

        @Volatile private var instance: SharedPreferenceHelper? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): SharedPreferenceHelper = instance
            ?: synchronized(
                LOCK
            ){
                instance
                    ?: buildHelper(context).also {
                        instance = it
                    }
            }

        private fun buildHelper(context: Context): SharedPreferenceHelper {
            prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferenceHelper()
        }
    }

    fun saveUpdateTime(time: Long) {
        prefs?.edit(commit = true){
            putLong(PREF_TIME, time)
        }
    }

    fun getUpdateTime() = prefs?.getLong(PREF_TIME, 0)

    fun getChacheDuration() = prefs?.getString("pref_cache_duration", "")
}