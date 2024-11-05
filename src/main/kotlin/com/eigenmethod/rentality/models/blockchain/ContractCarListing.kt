package com.eigenmethod.rentality.models.blockchain

data class ContractCarListing(
    @JsName("carId")
    val carId: Long? = null,
    @JsName("carVinNumber")
    val carVinNumber: String? =null,
    @JsName("createdBy")
    val host: String? =null,
    @JsName("pricePerDayInUsdCents")
    val pricePerDayInUsdCents: Long? = null,
    @JsName("tankVolumeInGal")
    val tankVolumeInGal: Long? = null,
    @JsName("distanceIncludedInMi")
    val distanceIncludedInMi: Long? = null,
    @JsName("currentlyListed")
    val currentlyListed: Boolean? = null
)