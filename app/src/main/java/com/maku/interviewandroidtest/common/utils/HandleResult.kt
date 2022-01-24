package com.maku.interviewandroidtest.common.utils


sealed class HandleResult<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T): HandleResult<T>(data)
    class RoomSuccess<T>(message: String?, data: T? = null): HandleResult<T>(data, message)
    class Error<T>(message: String?, data: T? = null): HandleResult<T>(data, message)
    class Loading<T>: HandleResult<T>()
}