package com.practice.client.feature.individual.auth.di

import android.content.Context
import com.practice.client.core.project.dagger.Scopes
import com.practice.client.core.project.utils.di.qualifiers.CoroutineDispatchersQualifier
import com.practice.client.core.project.utils.di.qualifiers.CoroutineDispatchersType
import com.practice.client.feature.individual.auth.data.RegistrationRepositoryImpl
import com.practice.client.feature.individual.auth.domain.IndividualRegistrationUseCase
import com.practice.client.feature.individual.auth.domain.RegistrationRepository
import com.practice.client.feature.individual.auth.registration.IndividualRegistrationViewModel
import com.practice.client.feature.project.auth.repo.UserRepository
import com.practice.client.feature.project.login.di.LoginViewModelFactory
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import kotlinx.coroutines.CoroutineDispatcher

@Module
object IndividualAuthViewModelFactory {
    @Provides
    @Scopes.FragmentScope
    fun provideIndividualRegistrationViewModel(
        context: Context,
        individualRegistrationUseCase: IndividualRegistrationUseCase
    ) = IndividualRegistrationViewModel(context, individualRegistrationUseCase)
}

@Module
object IndividualAuthUseCaseFactory {
    @Provides
    @Scopes.FragmentScope
    fun provideIndividualRegistrationUseCase(
        userRepository: UserRepository,
        registrationRepository: RegistrationRepository
    ) = IndividualRegistrationUseCase(userRepository, registrationRepository)
}

@Module
object IndividualAuthRepositoryFactory {
    @Provides
    @Scopes.AppScope
    fun provideIndividualRegistrationRepository(
        @CoroutineDispatchersQualifier(CoroutineDispatchersType.IO) coroutineDispatcher: CoroutineDispatcher,
        httpClient: HttpClient
    ): RegistrationRepository = RegistrationRepositoryImpl(coroutineDispatcher, httpClient)
}