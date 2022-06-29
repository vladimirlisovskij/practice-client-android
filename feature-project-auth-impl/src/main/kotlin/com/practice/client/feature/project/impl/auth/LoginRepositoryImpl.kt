package com.practice.client.feature.project.impl.auth

import com.practice.client.core.project.base.domain.DefaultRequestHeaders
import com.practice.client.core.project.network.NetworkExt.addJsonContentType
import com.practice.client.core.project.network.NetworkExt.baseHeadersConfig
import com.practice.client.core.project.network.NetworkExt.baseUrlConfig
import com.practice.client.core.project.network.NetworkExt.requestProcessing
import com.practice.client.feature.project.auth.repo.LoginRepository
import com.practice.network_entities.endpoints.api.auth.Login
import com.practice.network_entities.params.UserType
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginRepositoryImpl(
    private val dispatcherIO: CoroutineDispatcher,
    private val httpClient: HttpClient
): LoginRepository {
    override suspend fun login(
        defaultRequestHeaders: DefaultRequestHeaders,
        userDetails: Login.Body.LoginDetails,
        userType: UserType
    ): String {
        return withContext(dispatcherIO) {
            httpClient.requestProcessing {
                method = HttpMethod.Post
                headers {
                    baseHeadersConfig(defaultRequestHeaders)
                    addJsonContentType()
                }

                url {
                    baseUrlConfig(Login.ENDPOINT)
                    parameters.append(UserType::class.simpleName!!, userType.name)
                }

                setBody(userDetails)
            }
        }
    }
}