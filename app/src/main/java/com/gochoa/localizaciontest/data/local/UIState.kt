package com.gochoa.localizaciontest.data.local

sealed class UIState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): UIState<T>(data)
    class Error<T>(message: String?, data: T? = null): UIState<T>(data, message)
    class Loading<T>(message: String): UIState<T>(null, message)
}