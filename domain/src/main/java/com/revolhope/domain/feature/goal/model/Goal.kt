package com.revolhope.domain.feature.goal.model

import android.os.Parcelable
import com.revolhope.domain.common.time.model.TimeModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Goal(
    val id: String,
    val name: String,
    val description: String?,
    val from: Int,
    val to: Int,
    val gap: GoalGap?,
    val checkpointEvery: TimeModel?,
    val createdOn: TimeModel,
    val deadlineOn: TimeModel?,
    val updatedOn: TimeModel?
): Parcelable