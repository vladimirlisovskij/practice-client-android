package com.practice.client.feature.project.impl.auth.di

import com.practice.client.core.project.base.domain.UserDataContainer
import com.practice.client.feature.project.auth.repo.UserRepository
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
abstract class AuthDataContainers {
    @Binds
    @IntoSet
    abstract fun provideUserRepositoryContainer(authRepository: UserRepository): UserDataContainer
}