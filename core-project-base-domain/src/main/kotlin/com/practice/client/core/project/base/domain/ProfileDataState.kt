package com.practice.client.core.project.base.domain

sealed interface DataState<T : Any> {
    class Empty<T : Any> : DataState<T>
    class Loading<T : Any> : DataState<T>
    data class Error<T : Any>(val exception: Exception) : DataState<T>
    data class Success<T : Any>(val value: T) : DataState<T>
}