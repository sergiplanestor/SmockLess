package com.revolhope.domain.common.time.utils

import com.revolhope.domain.common.time.model.TimeModel
import com.revolhope.domain.common.time.timelapse.Timelapse
import kotlin.reflect.KProperty

operator fun TimeModel.plus(other: TimeModel): TimeModel =
    TimeModel(millis = millis + other.millis)

operator fun TimeModel.minus(other: TimeModel): TimeModel =
    TimeModel(millis = millis - other.millis)

operator fun TimeModel.plus(lapse: Timelapse): TimeModel =
    TimeModel(millis = millis + lapse.millis)

operator fun TimeModel.minus(lapse: Timelapse): TimeModel =
    TimeModel(millis = millis - lapse.millis)
