package com.revolhope.domain.common.time.utils

internal val millisOfASecond by lazy { 1000L }
internal val millisOfAMinute by lazy { millisOfASecond * 60 }
internal val millisOfAnHour by lazy { millisOfAMinute * 60 }
internal val millisOfADay by lazy { millisOfAnHour * 24 }
internal val millisOfAWeek by lazy { millisOfADay * 7 }
internal val millisOfA30Month by lazy { millisOfADay * 30 }
internal val millisOfA31Month by lazy { millisOfADay * 31 }
internal val millisOfAYear by lazy { millisOfADay * 365 }
