package com.eigenmethod.rentality.models.blockchain

import com.eigenmethod.rentality.constants.ABIS_RENTALITY
import com.eigenmethod.rentality.models.TripRequest
import com.eigenmethod.rentality.navigation_state.ConduitManager
import js.core.BigInt
import kotlin.js.Promise

private val provider = ConduitManager.getProvider()
private val contract = provider.getContract(ABIS_RENTALITY)

object RentalityContract : IRentalityContract {
    override fun getMyCars(): Promise<dynamic> {
        return Promise { resolve, _ ->
            // Здесь выполняется асинхронная операция JavaScript, которая возвращает значение через resolve()
            resolve(contract.getMyCars())
        }
    }

    override fun getAvailableCars(): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.getAvailableCars())
        }
    }

    override fun getCarMetadataURI(tokenId: dynamic): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.getCarMetadataURI(tokenId))
        }
    }

    override fun addCar(
        carMetadataUri: String,
        carVinNumber: String,
        pricePerDayInUsdCents: BigInt,
        tankVolumeInGal: BigInt,
        distanceIncludedInMi: BigInt
    ): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.addCar(carMetadataUri, carVinNumber, pricePerDayInUsdCents, tankVolumeInGal, distanceIncludedInMi))
        }
    }

    override fun createTripRequest(request: TripRequest, rentPriceInEth: Long): Promise<dynamic> {
        return Promise { resolve, _ ->
            val carId = request.carId
            val host = request.host
            val startDateTime = request.startDateTime
            val endDateTime = request.endDateTime
            val startLocation = request.startLocation
            val endLocation = request.endLocation
            val totalDayPriceInUsdCents = request.totalDayPriceInUsdCents
            val taxPriceInUsdCents = request.taxPriceInUsdCents
            val depositInUsdCents = request.depositInUsdCents
            val ethToCurrencyRate = request.ethToCurrencyRate
            val ethToCurrencyDecimals = request.ethToCurrencyDecimals
            val tripRequest = js("({ " +
                    "carId: carId, " +
                    "host: host, " +
                    "startDateTime: startDateTime, " +
                    "endDateTime: endDateTime, " +
                    "startLocation: startLocation, " +
                    "endLocation: endLocation, " +
                    "totalDayPriceInUsdCents: totalDayPriceInUsdCents, " +
                    "taxPriceInUsdCents: taxPriceInUsdCents, " +
                    "depositInUsdCents: depositInUsdCents, " +
                    "ethToCurrencyRate: BigInt(ethToCurrencyRate), " +
                    "ethToCurrencyDecimals: Number(ethToCurrencyDecimals) " +
                    "})")
            val options = js("({ value: BigInt(rentPriceInEth) })")
            resolve(contract.createTripRequest(tripRequest, options))
        }
    }

    override fun getDepositPriceInUsdCents(): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.getDepositPriceInUsdCents())
        }
    }

    override fun getFuelPricePerGalInUsdCents(): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.getFuelPricePerGalInUsdCents())
        }
    }

    override fun getTripsAsHost(): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.getTripsAsHost())
        }
    }

    override fun getTripsAsGuest(): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.getTripsAsGuest())
        }
    }

    override fun approveTripRequest(tripId: BigInt): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.approveTripRequest(tripId))
        }
    }

    override fun checkInByHost(
        tripId: BigInt,
        startFuelLevelInPermille: BigInt,
        startOdometr: BigInt
    ): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.checkInByHost(tripId, startFuelLevelInPermille, startOdometr))
        }
    }

    override fun checkOutByHost(tripId: BigInt, endFuelLevelInPermille: BigInt, endOdometr: BigInt): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.checkOutByHost(tripId, endFuelLevelInPermille, endOdometr))
        }
    }

    override fun finishTrip(tripId: BigInt): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.finishTrip(tripId))
        }
    }

    override fun checkInByGuest(
        tripId: BigInt,
        startFuelLevelInPermille: BigInt,
        startOdometr: BigInt
    ): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.checkInByGuest(tripId, startFuelLevelInPermille, startOdometr))
        }
    }

    override fun checkOutByGuest(tripId: BigInt, endFuelLevelInPermille: BigInt, endOdometr: BigInt): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.checkOutByGuest(tripId, endFuelLevelInPermille, endOdometr))
        }
    }

    override fun rejectTripRequest(tripId: BigInt): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.rejectTripRequest(tripId))
        }
    }

    override fun getTrip(tripId: BigInt): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.getTrip(tripId))
        }
    }
}

interface IRentalityContract {
    fun getMyCars(): Promise<dynamic>
    fun getAvailableCars(): Promise<dynamic>
    fun getCarMetadataURI(tokenId: dynamic): Promise<dynamic>
    fun addCar(
        carMetadataUri: String,
        carVinNumber: String,
        pricePerDayInUsdCents: BigInt,
        tankVolumeInGal: BigInt,
        distanceIncludedInMi: BigInt
    ): Promise<dynamic>
    fun createTripRequest(request: TripRequest, rentPriceInEth: Long): Promise<dynamic>
    fun getDepositPriceInUsdCents(): Promise<dynamic>
    fun getFuelPricePerGalInUsdCents(): Promise<dynamic>
    fun getTripsAsHost(): Promise<dynamic>
    fun getTripsAsGuest(): Promise<dynamic>
    fun approveTripRequest(tripId: BigInt): Promise<dynamic>
    fun checkInByHost(tripId: BigInt, startFuelLevelInPermille: BigInt, startOdometr: BigInt): Promise<dynamic>
    fun  checkOutByHost(tripId: BigInt, endFuelLevelInPermille: BigInt, endOdometr: BigInt): Promise<dynamic>
    fun finishTrip(tripId: BigInt): Promise<dynamic>
    fun checkInByGuest(tripId: BigInt, startFuelLevelInPermille: BigInt, startOdometr: BigInt): Promise<dynamic>
    fun checkOutByGuest(tripId: BigInt, endFuelLevelInPermille: BigInt, endOdometr: BigInt): Promise<dynamic>
    fun rejectTripRequest(tripId: BigInt): Promise<dynamic>
    fun getTrip(tripId: BigInt): Promise<dynamic>

}