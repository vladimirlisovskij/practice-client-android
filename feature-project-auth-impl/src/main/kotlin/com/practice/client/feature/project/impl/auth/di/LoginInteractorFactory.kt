package com.practice.client.feature.project.impl.auth.di

import com.practice.client.core.project.dagger.Scopes
import com.practice.client.feature.project.auth.RunAuthorizedInteractor
import com.practice.client.feature.project.auth.repo.LoginRepository
import com.practice.client.feature.project.auth.repo.UserRepository
import dagger.Module
import dagger.Provides

@Module
object LoginInteractorFactory {
    @Provides
    @Scopes.AppScope
    fun provideRunAuthorizedInteractor(
        userRepository: UserRepository,
        loginRepository: LoginRepository
    ) = RunAuthorizedInteractor(userRepository, loginRepository)
}