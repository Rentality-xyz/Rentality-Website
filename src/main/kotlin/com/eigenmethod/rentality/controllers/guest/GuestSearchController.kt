package com.eigenmethod.rentality.controllers.guest

import com.eigenmethod.rentality.models.Car.Companion.carFromContractCarInfo
import com.eigenmethod.rentality.models.RentCar
import com.eigenmethod.rentality.models.blockchain.ContractCarInfo.Companion.contractCartoKotlinContractCarInfo
import com.eigenmethod.rentality.models.blockchain.RentalityContract
import com.eigenmethod.rentality.utilites.orIfNull
import io.kvision.types.LocalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.await
import kotlinx.coroutines.withContext

suspend fun searchAvailableCars(
    location: String,
    dateFrom: LocalDate,
    dateTo: LocalDate
) : List<RentCar> =
    withContext(Dispatchers.Default) {
        val listRentCarInfo: MutableList<RentCar> = emptyList<RentCar>().toMutableList()

        try {
            val availableCars = RentalityContract.getAvailableCars().await()
//            console.log("availableCars=$availableCars")
            for (contractCar in availableCars) {
                val contractCarInfo = contractCartoKotlinContractCarInfo(contractCar, RentalityContract)
                val car = carFromContractCarInfo(contractCarInfo)
                val deposit = (RentalityContract.getDepositPriceInUsdCents().await() as? Int ?: 0) / 100
                val fuelPricePerGalInUsdCents = (RentalityContract.getFuelPricePerGalInUsdCents().await() as? Long).orIfNull { 0 }
                listRentCarInfo.add(
                    RentCar(
                        car,
                        deposit.toLong(),
                        location,
                        dateFrom,
                        dateTo,
                        fuelPricePerGalInUsdCents
                    )
                )
            }
            listRentCarInfo
        } catch (error: Throwable) {
            console.log("searchAvailableCarsError=$error") // Выводит: "Ошибка операции"
            emptyList()
        }
    }