package com.practice.client.feature.individual.profile.presentation

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.practice.client.core.project.base.domain.DataState
import com.practice.client.core.project.base.presentation.mvvm.action.DialogButtonPressed
import com.practice.client.core.project.base.presentation.mvvm.action.RefreshScreenAction
import com.practice.client.core.project.base.presentation.mvvm.action.ScreenAction
import com.practice.client.core.project.base.presentation.mvvm.state.DialogState
import com.practice.client.core.project.base.presentation.utils.PresentationExt.mapToDialogState
import com.practice.client.core.project.base.presentation.viewmodel.AuthorizedViewModel
import com.practice.client.feature.individual.profile.domain.ProfileInteractor
import com.practice.client.feature.individual.profile.presentation.mvvm.LogOutAction
import com.practice.client.feature.individual.profile.presentation.mvvm.ProfileInfoState
import com.practice.client.feature.individual.profile.presentation.mvvm.ProfileState
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val context: Context,
    private val profileInteractor: ProfileInteractor
) : AuthorizedViewModel<ProfileState>() {
    init {
        initListeners()
        makeProfileRequest()
    }

    override fun submitAction(action: ScreenAction) {
        when (action) {
            RefreshScreenAction -> makeProfileRequest()
            LogOutAction -> unAuthorize()
            is DialogButtonPressed -> onDialogButtonPressed(action)
        }
    }

    override fun createInitialState(): ProfileState {
        return ProfileState(
            dialogState = null,
            profileState = ProfileInfoState.Loading
        )
    }

    private fun initListeners() {
        viewModelScope.launch {
            profileInteractor.profileDataFlow.collect { profileState ->
                when (profileState) {
                    is DataState.Error -> {
                        mutableScreenState.update { state ->
                            state.copy(
                                profileState = ProfileInfoState.Error,
                                dialogState = profileState.exception.mapToDialogState(context)
                            )
                        }
                    }

                    is DataState.Loading -> {
                        mutableScreenState.update { it.copy(profileState = ProfileInfoState.Loading) }
                    }

                    is DataState.Success -> {
                        mutableScreenState.update { it.copy(profileState = ProfileInfoState.Success(profileState.value)) }
                    }

                    is DataState.Empty -> {
                        // Do nothing
                    }
                }
            }
        }
    }

    private fun makeProfileRequest() {
        profileInteractor.makeRequest()
    }

    private fun onDialogButtonPressed(action: DialogButtonPressed) {
        if (action.dialogId == DialogState.UnAuthorizedDialog) {
            unAuthorize()
        } else {
            mutableScreenState.update { it.copy(dialogState = null) }
        }
    }
}

