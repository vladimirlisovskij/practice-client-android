package com.practice.client.feature.individual.profile.domain

import com.practice.client.core.project.base.domain.DataState
import com.practice.client.core.project.base.domain.UserDataContainer
import com.practice.client.feature.project.auth.RunAuthorizedInteractor
import com.practice.network_entities.endpoints.api.profile.ProfileGet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileInteractor(
    private val profileRepository: ProfileRepository,
    private val runAuthorizedInteractor: RunAuthorizedInteractor,
    private val appScope: CoroutineScope
): UserDataContainer {
    private val mutableProfileDataflow = MutableStateFlow<DataState<ProfileGet.Response.IndividualResponse>>(DataState.Empty())
    val profileDataFlow = mutableProfileDataflow.asStateFlow()

    fun makeRequest() {
        val curValue = mutableProfileDataflow.value
        if (curValue is DataState.Loading || curValue is DataState.Success) return
        mutableProfileDataflow.value = DataState.Loading()
        appScope.launch {
            mutableProfileDataflow.value = try {
                val profile = runAuthorizedInteractor.invoke(profileRepository::getProfile)
                DataState.Success(profile)
            } catch (error: Exception) {
                DataState.Error(error)
            }
        }
    }

    override suspend fun clearUserData() {
        mutableProfileDataflow.value = DataState.Empty()
    }
}

