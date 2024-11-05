package com.eigenmethod.rentality.models

data class TripRequest(
    val carId: Long,
    val host: String,
    val startDateTime: Double,
    val endDateTime: Double,
    val startLocation: String,
    val endLocation: String,
    val totalDayPriceInUsdCents: Long,
    val taxPriceInUsdCents: Int,
    val depositInUsdCents: Long,
    val ethToCurrencyRate: Long,
    val ethToCurrencyDecimals: Long
)
