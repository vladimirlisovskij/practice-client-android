package com.practice.client.feature.project.login

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.practice.client.core.project.base.presentation.viewmodel.MvvmViewModel
import com.practice.client.core.project.base.presentation.mvvm.action.DialogButtonPressed
import com.practice.client.core.project.base.presentation.mvvm.action.PrimaryButtonPressed
import com.practice.client.core.project.base.presentation.mvvm.action.ScreenAction
import com.practice.client.core.project.base.presentation.mvvm.event.FinishActivityEvent
import com.practice.client.core.project.base.presentation.utils.PresentationExt.mapToDialogState
import com.practice.client.feature.project.auth.LoginUseCase
import com.practice.client.feature.project.login.mvvm.LoginScreenAction
import com.practice.client.feature.project.login.mvvm.LoginProgressScreenState
import com.practice.network_entities.endpoints.api.auth.Login
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val context: Context
) : MvvmViewModel<LoginProgressScreenState>() {
    override fun submitAction(action: ScreenAction) {
        when (action) {
            is LoginScreenAction.NicknameChanged -> nicknameChanged(action.newNickname)
            is LoginScreenAction.PasswordChanged -> passwordChanged(action.newPassword)
            is PrimaryButtonPressed -> onLoginButtonPressed()
            is DialogButtonPressed -> closeDialog()
        }
    }

    override fun createInitialState(): LoginProgressScreenState {
        return LoginProgressScreenState(
            isShowProgress = false,
            dialogState = null,
            nickname = "",
            password = ""
        )
    }

    private fun nicknameChanged(newNickname: String) {
        mutableScreenState.update { it.copy(nickname = newNickname) }
    }

    private fun passwordChanged(newPassword: String) {
        mutableScreenState.update { it.copy(password = newPassword) }
    }

    private fun closeDialog() {
        mutableScreenState.update { it.copy(dialogState = null) }
    }

    private fun onLoginButtonPressed() {
        val screenValue = mutableScreenState.value
        val nickname = screenValue.nickname
        val password = screenValue.password
        viewModelScope.launch {
            mutableScreenState.update { it.copy(isShowProgress = true) }
            try {
                loginUseCase.invoke(
                    Login.Body.LoginDetails(
                        nickName = nickname,
                        password = password
                    )
                )

                mutableScreenEvents.emit(OnAuthorizeEvent)
            } catch (error: Exception) {
                mutableScreenState.update { state ->
                    state.copy(
                        dialogState = error.mapToDialogState(context),
                        isShowProgress = false
                    )
                }
            }
        }
    }
}

