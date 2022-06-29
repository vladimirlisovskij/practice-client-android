package com.practice.client.feature.individual.profile.presentation.mvvm

import com.practice.client.core.project.base.presentation.mvvm.state.DialogState
import com.practice.client.core.project.base.presentation.mvvm.state.ScreenState

data class ProfileState(
    val dialogState: DialogState?,
    val profileState: ProfileInfoState
) : ScreenState