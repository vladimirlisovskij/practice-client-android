package com.practice.client.feature.individual.auth.registration.mvvm

import com.practice.client.core.project.base.presentation.mvvm.action.ScreenAction

sealed interface IndividualRegistrationScreenAction : ScreenAction {
    data class NickNameChanged(val nickName: String): IndividualRegistrationScreenAction
    data class FullNameChanged(val fullName: String): IndividualRegistrationScreenAction
    data class PasswordChanged(val password: String): IndividualRegistrationScreenAction
}