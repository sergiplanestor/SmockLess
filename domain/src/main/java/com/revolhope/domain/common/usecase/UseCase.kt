package com.revolhope.domain.common.usecase

import com.revolhope.domain.common.time.model.TimeModel
import com.revolhope.domain.common.time.utils.minus
import com.revolhope.domain.common.time.utils.now
import com.revolhope.domain.common.time.utils.plus
import com.revolhope.domain.common.time.utils.timelapseSecs
import kotlinx.coroutines.withTimeout

interface UseCase<Params, Data> {

    suspend operator fun invoke(request: Request<Params>): Result<Data> {
        val result: Result<Data> = try {
            val data = withTimeout(timeMillis = request.timeout.millis) {
                execute(request.id, request.params)
            }
            val timestamp = now()
            Result.Success(
                fromRequestId = request.id,
                timestamp = timestamp,
                elapsed = timestamp - request.timestamp,
                data = data
            )
        } catch (throwable: Throwable) {
            val timestamp = now()
            Result.Error(
                fromRequestId = request.id,
                timestamp = timestamp,
                elapsed = timestamp - request.timestamp,
                cause = throwable
            )
        }
        return result
    }

    suspend fun execute(id: String, params: Params): Data

    data class Request<out T>(
        val id: String,
        val params: T,
        val timestamp: TimeModel = now(),
        val timeout: TimeModel = timestamp + 20.timelapseSecs,
    )

    sealed class Result<out T>(
        open val fromRequestId: String,
        open val timestamp: TimeModel = now(),
        open val elapsed: TimeModel,
    ) {
        data class Success<out T>(
            override val fromRequestId: String,
            override val timestamp: TimeModel = now(),
            override val elapsed: TimeModel,
            val data: T,
        ) : Result<T>(fromRequestId, timestamp, timestamp)

        data class Error<out T>(
            override val fromRequestId: String,
            override val timestamp: TimeModel = now(),
            override val elapsed: TimeModel,
            val cause: Throwable? = null,
        ) : Result<T>(fromRequestId, timestamp, timestamp)
    }
}