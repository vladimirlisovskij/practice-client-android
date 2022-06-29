package com.practice.client.feature.individual.profile.data

import com.practice.client.core.project.base.domain.DefaultRequestHeaders
import com.practice.client.core.project.network.NetworkExt.baseHeadersConfig
import com.practice.client.core.project.network.NetworkExt.baseUrlConfig
import com.practice.client.core.project.network.NetworkExt.requestProcessing
import com.practice.client.feature.individual.profile.domain.ProfileRepository
import com.practice.network_entities.endpoints.api.profile.ProfileGet
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProfileRepositoryImpl(
    private val dispatcherIO: CoroutineDispatcher,
    private val httpClient: HttpClient
) : ProfileRepository {
    override suspend fun getProfile(defaultRequestHeaders: DefaultRequestHeaders): ProfileGet.Response.IndividualResponse {
        return withContext(dispatcherIO) {
            httpClient.requestProcessing {
                headers {
                    baseHeadersConfig(defaultRequestHeaders)
                }

                url {
                    baseUrlConfig(ProfileGet.ENDPOINT)
                }
            }
        }
    }
}