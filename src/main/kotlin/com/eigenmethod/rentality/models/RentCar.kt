package com.eigenmethod.rentality.models

import com.eigenmethod.rentality.utilites.calculateDays
import com.eigenmethod.rentality.utilites.orIfNull
import io.kvision.types.LocalDate

data class RentCar(
    val car: Car = Car(),
    val deposit: Long = 0,
    val location: String = "",
    val dateFrom: LocalDate = LocalDate(),
    val dateTo: LocalDate = LocalDate(),
    val fuelPricePerGalInUsdCents: Long = 0
) {
    fun getTotalPrice(): Long {
        return (this.car.pricePerDay?.toIntOrNull().orIfNull { 0 } * getRentalPeriod()) + this.deposit
    }

    fun getRentalPeriod(): Int {
        return this.dateFrom.calculateDays(this.dateTo)
    }
}
