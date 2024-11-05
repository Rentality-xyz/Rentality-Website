package com.eigenmethod.rentality.controllers

import com.eigenmethod.rentality.models.RentCar
import com.eigenmethod.rentality.models.TripRequest
import com.eigenmethod.rentality.models.blockchain.RentalityContract
import com.eigenmethod.rentality.models.blockchain.RentalityCurrConvertContract
import com.eigenmethod.rentality.utilites.orIfNull
import js.core.BigInt
import kotlinx.coroutines.await

suspend fun sendRentCarRequest(rentCar: RentCar) {
    val totalDayPriceInUsdCents = rentCar.getTotalPrice().orIfNull { 0 } * 100
    val depositInUsdCents = rentCar.deposit * 100
    val taxPriceInUsdCents = 0

    val rentPriceInUsdCents = totalDayPriceInUsdCents + taxPriceInUsdCents + depositInUsdCents
    val contractEthFromUsdLatest = RentalityCurrConvertContract.getEthFromUsdLatest(rentPriceInUsdCents).await()
    val rentPriceInEth = (contractEthFromUsdLatest[0] as? BigInt).toString().toLongOrNull().orIfNull { 0 }
    val ethToCurrencyRate = (contractEthFromUsdLatest[1] as? BigInt).toString().toLongOrNull().orIfNull { 0 }
    val ethToCurrencyDecimals = (contractEthFromUsdLatest[2] as? BigInt).toString().toLongOrNull().orIfNull { 0 }

    val tripRequest = TripRequest(
        carId = rentCar.car.carId.orIfNull { 0 },
        host = rentCar.car.host.orEmpty(),
        startDateTime = rentCar.dateFrom.getTime(),
        endDateTime = rentCar.dateTo.getTime(),
        startLocation = rentCar.location,
        endLocation = rentCar.location,
        totalDayPriceInUsdCents = totalDayPriceInUsdCents,
        taxPriceInUsdCents = taxPriceInUsdCents,
        depositInUsdCents = depositInUsdCents,
        ethToCurrencyRate = ethToCurrencyRate,
        ethToCurrencyDecimals = ethToCurrencyDecimals
    )

    RentalityContract.createTripRequest(tripRequest, rentPriceInEth).await()
}
