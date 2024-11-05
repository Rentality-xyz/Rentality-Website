package com.eigenmethod.rentality.models.blockchain

data class ContractPaymentInfo(
    @JsName("tripId")
    val tripId: Long? = null,
    @JsName("from")
    val from: String? = null,
    @JsName("to")
    val to: String? = null,
    @JsName("totalDayPriceInUsdCents")
    val totalDayPriceInUsdCents: Long? = null,
    @JsName("taxPriceInUsdCents")
    val taxPriceInUsdCents: Long? = null,
    @JsName("depositInUsdCents")
    val depositInUsdCents: Long? = null,
    @JsName("resolveAmountInUsdCents")
    val resolveAmountInUsdCents: Long? = null,
    @JsName("currencyType")
    val currencyType: Int? = null,
    @JsName("ethToCurrencyRate")
    val ethToCurrencyRate: Long? = null,
    @JsName("ethToCurrencyDecimals")
    val ethToCurrencyDecimals: Long? = null
)