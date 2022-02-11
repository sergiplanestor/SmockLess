package com.revolhope.domain.common.usecase

fun <T> UseCase.Result<T>.fold(onSuccess: (T) -> Unit = {}, onError: (Throwable?) -> Unit = {}) {
    when (this) {
        is UseCase.Result.Error -> onError(cause)
        is UseCase.Result.Success -> onSuccess(data)
    }
}

fun <T> UseCase.Result<T>.doOnSuccess(block: (T) -> Unit): UseCase.Result<T> =
    apply { fold(onSuccess = block) }

fun <T> UseCase.Result<T>.doOnError(block: (Throwable?) -> Unit): UseCase.Result<T> =
    apply { fold(onError = block) }

fun <T, R> UseCase.Result<T>.map(onSuccess: (T) -> R, onError: (Throwable?) -> R): R =
    when (this) {
        is UseCase.Result.Error -> onError(cause)
        is UseCase.Result.Success -> onSuccess(data)
    }