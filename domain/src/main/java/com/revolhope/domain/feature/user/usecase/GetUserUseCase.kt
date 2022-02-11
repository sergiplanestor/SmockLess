package com.revolhope.domain.feature.user.usecase

import com.revolhope.domain.common.usecase.UseCase
import com.revolhope.domain.feature.user.model.User
import com.revolhope.domain.feature.user.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: UserRepository) :
    UseCase<Unit, User> {

    override suspend fun execute(id: String, params: Unit): User =
        repository.fetch()

    companion object {
        const val ID = "UC-ID-GetUserUseCase"
    }
}