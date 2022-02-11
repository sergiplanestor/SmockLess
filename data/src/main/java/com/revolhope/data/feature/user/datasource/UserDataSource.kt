package com.revolhope.data.feature.user.datasource

import com.revolhope.data.feature.user.dto.UserDto


interface UserDataSource {
    suspend fun fetchUser(): UserDto?
    suspend fun insertOrUpdateUser(user: UserDto): UserDto?

    companion object {
        const val PREF_USER = "smockless.prefs.user"
    }
}