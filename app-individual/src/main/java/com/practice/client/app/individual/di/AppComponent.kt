package com.practice.client.app.individual.di

import android.content.Context
import com.practice.client.core.project.base.domain.UserDataContainer
import com.practice.client.core.project.dagger.Scopes
import com.practice.client.core.project.network.NetworkFactory
import com.practice.client.core.project.utils.di.UtilsFactory
import com.practice.client.core.project.utils.di.qualifiers.CoroutineAppScope
import com.practice.client.feature.individual.auth.di.IndividualAuthRepositoryFactory
import com.practice.client.feature.individual.profile.di.ProfileInteractorFactory
import com.practice.client.feature.individual.profile.di.ProfileRepositoryFactory
import com.practice.client.feature.individual.profile.domain.ProfileInteractor
import com.practice.client.feature.project.auth.repo.UserRepository
import com.practice.client.feature.project.impl.auth.di.AuthRepositoryFactory
import com.practice.client.feature.project.impl.auth.di.LoginInteractorFactory
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.CoroutineScope

@Component(
    modules = [
        UtilsFactory::class,
        NetworkFactory::class,
        UserTypeFactory::class,
        AuthRepositoryFactory::class,
        IndividualAuthRepositoryFactory::class,
        LoginInteractorFactory::class,
        ProfileInteractorFactory::class,
        ProfileRepositoryFactory::class
    ]
)
@Scopes.AppScope
interface AppComponent {

    fun getUserRepository(): UserRepository

    fun getFragmentSubcomponent(): FragmentSubComponent.Factory

    fun getDataContainers(): Set<@JvmSuppressWildcards UserDataContainer>
    fun getProfileInteractor(): ProfileInteractor

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @CoroutineAppScope @BindsInstance scope: CoroutineScope,
        ): AppComponent
    }
}

