package com.practice.client.core.project.utils.prefs

import android.content.SharedPreferences

interface SharedPreferencesService {
    fun getPreferences(filename: String): SharedPreferences
}

