package com.practice.client.core.project.base.presentation.utils

import android.content.Context
import android.content.DialogInterface
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practice.client.core.project.base.domain.exceptions.BadRequestException
import com.practice.client.core.project.base.domain.exceptions.ConnectionToNetworkException
import com.practice.client.core.project.base.domain.exceptions.StatusCodeException
import com.practice.client.core.project.base.presentation.mvvm.action.DialogButtonPressed
import com.practice.client.core.project.base.presentation.mvvm.state.DialogState
import com.practice.network_entities.response_status.ErrorType
import com.practice.network_entities.response_status.ResponseStatus

object PresentationExt {
    fun DialogState.createDialog(context: Context, block: (DialogButtonPressed) -> Unit): AlertDialog {
        val buttonListener = DialogInterface.OnClickListener { _, button ->
            when (button) {
                DialogInterface.BUTTON_POSITIVE -> block.invoke(
                    DialogButtonPressed(
                        DialogButtonPressed.ButtonType.POSITIVE,
                        dialogId
                    )
                )

                DialogInterface.BUTTON_NEGATIVE -> block.invoke(
                    DialogButtonPressed(
                        DialogButtonPressed.ButtonType.NEGATIVE,
                        dialogId
                    )
                )
            }
        }

        val builder = AlertDialog.Builder(context)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(positiveText, buttonListener)

        negativeText?.let { builder.setNegativeButton(it, buttonListener) }
        title?.let(builder::setTitle)
        return builder.show()
    }

    fun EditText.withoutTextWatcher(textWatcher: TextWatcher, block: EditText.() -> Unit) {
        removeTextChangedListener(textWatcher)
        block.invoke(this)
        addTextChangedListener(textWatcher)
    }

    fun EditText.setTextWithSelection(string: String) {
        val oldSelection = selectionStart
        setText(string)
        setSelection(oldSelection)
    }

    inline fun <reified VM : ViewModel> Fragment.providedViewModels(
        crossinline provider: () -> VM
    ) = viewModels<VM> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return provider.invoke() as T
            }
        }
    }

    fun Context.getInternalError() = DialogState(
        title = getString(com.practice.client.core.project.design.R.string.error_internal_title),
        message = getString(com.practice.client.core.project.design.R.string.error_internal_message),
        positiveText = getString(com.practice.client.core.project.design.R.string.action_understand),
        dialogId = DialogState.DefaultDialog
    )

    fun Context.getDefaultError(message: String) = DialogState(
        title = getString(com.practice.client.core.project.design.R.string.error_request_title),
        message = message,
        positiveText = getString(com.practice.client.core.project.design.R.string.action_understand),
        dialogId = DialogState.DefaultDialog
    )

    fun Context.getConnectionError() = DialogState(
        title = getString(com.practice.client.core.project.design.R.string.error_network_title),
        message = getString(com.practice.client.core.project.design.R.string.error_network_message),
        positiveText = getString(com.practice.client.core.project.design.R.string.action_understand),
        dialogId = DialogState.DefaultDialog
    )

    fun Context.getClientError() = DialogState(
        title = getString(com.practice.client.core.project.design.R.string.error_client_title),
        message = getString(com.practice.client.core.project.design.R.string.error_client_message),
        positiveText = getString(com.practice.client.core.project.design.R.string.action_understand),
        dialogId = DialogState.DefaultDialog
    )

    fun Context.getUnAuthorizedError() = DialogState(
        title = getString(com.practice.client.core.project.design.R.string.error_client_title),
        message = getString(com.practice.client.core.project.design.R.string.error_client_message),
        positiveText = getString(com.practice.client.core.project.design.R.string.action_understand),
        dialogId = DialogState.UnAuthorizedDialog
    )

    fun Exception.mapToDialogState(context: Context) = when (this) {
        is BadRequestException -> when {
            errorBody.message != null -> context.getDefaultError(errorBody.message!!)

            else -> context.getInternalError()
        }

        is ConnectionToNetworkException -> context.getConnectionError()

        is StatusCodeException -> {
            when(statusCode) {
                ResponseStatus.UNAUTHORIZED -> context.getUnAuthorizedError()

                else -> context.getInternalError()
            }
        }

        else -> context.getClientError()
    }
}