package com.practice.client.core.project.utils.prefs.internal

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.practice.client.core.project.utils.prefs.SharedPreferencesService

internal class EncryptedPrefsService (
    private val context: Context,
    private val masterKey: MasterKey
) : SharedPreferencesService {
    override fun getPreferences(filename: String): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            filename,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}

