package com.revolhope.data.common.preferences.datasource

import android.content.SharedPreferences
import com.revolhope.data.common.preferences.utils.getJson
import com.revolhope.data.common.preferences.utils.putJson
import com.revolhope.data.feature.user.datasource.UserDataSource
import com.revolhope.data.feature.user.datasource.UserDataSource.Companion.PREF_USER
import com.revolhope.data.feature.user.dto.UserDto
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val prefs: SharedPreferences
) : UserDataSource {

    override suspend fun fetchUser(): UserDto? =
        prefs.getJson(PREF_USER)

    override suspend fun insertOrUpdateUser(user: UserDto): UserDto? {
        prefs.putJson(PREF_USER, user)
        return user
    }
}