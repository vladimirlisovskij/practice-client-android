package com.practice.client.feature.individual.auth.registration

import kotlin.properties.Delegates

interface IndividualRegistrationDependencies {
    fun getIndividualRegistrationViewModel(): IndividualRegistrationViewModel

    companion object {
        var creator by Delegates.notNull<() -> IndividualRegistrationDependencies>()
    }
}