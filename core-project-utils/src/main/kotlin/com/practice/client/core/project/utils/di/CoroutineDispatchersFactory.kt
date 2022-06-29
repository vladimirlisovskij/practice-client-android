package com.practice.client.core.project.utils.di

import com.practice.client.core.project.utils.di.qualifiers.CoroutineDispatchersQualifier
import com.practice.client.core.project.utils.di.qualifiers.CoroutineDispatchersType
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
object CoroutineDispatchersFactory {
    @Provides
    @CoroutineDispatchersQualifier(CoroutineDispatchersType.MAIN)
    fun provideMainDispatcher() = Dispatchers.Main

    @Provides
    @CoroutineDispatchersQualifier(CoroutineDispatchersType.IO)
    fun provideIODispatcher() = Dispatchers.IO

    @Provides
    @CoroutineDispatchersQualifier(CoroutineDispatchersType.DEFAULT)
    fun provedDefaultDispatcher() = Dispatchers.Default
}