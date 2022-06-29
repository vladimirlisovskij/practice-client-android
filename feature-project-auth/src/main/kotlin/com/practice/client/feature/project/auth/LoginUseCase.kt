package com.practice.client.feature.project.auth

import com.practice.client.feature.project.auth.repo.LoginRepository
import com.practice.client.feature.project.auth.repo.UserRepository
import com.practice.network_entities.endpoints.api.auth.Login

class LoginUseCase(
    private val userRepository: UserRepository,
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(userDetails: Login.Body.LoginDetails) {
        val token = loginRepository.login(
            userRepository.getDefaultHeaders(),
            userDetails,
            userRepository.getUserType()
        )

        userRepository.setUserJwt(token)
        userRepository.setUserDetails(userDetails)
    }
}


