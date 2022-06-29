package com.practice.client.core.project.base.presentation.mvvm.action

data class DialogButtonPressed(
    val type: ButtonType,
    val dialogId: Int
): ScreenAction {
    enum class ButtonType { POSITIVE, NEGATIVE }
}

