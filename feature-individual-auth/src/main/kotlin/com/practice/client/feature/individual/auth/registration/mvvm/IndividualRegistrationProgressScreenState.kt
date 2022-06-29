package com.practice.client.feature.individual.auth.registration.mvvm

import com.practice.client.core.project.base.presentation.mvvm.state.DialogState
import com.practice.client.core.project.base.presentation.mvvm.state.ProgressScreenState

data class IndividualRegistrationProgressScreenState(
    override val isShowProgress: Boolean,
    override val dialogState: DialogState?,
    val nickName: String,
    val fullName: String,
    val password: String
): ProgressScreenState