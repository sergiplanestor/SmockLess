package com.revolhope.domain.feature.goal.model

import android.os.Parcelable
import com.revolhope.domain.common.time.model.TimeModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoalGap(
    val duration: TimeModel,
    val increment: Int
): Parcelable
