package com.eigenmethod.rentality.utilites

import io.kvision.types.LocalDate

fun LocalDate.calculateDays(dateTo: LocalDate): Int {
    val days = (dateTo.getDate() - this.getDate()) + 1
    return if (days < 0) 0 else days
}