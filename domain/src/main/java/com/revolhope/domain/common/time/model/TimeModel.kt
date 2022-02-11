package com.revolhope.domain.common.time.model

import android.os.Parcelable
import com.revolhope.domain.common.time.patterns.DATE_PATTERN_DD_MM_YY
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Locale

@Parcelize
data class TimeModel(val millis: Long): Parcelable {

    val isEmpty: Boolean get() = millis > 0

    companion object {
        val empty: TimeModel
            get() = TimeModel(millis = -1)
    }
}
