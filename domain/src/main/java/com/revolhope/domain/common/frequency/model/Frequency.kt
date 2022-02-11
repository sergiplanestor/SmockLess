package com.revolhope.domain.common.frequency.model

sealed class Frequency(open val amount: Int) {
    data class Hourly(override val amount: Int) : Frequency(amount)
    data class Daily(override val amount: Int) : Frequency(amount)
    data class Weekly(override val amount: Int) : Frequency(amount)
    data class Monthly(override val amount: Int) : Frequency(amount)
}
