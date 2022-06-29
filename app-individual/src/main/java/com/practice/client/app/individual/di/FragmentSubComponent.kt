package com.practice.client.app.individual.di

import com.practice.client.core.project.dagger.Scopes
import com.practice.client.feature.individual.auth.di.IndividualAuthUseCaseFactory
import com.practice.client.feature.individual.auth.di.IndividualAuthViewModelFactory
import com.practice.client.feature.individual.auth.registration.IndividualRegistrationDependencies
import com.practice.client.feature.individual.profile.di.ProfileViewModelFactory
import com.practice.client.feature.individual.profile.presentation.ProfileDependencies
import com.practice.client.feature.project.impl.auth.di.LoginUseCaseFactory
import com.practice.client.feature.project.login.LoginDependencies
import com.practice.client.feature.project.login.di.LoginViewModelFactory
import dagger.Subcomponent

@Subcomponent(
    modules = [
        IndividualAuthViewModelFactory::class,
        IndividualAuthUseCaseFactory::class,
        LoginUseCaseFactory::class,
        LoginViewModelFactory::class,
        ProfileViewModelFactory::class
    ]
)
@Scopes.FragmentScope
interface FragmentSubComponent : LoginDependencies, IndividualRegistrationDependencies, ProfileDependencies {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentSubComponent
    }
}