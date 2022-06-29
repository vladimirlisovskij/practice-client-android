package com.practice.client.core.project.utils.di

import dagger.Module

@Module(includes = [CryptoFactory::class, SharedPreferencesFactory::class, CoroutineDispatchersFactory::class])
object UtilsFactory