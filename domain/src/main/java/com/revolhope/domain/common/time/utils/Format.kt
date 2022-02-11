package com.revolhope.domain.common.time.utils

import com.revolhope.domain.common.time.model.TimeModel
import com.revolhope.domain.common.time.patterns.DATE_PATTERN_DD_MM_YY
import java.text.SimpleDateFormat
import java.util.Locale

fun TimeModel.format(locale: Locale = Locale.getDefault()): String =
    SimpleDateFormat(DATE_PATTERN_DD_MM_YY, locale).format(millis)