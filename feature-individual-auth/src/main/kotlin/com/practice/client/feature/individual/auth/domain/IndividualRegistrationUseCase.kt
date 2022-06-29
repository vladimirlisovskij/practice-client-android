package com.practice.client.feature.individual.auth.domain

import com.practice.client.feature.project.auth.repo.UserRepository
import com.practice.network_entities.endpoints.api.auth.Registration

class IndividualRegistrationUseCase(
    private val userRepository: UserRepository,
    private val registrationRepository: RegistrationRepository
) {
    suspend operator fun invoke(registrationData: Registration.Body.Individual) {
        val token = registrationRepository.registerIndividual(
            userRepository.getDefaultHeaders(),
            registrationData
        )

        userRepository.setUserJwt(token)
    }
}

