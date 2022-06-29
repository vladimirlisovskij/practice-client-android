package com.practice.client.feature.individual.auth.domain

import com.practice.client.core.project.base.domain.DefaultRequestHeaders
import com.practice.network_entities.endpoints.api.auth.Registration

interface RegistrationRepository {
    suspend fun registerIndividual(
        defaultRequestHeaders: DefaultRequestHeaders,
        registrationData: Registration.Body.Individual
    ): String
}