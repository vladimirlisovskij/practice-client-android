package com.practice.client.feature.individual.profile.di

import com.practice.client.core.project.base.domain.UserDataContainer
import com.practice.client.core.project.dagger.Scopes
import com.practice.client.core.project.utils.di.qualifiers.CoroutineAppScope
import com.practice.client.feature.individual.profile.domain.ProfileInteractor
import com.practice.client.feature.individual.profile.domain.ProfileRepository
import com.practice.client.feature.project.auth.RunAuthorizedInteractor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import kotlinx.coroutines.CoroutineScope

@Module(includes = [ProfileDataContainers::class])
object ProfileInteractorFactory {
    @Provides
    @Scopes.AppScope
    fun provideProfileInteractorFactory(
        profileRepository: ProfileRepository,
        runAuthorizedInteractor: RunAuthorizedInteractor,
        @CoroutineAppScope scope: CoroutineScope,
    ) = ProfileInteractor(
        profileRepository,
        runAuthorizedInteractor,
        scope
    )
}

@Module
abstract class ProfileDataContainers {
    @Binds
    @IntoSet
    abstract fun bindProfileInteractor(profileInteractor: ProfileInteractor): UserDataContainer
}

