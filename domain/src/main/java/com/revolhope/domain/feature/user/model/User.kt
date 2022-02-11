package com.revolhope.domain.feature.user.model

import com.revolhope.domain.common.time.model.TimeModel

data class User(
    val id: String,
    val name: String,
    val createdOn: TimeModel,
    val lastSessionOn: TimeModel
)
