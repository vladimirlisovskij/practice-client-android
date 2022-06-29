package com.practice.client.core.project.utils.prefs.internal

import android.content.Context
import android.content.SharedPreferences
import com.practice.client.core.project.utils.prefs.SharedPreferencesService

internal class DefaultPrefsService(private val context: Context) : SharedPreferencesService {
    override fun getPreferences(filename: String): SharedPreferences {
        return context.getSharedPreferences(filename, Context.MODE_PRIVATE)
    }
}