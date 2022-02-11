package com.revolhope.domain.feature.user.exceptions

sealed class UserException : Throwable()

object UserNotFoundError : UserException()
sealed class UserUpdateError : UserException() {
    object InvalidInput : UserUpdateError()
    object WhileUpdating : UserUpdateError()
}
