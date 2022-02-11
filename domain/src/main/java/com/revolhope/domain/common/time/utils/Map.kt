package com.revolhope.domain.common.time.utils

import com.revolhope.domain.common.time.model.TimeModel
import com.revolhope.domain.common.time.timelapse.Timelapse

inline val Int.timelapseSecs: Timelapse get() = Timelapse.Second(this)
inline val Int.timelapseMinutes: Timelapse get() = Timelapse.Minute(this)
inline val Int.timelapseHours: Timelapse get() = Timelapse.Hour(this)
inline val Int.timelapseDays: Timelapse get() = Timelapse.Day(this)
inline val Int.timelapseWeeks: Timelapse get() = Timelapse.Week(this)
inline val Int.timelapseMonths: Timelapse get() = Timelapse.Month(this)
inline val Int.timelapseYears: Timelapse get() = Timelapse.Year(this)

inline val Long.timeModel: TimeModel get() = TimeModel(this)

fun TimeModel?.orEmpty(): TimeModel = this ?: TimeModel.empty