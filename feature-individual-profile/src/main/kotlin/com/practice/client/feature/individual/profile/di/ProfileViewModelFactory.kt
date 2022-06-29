package com.practice.client.feature.individual.profile.di

import android.content.Context
import com.practice.client.core.project.dagger.Scopes
import com.practice.client.feature.individual.profile.domain.ProfileInteractor
import com.practice.client.feature.individual.profile.presentation.ProfileViewModel
import dagger.Module
import dagger.Provides

@Module
object ProfileViewModelFactory {
    @Provides
    @Scopes.FragmentScope
    fun provideProfileViewModel(
        context: Context,
        profileInteractor: ProfileInteractor
    ) = ProfileViewModel(
        context,
        profileInteractor
    )
}