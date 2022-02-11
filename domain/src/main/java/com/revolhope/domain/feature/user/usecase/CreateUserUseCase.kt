package com.revolhope.domain.feature.user.usecase

import com.revolhope.domain.common.time.utils.now
import com.revolhope.domain.common.usecase.UseCase
import com.revolhope.domain.feature.user.exceptions.UserUpdateError
import com.revolhope.domain.feature.user.model.User
import com.revolhope.domain.feature.user.repository.UserRepository
import java.util.*
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val repository: UserRepository) :
    UseCase<String, User> {

    override suspend fun execute(id: String, params: String): User {
        if (params.isBlank()) throw UserUpdateError.InvalidInput
        val user = User(
            id = UUID.randomUUID().toString(),
            name = params,
            createdOn = now(),
            lastSessionOn = now()
        )
        return repository.register(user)
    }

    companion object {
        const val ID = "UC-ID-CreateUserUseCase"
    }
}