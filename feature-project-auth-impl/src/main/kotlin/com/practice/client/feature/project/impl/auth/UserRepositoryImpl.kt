package com.practice.client.feature.project.impl.auth

import androidx.core.content.edit
import com.practice.client.core.project.base.domain.DefaultRequestHeaders
import com.practice.client.core.project.utils.prefs.SharedPreferencesService
import com.practice.client.feature.project.auth.repo.UserRepository
import com.practice.network_entities.endpoints.api.auth.Login
import com.practice.network_entities.headers.Locale
import com.practice.network_entities.headers.Version
import com.practice.network_entities.params.UserType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    prefsService: SharedPreferencesService,
    private val dispatcherIO: CoroutineDispatcher,
    private val userType: UserType
) : UserRepository {
    private var userDetails: Login.Body.LoginDetails? = null
    private var userJwt: String? = null

    private val userPrefs = prefsService.getPreferences(USER_AUTH_FILE)

    override suspend fun getUserDetails(): Login.Body.LoginDetails {
        if (userDetails == null) {
            userDetails = withContext(dispatcherIO) {
                Login.Body.LoginDetails(
                    nickName = userPrefs.getString(KEY_USER_LOGIN, "") ?: "",
                    password = userPrefs.getString(KEY_USER_PASSWORD, "") ?: ""
                )
            }
        }

        return userDetails!!
    }

    override suspend fun setUserDetails(details: Login.Body.LoginDetails) {
        userDetails = details
        withContext(dispatcherIO) {
            userPrefs.edit(commit = true) {
                putString(KEY_USER_LOGIN, details.nickName)
                putString(KEY_USER_PASSWORD, details.password)
            }
        }
    }

    override suspend fun getDefaultHeaders(): DefaultRequestHeaders {
        if (userJwt == null) {
            userJwt = withContext(dispatcherIO) { userPrefs.getString(KEY_USER_JWT, null) }
        }

        return DefaultRequestHeaders(
            version = Version.V1,
            locale = Locale.RU,
            jwt = userJwt
        )
    }

    override suspend fun setUserJwt(jwt: String?) {
        userJwt = jwt
        withContext(dispatcherIO) {
            userPrefs.edit(commit = true) {
                putString(KEY_USER_JWT, jwt)
            }
        }
    }

    override fun getUserType() = userType

    override suspend fun clearUserData() {
        userDetails = null
        userJwt = null
        withContext(dispatcherIO) {
            userPrefs.edit(commit = true) {
                clear()
            }
        }
    }

    companion object {
        private const val USER_AUTH_FILE = "UserRepositoryImpl.USER_AUTH_FILE"
        private const val KEY_USER_LOGIN = "UserRepositoryImpl.KEY_USER_LOGIN"
        private const val KEY_USER_PASSWORD = "UserRepositoryImpl.KEY_USER_PASSWORD"
        private const val KEY_USER_JWT = "UserRepositoryImpl.KEY_USER_JWT"
    }
}