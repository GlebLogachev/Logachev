package com.glogachev.developerslife.domain

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    object Error : NetworkResult<Nothing>()
}