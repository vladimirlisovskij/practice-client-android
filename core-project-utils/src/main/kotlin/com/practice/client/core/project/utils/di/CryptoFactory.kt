package com.practice.client.core.project.utils.di

import android.content.Context
import androidx.security.crypto.MasterKey
import com.practice.client.core.project.dagger.Scopes
import dagger.Module
import dagger.Provides

@Module
object CryptoFactory {
    @Provides
    fun provideMasterKey(context: Context) = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
}