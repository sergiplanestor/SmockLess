package com.revolhope.domain.common.time.utils

import com.revolhope.domain.common.time.model.TimeModel
import com.revolhope.domain.common.time.timelapse.Timelapse

fun currentMillis(): Long = System.currentTimeMillis()

fun now(): TimeModel = currentMillis().timeModel

fun today(): TimeModel = now()

fun tomorrow(): TimeModel = today() + Timelapse.Day()

fun anHourLater(): TimeModel = now() + Timelapse.Hour()