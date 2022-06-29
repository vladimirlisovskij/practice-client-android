package com.practice.client.feature.project.login.di

import android.content.Context
import com.practice.client.feature.project.auth.LoginUseCase
import com.practice.client.feature.project.login.LoginViewModel
import dagger.Module
import dagger.Provides

@Module
object LoginViewModelFactory {
    @Provides
    fun provideLoginViewModel(loginUseCase: LoginUseCase, context: Context) = LoginViewModel(loginUseCase, context)
}