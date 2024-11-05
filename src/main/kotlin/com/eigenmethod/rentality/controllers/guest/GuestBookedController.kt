package com.eigenmethod.rentality.controllers.guest

import com.eigenmethod.rentality.models.BookedCar
import com.eigenmethod.rentality.models.Car
import com.eigenmethod.rentality.models.RentCar
import com.eigenmethod.rentality.models.TripStatus
import com.eigenmethod.rentality.models.blockchain.ContractCarInfo
import com.eigenmethod.rentality.models.blockchain.ContractPaymentInfo
import com.eigenmethod.rentality.models.blockchain.ContractTrips
import com.eigenmethod.rentality.models.blockchain.RentalityContract
import com.eigenmethod.rentality.utilites.orIfNull
import com.eigenmethod.rentality.utilites.toKotlinObj
import io.kvision.types.LocalDate
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json

suspend fun getBookedCarsForGuest(): List<BookedCar> {
    val listBookedCars: MutableList<BookedCar> = emptyList<BookedCar>().toMutableList()
    return try {
        val tripsAsGuest = RentalityContract.getTripsAsGuest().await()
//        console.log("bookedCars-guest=${bookedCars}")

        for (trip in tripsAsGuest) {
            val contractTrips = toKotlinObj(trip, ContractTrips::class)
            val paymentInfo = toKotlinObj(contractTrips.paymentInfo, ContractPaymentInfo::class)
            val contractTripsWithPayment = contractTrips.copy(paymentInfo = paymentInfo)
//            console.log("bookedCars-guest-contractTrips=${contractTrips}")
//            console.log("bookedCars-guest-paymentInfo=${paymentInfo}")
//            console.log("bookedCars-guest-contractTripsWithPayment=${contractTripsWithPayment}")

            val carMetadataURI = RentalityContract.getCarMetadataURI(contractTrips.carId).await() as? String
            val json = Json { ignoreUnknownKeys = true }
            val contractCarInfo =
                json.decodeFromString<ContractCarInfo>(carMetadataURI.orEmpty())
                    .copy(carId = contractTrips.carId, host = contractTrips.host)

            contractTrips
                .status
                ?.also { status ->
                    val currStatus = TripStatus.values().firstOrNull {
                        it.ordinal == status
                    }?.name.orEmpty()
                    if (!(currStatus == TripStatus.Closed.name || currStatus == TripStatus.Rejected.name)) {
                        val rentCar =
                            RentCar(
                                Car.carFromContractCarInfo(contractCarInfo),
                                contractTripsWithPayment.paymentInfo.depositInUsdCents.orIfNull { 0 }.toString().toLongOrNull().orIfNull { 0 } / 100,
                                contractTrips.startLocation.orEmpty(),
                                LocalDate(contractTrips.startDateTime.orIfNull { 0 }.toString().toLongOrNull().orIfNull { 0 }),
                                LocalDate(contractTrips.endDateTime.orIfNull { 0 }.toString().toLongOrNull().orIfNull { 0 }),
                                contractTrips.fuelPricePerGalInUsdCents.orIfNull { 0 }
                            )
                        listBookedCars.add(BookedCar(contractTrips, rentCar))
                    }
                }
        }
        listBookedCars
    } catch (error: Throwable) {
        console.log("getBookedCarsForGuest Error=$error")
        emptyList()
    }
}