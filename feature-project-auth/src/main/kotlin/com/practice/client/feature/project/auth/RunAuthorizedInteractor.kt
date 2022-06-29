package com.practice.client.feature.project.auth

import com.practice.client.core.project.base.domain.DefaultRequestHeaders
import com.practice.client.core.project.base.domain.exceptions.BadRequestException
import com.practice.client.core.project.base.domain.exceptions.StatusCodeException
import com.practice.client.feature.project.auth.repo.LoginRepository
import com.practice.client.feature.project.auth.repo.UserRepository
import com.practice.network_entities.response_status.ResponseStatus
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class RunAuthorizedInteractor(
    private val userRepository: UserRepository,
    private val loginRepository: LoginRepository
) {
    private val refreshRequestMutex = Mutex()

    suspend operator fun <T: Any> invoke(
        block: suspend (DefaultRequestHeaders) -> T
    ): T {
        val headersBeforeRefreshRequest = userRepository.getDefaultHeaders()
        val initRequest = runCatching { block(headersBeforeRefreshRequest) }
        if (initRequest.isSuccess) {
            return initRequest.getOrNull()!!
        }

        val exception = initRequest.exceptionOrNull()!!
        if (exception !is StatusCodeException || exception.statusCode != ResponseStatus.UNAUTHORIZED) {
            throw exception
        }

        val headersAfterRefreshRequest = refreshRequestMutex.withLock {
            val currentHeaders = userRepository.getDefaultHeaders()
            if (headersBeforeRefreshRequest.jwt == currentHeaders.jwt) {
                val token = loginRepository.login(
                    headersBeforeRefreshRequest,
                    userRepository.getUserDetails(),
                    userRepository.getUserType()
                )

                userRepository.setUserJwt(token)
                userRepository.getDefaultHeaders()
            } else {
                currentHeaders
            }
        }

        return block(headersAfterRefreshRequest)
    }
}