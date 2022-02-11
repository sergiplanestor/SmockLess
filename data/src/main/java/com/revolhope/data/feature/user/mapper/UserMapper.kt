package com.revolhope.data.feature.user.mapper

import com.revolhope.data.feature.user.dto.UserDto
import com.revolhope.domain.common.time.utils.orEmpty
import com.revolhope.domain.common.time.utils.timeModel
import com.revolhope.domain.feature.user.model.User

object UserMapper {

    fun mapDataToDomain(dto: UserDto): User =
        User(
            id = dto.id.orEmpty(),
            name = dto.name.orEmpty(),
            createdOn = dto.createdOn?.timeModel.orEmpty(),
            lastSessionOn = dto.lastSessionOn?.timeModel.orEmpty()
        )

    fun mapDomainToData(user: User): UserDto =
        UserDto(
            id = user.id,
            name = user.name,
            createdOn = user.createdOn.millis,
            lastSessionOn = user.lastSessionOn.millis
        )

}