package com.practice.client.core.project.base.presentation.mvvm.state

interface ProgressScreenState: ScreenState {
    val isShowProgress: Boolean
    val dialogState: DialogState?
}