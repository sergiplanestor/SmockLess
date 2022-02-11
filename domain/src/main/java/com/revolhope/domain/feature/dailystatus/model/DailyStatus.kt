package com.revolhope.domain.feature.dailystatus.model

import android.os.Parcelable
import com.revolhope.domain.common.time.model.TimeModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyStatus(
    val currentCount: Int,
    val maxCount: Int,
    val lastUpdateOn: TimeModel
): Parcelable
