package com.revolhope.data.common.preferences.utils

import android.content.SharedPreferences
import androidx.core.content.edit
import com.revolhope.data.common.json.mapJsonTo
import com.revolhope.data.common.json.mapToJson

inline fun <reified T : Any> SharedPreferences.getJson(key: String): T? =
    getString(key, null)?.mapJsonTo(T::class)

fun SharedPreferences.putJson(key: String, json: String) =
    edit { putString(key, json) }

inline fun <reified T : Any> SharedPreferences.putJson(key: String, data: T) =
    putJson(key, data.mapToJson())