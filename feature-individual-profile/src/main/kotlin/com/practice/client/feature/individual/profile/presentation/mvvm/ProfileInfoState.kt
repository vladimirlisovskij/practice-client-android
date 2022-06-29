package com.practice.client.feature.individual.profile.presentation.mvvm

import com.practice.network_entities.endpoints.api.profile.ProfileGet

sealed interface ProfileInfoState {
    object Error : ProfileInfoState
    object Loading : ProfileInfoState
    data class Success(val data: ProfileGet.Response.IndividualResponse) : ProfileInfoState
}