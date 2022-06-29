package com.practice.client.feature.project.login.mvvm

import com.practice.client.core.project.base.presentation.mvvm.action.ScreenAction

sealed interface LoginScreenAction : ScreenAction {
    class NicknameChanged(val newNickname: String) : LoginScreenAction
    class PasswordChanged(val newPassword: String) : LoginScreenAction
}