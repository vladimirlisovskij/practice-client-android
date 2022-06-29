package com.practice.client.feature.project.auth.repo

import com.practice.client.core.project.base.domain.DefaultRequestHeaders
import com.practice.client.core.project.base.domain.UserDataContainer
import com.practice.network_entities.endpoints.api.auth.Login
import com.practice.network_entities.params.UserType

interface UserRepository : UserDataContainer {
    suspend fun getUserDetails(): Login.Body.LoginDetails

    suspend fun setUserDetails(details: Login.Body.LoginDetails)

    suspend fun getDefaultHeaders(): DefaultRequestHeaders

    suspend fun setUserJwt(jwt: String?)

    fun getUserType(): UserType
}



