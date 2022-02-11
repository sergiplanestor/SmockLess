package com.revolhope.domain.common.time.timelapse

import androidx.annotation.IntRange
import com.revolhope.domain.common.time.utils.*
import com.revolhope.domain.common.time.utils.millisOfA30Month
import com.revolhope.domain.common.time.utils.millisOfA31Month
import com.revolhope.domain.common.time.utils.millisOfADay
import com.revolhope.domain.common.time.utils.millisOfAWeek
import com.revolhope.domain.common.time.utils.millisOfAnHour

sealed class Timelapse(open val millis: Long) {

    data class Second(@IntRange(from = 1) val amount: Int = 1) :
        Timelapse(millis = amount * millisOfASecond)

    data class Minute(@IntRange(from = 1) val amount: Int = 1) :
        Timelapse(millis = amount * millisOfAMinute)

    data class Hour(@IntRange(from = 1) val amount: Int = 1) :
        Timelapse(millis = amount * millisOfAnHour)

    data class Day(@IntRange(from = 1) val amount: Int = 1) :
        Timelapse(millis = amount * millisOfADay)

    data class Week(@IntRange(from = 1) val amount: Int = 1) :
        Timelapse(millis = amount * millisOfAWeek)

    data class Month(@IntRange(from = 1) val amount: Int = 1, val is31Days: Boolean = false) :
        Timelapse(millis = amount * if (is31Days) millisOfA31Month else millisOfA30Month)

    data class Year(@IntRange(from = 1) val amount: Int = 1) :
        Timelapse(millis = amount * millisOfAYear)
}
