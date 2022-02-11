package com.revolhope.domain.feature.user.repository

import com.revolhope.domain.feature.user.model.User

interface UserRepository {
    suspend fun fetch(): User
    suspend fun register(user: User): User
    suspend fun update(user: User): User
    suspend fun updateSession(): User
}