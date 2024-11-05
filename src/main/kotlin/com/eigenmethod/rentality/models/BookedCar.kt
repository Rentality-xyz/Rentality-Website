package com.eigenmethod.rentality.models

import com.eigenmethod.rentality.models.blockchain.ContractTrips

data class BookedCar(
    val trips: ContractTrips ,
    val rentCar: RentCar
)
