package com.revolhope.data.feature.user.repositoryimpl

import com.revolhope.data.feature.user.datasource.UserDataSource
import com.revolhope.domain.feature.user.exceptions.UserUpdateError
import com.revolhope.domain.feature.user.exceptions.UserNotFoundError
import com.revolhope.data.feature.user.mapper.UserMapper
import com.revolhope.domain.common.time.utils.today
import com.revolhope.domain.feature.user.model.User
import com.revolhope.domain.feature.user.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: UserDataSource
) : UserRepository {

    override suspend fun fetch(): User =
        dataSource.fetchUser()?.let(UserMapper::mapDataToDomain) ?: throw UserNotFoundError

    override suspend fun register(user: User): User =
        update(user)

    override suspend fun update(user: User): User =
        dataSource.insertOrUpdateUser(user.let(UserMapper::mapDomainToData))
            ?.let(UserMapper::mapDataToDomain) ?: throw UserUpdateError.WhileUpdating

    override suspend fun updateSession(): User {
        return update(fetch().copy(lastSessionOn = today()))
    }
}