package com.eigenmethod.rentality.utilites

fun Double?.orZero(): Double {
    return this ?: 0.0
}

fun Double?.isMoreZero(): Boolean = this.orZero() > 0.0

fun Double?.isZero(): Boolean = this == 0.0

fun Double?.toIntOrZero(): Int = this.orZero().toInt()