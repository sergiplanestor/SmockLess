package com.revolhope.data.common.json

import com.google.gson.Gson
import kotlin.reflect.KClass


fun <T : Any> String.mapJsonTo(clazz: KClass<T>): T = Gson().fromJson(this, clazz.java)

fun <T> T.mapToJson(): String = Gson().toJson(this)