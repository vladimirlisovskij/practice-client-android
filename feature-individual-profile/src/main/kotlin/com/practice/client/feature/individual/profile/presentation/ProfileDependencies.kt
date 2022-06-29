package com.practice.client.feature.individual.profile.presentation

import kotlin.properties.Delegates

interface ProfileDependencies {
    fun getProfileViewModel(): ProfileViewModel

    companion object {
        var creator: (() -> ProfileDependencies) by Delegates.notNull()
    }
}