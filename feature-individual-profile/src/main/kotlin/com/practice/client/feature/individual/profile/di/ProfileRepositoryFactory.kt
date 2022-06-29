package com.practice.client.feature.individual.profile.di

import com.practice.client.core.project.dagger.Scopes
import com.practice.client.core.project.utils.di.qualifiers.CoroutineDispatchersQualifier
import com.practice.client.core.project.utils.di.qualifiers.CoroutineDispatchersType
import com.practice.client.feature.individual.profile.data.ProfileRepositoryImpl
import com.practice.client.feature.individual.profile.domain.ProfileRepository
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher

@Module
object ProfileRepositoryFactory {
    @Provides
    @Scopes.AppScope
    fun provideProfileRepository(
        @CoroutineDispatchersQualifier(CoroutineDispatchersType.IO) dispatcherIO: CoroutineDispatcher,
        httpClient: HttpClient
    ): ProfileRepository = ProfileRepositoryImpl(
        dispatcherIO,
        httpClient
    )
}