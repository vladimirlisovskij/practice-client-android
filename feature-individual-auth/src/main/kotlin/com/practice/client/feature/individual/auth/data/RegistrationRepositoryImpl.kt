package com.practice.client.feature.individual.auth.data

import com.practice.client.core.project.base.domain.DefaultRequestHeaders
import com.practice.client.core.project.network.NetworkExt.addJsonContentType
import com.practice.client.core.project.network.NetworkExt.baseHeadersConfig
import com.practice.client.core.project.network.NetworkExt.baseUrlConfig
import com.practice.client.core.project.network.NetworkExt.requestProcessing
import com.practice.client.feature.individual.auth.domain.RegistrationRepository
import com.practice.network_entities.endpoints.api.auth.Registration
import com.practice.network_entities.params.UserType
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RegistrationRepositoryImpl(
    private val dispatcherIO: CoroutineDispatcher,
    private val httpClient: HttpClient
): RegistrationRepository {
    override suspend fun registerIndividual(
        defaultRequestHeaders: DefaultRequestHeaders,
        registrationData: Registration.Body.Individual
    ): String {
        return withContext(dispatcherIO) {
            httpClient.requestProcessing {
                method = HttpMethod.Post
                headers {
                    baseHeadersConfig(defaultRequestHeaders)
                    addJsonContentType()
                }

                url {
                    baseUrlConfig(Registration.ENDPOINT)
                    parameters.append(UserType::class.simpleName!!, UserType.INDIVIDUAL.name)
                }

                setBody(registrationData)
            }
        }
    }
}