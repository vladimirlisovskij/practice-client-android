package com.practice.client.app.individual.di

import com.practice.network_entities.params.UserType
import dagger.Module
import dagger.Provides

@Module
object UserTypeFactory {
    @Provides
    fun provideIndividualUserType() = UserType.INDIVIDUAL
}