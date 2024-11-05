package com.eigenmethod.rentality.controllers.host

import com.eigenmethod.rentality.models.Car
import com.eigenmethod.rentality.models.blockchain.ContractCarInfo
import com.eigenmethod.rentality.models.blockchain.RentalityContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.await
import kotlinx.coroutines.withContext

suspend fun getInfoCarsOfCurrentHost(): List<Car> = withContext(Dispatchers.Default) {
    val listCarInfo: MutableList<Car> = emptyList<Car>().toMutableList()

    try {
        val carsHost = RentalityContract.getMyCars().await()
//       console.log("contract-carsHost-result=$carsHost")
        for (contractCar in carsHost) {
            val contractCarInfoCar = ContractCarInfo.contractCartoKotlinContractCarInfo(contractCar,RentalityContract)
            val car = Car.carFromContractCarInfo(contractCarInfoCar)
            listCarInfo.add(car)
        }
        listCarInfo
    } catch (error: Throwable) {
        console.log("promiseGetInfoCarsOfCurrentHostError=$error") // Выводит: "Ошибка операции"
        emptyList()
    }
}