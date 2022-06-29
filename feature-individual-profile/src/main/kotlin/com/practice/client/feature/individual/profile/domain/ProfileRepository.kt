package com.practice.client.feature.individual.profile.domain

import com.practice.client.core.project.base.domain.DefaultRequestHeaders
import com.practice.network_entities.endpoints.api.profile.ProfileGet

interface ProfileRepository {
    suspend fun getProfile(defaultRequestHeaders: DefaultRequestHeaders): ProfileGet.Response.IndividualResponse
}