package com.eigenmethod.rentality.controllers

import com.eigenmethod.rentality.models.blockchain.HistoryDetails
import com.eigenmethod.rentality.models.blockchain.RentalityContract
import com.eigenmethod.rentality.utilites.toKotlinObj
import js.core.BigInt
import kotlinx.coroutines.await

suspend fun getHistoryDetails(tripId: Long): HistoryDetails? {
    return try {
        val contractHistoryDetails = RentalityContract.getTrip(BigInt(tripId.toString())).await()
//        console.log("bookedCars-guest-tripId=${tripId}")
//        console.log("bookedCars-guest=${contractHistoryDetails}")

        toKotlinObj(contractHistoryDetails, HistoryDetails::class)
//        console.log("bookedCars-guest-historyDetails=${historyDetails}")

    } catch (error: Throwable) {
        console.log("getHistoryDetails Error=$error")
        null
    }
}