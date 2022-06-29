package com.practice.client.core.project.base.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.practice.client.core.project.base.presentation.mvvm.event.LogOutEvent
import com.practice.client.core.project.base.presentation.mvvm.state.ScreenState
import kotlinx.coroutines.launch

abstract class AuthorizedViewModel<T: ScreenState>: MvvmViewModel<T>(){
    protected open fun unAuthorize() {
        viewModelScope.launch { mutableScreenEvents.emit(LogOutEvent) }
    }
}