package com.practice.client.feature.project.login.mvvm

import com.practice.client.core.project.base.presentation.mvvm.state.ProgressScreenState
import com.practice.client.core.project.base.presentation.mvvm.state.DialogState

data class LoginProgressScreenState(
    override val isShowProgress: Boolean,
    override val dialogState: DialogState?,
    val nickname: String,
    val password: String
) : ProgressScreenState