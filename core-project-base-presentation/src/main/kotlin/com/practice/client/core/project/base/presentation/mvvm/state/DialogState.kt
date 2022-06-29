package com.practice.client.core.project.base.presentation.mvvm.state

data class DialogState(
    val message: String,
    val positiveText: String,
    val title: String? = null,
    val negativeText: String? = null,
    val dialogId: Int
) {
    companion object {
        const val UnAuthorizedDialog = 1
        const val DefaultDialog = 2
    }
}
