package com.eigenmethod.rentality.models.blockchain

data class ContractTrips(
    @JsName("tripId")
    val tripId: Long? = null,
    @JsName("carId")
    val carId: Long? = null,
    @JsName("status")
    val status: Int? = null, //TripStatus
    @JsName("guest")
    val guest: String? = null,
    @JsName("host")
    val host: String? = null,
    @JsName("pricePerDayInUsdCents")
    val pricePerDayInUsdCents: Long? = null,
    @JsName("startDateTime")
    val startDateTime: Long? = null,
    @JsName("endDateTime")
    val endDateTime: Long? = null,
    @JsName("startLocation")
    val startLocation: String? = null,
    @JsName("endLocation")
    val endLocation: String? = null,
    @JsName("milesIncluded")
    val milesIncluded: Int? = null,
    @JsName("fuelPricePerGalInUsdCents")
    val fuelPricePerGalInUsdCents: Long? = null,
    @JsName("paymentInfo")
    val paymentInfo: ContractPaymentInfo,
    @JsName("approvedDateTime")
    val approvedDateTime: Int? = null,
    @JsName("checkedInByHostDateTime")
    val checkedInByHostDateTime: Int? = null,
    @JsName("startFuelLevelInGal")
    val startFuelLevelInGal: Int? = null,
    @JsName("startOdometr")
    val startOdometr: Int? = null,
    @JsName("checkedInByGuestDateTime")
    val checkedInByGuestDateTime: Int? = null,
    @JsName("checkedOutByGuestDateTime")
    val checkedOutByGuestDateTime: Int? = null,
    @JsName("endFuelLevelInGal")
    val endFuelLevelInGal: Int? = null,
    @JsName("endOdometr")
    val endOdometr: Int? = null,
    @JsName("checkedOutByHostDateTime")
    val checkedOutByHostDateTime: Int? = null
)
