package com.practice.client.core.project.utils.di

import android.content.Context
import androidx.security.crypto.MasterKey
import com.practice.client.core.project.utils.di.qualifiers.SharedPreferencesQualifier
import com.practice.client.core.project.utils.di.qualifiers.SharedPreferencesType
import com.practice.client.core.project.utils.prefs.SharedPreferencesService
import com.practice.client.core.project.utils.prefs.internal.DefaultPrefsService
import com.practice.client.core.project.utils.prefs.internal.EncryptedPrefsService
import dagger.Module
import dagger.Provides

@Module
object SharedPreferencesFactory {
    @Provides
    @SharedPreferencesQualifier(SharedPreferencesType.DEFAULT)
    fun provideDefaultPrefs(context: Context): SharedPreferencesService = DefaultPrefsService(context)

    @Provides
    @SharedPreferencesQualifier(SharedPreferencesType.ENCRYPTED)
    fun provideEncryptedPrefs(
        context: Context,
        masterKey: MasterKey
    ): SharedPreferencesService = EncryptedPrefsService(context, masterKey)
}

