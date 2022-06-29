package com.practice.client.feature.individual.auth.registration

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.practice.client.core.project.base.presentation.viewmodel.MvvmViewModel
import com.practice.client.core.project.base.presentation.mvvm.action.DialogButtonPressed
import com.practice.client.core.project.base.presentation.mvvm.action.PrimaryButtonPressed
import com.practice.client.core.project.base.presentation.mvvm.action.ScreenAction
import com.practice.client.core.project.base.presentation.mvvm.event.FinishActivityEvent
import com.practice.client.core.project.base.presentation.utils.PresentationExt.mapToDialogState
import com.practice.client.feature.individual.auth.domain.IndividualRegistrationUseCase
import com.practice.client.feature.individual.auth.registration.mvvm.IndividualRegistrationScreenAction
import com.practice.client.feature.individual.auth.registration.mvvm.IndividualRegistrationProgressScreenState
import com.practice.client.feature.project.login.OnAuthorizeEvent
import com.practice.network_entities.endpoints.api.auth.Registration
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IndividualRegistrationViewModel(
    private val context: Context,
    private val registrationUseCase: IndividualRegistrationUseCase
): MvvmViewModel<IndividualRegistrationProgressScreenState>() {
    override fun submitAction(action: ScreenAction) {
        when(action) {
            PrimaryButtonPressed -> onRegistration()
            is IndividualRegistrationScreenAction.NickNameChanged -> onNickNameChanged(action.nickName)
            is IndividualRegistrationScreenAction.FullNameChanged -> onFullNameChanged(action.fullName)
            is IndividualRegistrationScreenAction.PasswordChanged -> onPasswordChanged(action.password)
            is DialogButtonPressed -> onDialogClosed()
        }
    }

    override fun createInitialState(): IndividualRegistrationProgressScreenState {
        return IndividualRegistrationProgressScreenState(
            isShowProgress = false,
            dialogState = null,
            nickName = "",
            fullName = "",
            password = ""
        )
    }

    private fun onRegistration() {
        val curState = mutableScreenState.value
        viewModelScope.launch {
            try {
                mutableScreenState.update { it.copy(isShowProgress = true) }
                registrationUseCase.invoke(
                    Registration.Body.Individual(
                        nickName = curState.nickName,
                        fullName = curState.fullName,
                        password = curState.password
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

    private fun onDialogClosed() {
        mutableScreenState.update { it.copy(dialogState = null) }
    }

    private fun onNickNameChanged(newNickname: String) {
        mutableScreenState.update { it.copy(nickName = newNickname) }
    }

    private fun onFullNameChanged(newFullName: String) {
        mutableScreenState.update { it.copy(fullName = newFullName) }
    }

    private fun onPasswordChanged(newPassword: String) {
        mutableScreenState.update { it.copy(password = newPassword) }
    }
}