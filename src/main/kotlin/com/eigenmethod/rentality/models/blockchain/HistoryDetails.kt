package com.eigenmethod.rentality.models.blockchain

data class HistoryDetails(
    @JsName("tripId")
    val tripId: Long? = null,
    @JsName("carId")
    val carId: Long? = null,
    @JsName("status")
    val status: String? = null,
    @JsName("guest")
    val guest: String? = null,
    @JsName("host")
    val host: String? = null,
    @JsName("startDateTime")
    val startDateTime: Double? = null,
    @JsName("endDateTime")
    val endDateTime: Double? = null,
    @JsName("startLocation")
    val startLocation: String? = null,
    @JsName("endLocation")
    val endLocation: String? = null,
    @JsName("milesIncluded")
    val milesIncluded: String? = null,
    @JsName("fuelPricePerGalInUsd")
    val fuelPricePerGalInUsd: String? = null,
    @JsName("approvedDateTime")
    val approvedDateTime: Double? = null,
    @JsName("checkedInByHostDateTime")
    val checkedInByHostDateTime: Double? = null,
    @JsName("startFuelLevelInGal")
    val startFuelLevelInGal: Int? = null,
    @JsName("startOdometr")
    val startOdometer: Int? = null,
    @JsName("checkedInByGuestDateTime")
    val checkedInByGuestDateTime: Double? = null,
    @JsName("checkedOutByGuestDateTime")
    val checkedOutByGuestDateTime: Double? = null,
    @JsName("endFuelLevelInGal")
    val endFuelLevelInGal: Int? = null,
    @JsName("endOdometr")
    val endOdometer: Int? = null,
    @JsName("checkedOutByHostDateTime")
    val checkedOutByHostDateTime: Double? = null,
    @JsName("resolveAmountInUsd")
    val resolveAmountInUsd: Int? = null,
    @JsName("paymentFrom")
    val paymentFrom: String? = null,
    @JsName("paymentTo")
    val paymentTo: String? = null,
    @JsName("pricePerDayInUsdCents")
    val pricePerDayInUsdCents: Int? = null,
    @JsName("totalDayPriceInUsd")
    val totalDayPriceInUsd: Int? = null,
    @JsName("taxPriceInUsd")
    val taxPriceInUsd: Int? = null,
    @JsName("depositInUsd")
    val depositInUsd: Int? = null,
    @JsName("currencyType")
    val currencyType: Int? = null,
    @JsName("ethToCurrencyRate")
    val ethToCurrencyRate: Int? = null
)
