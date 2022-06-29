package com.practice.client.feature.project.impl.auth.di

import com.practice.client.core.project.dagger.Scopes
import com.practice.client.core.project.utils.di.qualifiers.CoroutineDispatchersQualifier
import com.practice.client.core.project.utils.di.qualifiers.CoroutineDispatchersType
import com.practice.client.core.project.utils.di.qualifiers.SharedPreferencesQualifier
import com.practice.client.core.project.utils.di.qualifiers.SharedPreferencesType
import com.practice.client.core.project.utils.prefs.SharedPreferencesService
import com.practice.client.feature.project.auth.repo.LoginRepository
import com.practice.client.feature.project.auth.repo.UserRepository
import com.practice.client.feature.project.impl.auth.LoginRepositoryImpl
import com.practice.client.feature.project.impl.auth.UserRepositoryImpl
import com.practice.network_entities.params.UserType
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher

@Module(includes = [AuthDataContainers::class])
object AuthRepositoryFactory {
    @Provides
    @Scopes.AppScope
    fun provideUserRepositoryImpl(
        @SharedPreferencesQualifier(SharedPreferencesType.ENCRYPTED) prefsService: SharedPreferencesService,
        @CoroutineDispatchersQualifier(CoroutineDispatchersType.IO) coroutineDispatcher: CoroutineDispatcher,
        userType: UserType
    ): UserRepository = UserRepositoryImpl(
        prefsService,
        coroutineDispatcher,
        userType
    )

    @Provides
    @Scopes.AppScope
    fun provideLoginRepositoryImpl(
        @CoroutineDispatchersQualifier(CoroutineDispatchersType.IO) coroutineDispatcher: CoroutineDispatcher,
        httpClient: HttpClient
    ): LoginRepository = LoginRepositoryImpl(
        coroutineDispatcher,
        httpClient
    )
}

