package com.practice.client.core.project.base.presentation.viewmodel

import com.practice.client.core.project.base.presentation.mvvm.action.ScreenAction
import com.practice.client.core.project.base.presentation.mvvm.event.ScreenEvent
import com.practice.client.core.project.base.presentation.mvvm.state.ScreenState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class MvvmViewModel<T : ScreenState> : BaseViewModel() {
    protected val mutableScreenState by lazy { MutableStateFlow(createInitialState()) }
    val screenState by lazy { mutableScreenState.asStateFlow() }

    protected val mutableScreenEvents = MutableSharedFlow<ScreenEvent>()
    val screenEvents = mutableScreenEvents.asSharedFlow()

    abstract fun submitAction(action: ScreenAction)
    abstract fun createInitialState(): T
}

