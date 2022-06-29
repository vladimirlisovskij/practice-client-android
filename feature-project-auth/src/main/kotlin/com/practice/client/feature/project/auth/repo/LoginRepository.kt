package com.practice.client.feature.project.auth.repo

import com.practice.client.core.project.base.domain.DefaultRequestHeaders
import com.practice.network_entities.endpoints.api.auth.Login
import com.practice.network_entities.params.UserType

interface LoginRepository {
    suspend fun login(
        defaultRequestHeaders: DefaultRequestHeaders,
        userDetails: Login.Body.LoginDetails,
        userType: UserType,
    ): String
}