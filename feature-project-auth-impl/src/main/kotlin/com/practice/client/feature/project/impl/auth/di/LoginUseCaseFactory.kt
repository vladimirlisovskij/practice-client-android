package com.practice.client.feature.project.impl.auth.di

import com.practice.client.core.project.dagger.Scopes
import com.practice.client.feature.project.auth.LoginUseCase
import com.practice.client.feature.project.auth.repo.LoginRepository
import com.practice.client.feature.project.auth.repo.UserRepository
import dagger.Module
import dagger.Provides

@Module
object LoginUseCaseFactory {
    @Provides
    @Scopes.FragmentScope
    fun provideLoginUseCase(
        userRepository: UserRepository,
        loginRepository: LoginRepository
    ) = LoginUseCase(userRepository, loginRepository)
}

