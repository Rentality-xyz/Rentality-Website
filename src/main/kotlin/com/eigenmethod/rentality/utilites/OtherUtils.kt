package com.eigenmethod.rentality.utilites

fun <T : Any> tryOrDefault(messageForLog: String? = null, defaultIfCatches: T, tryFunc: () -> T): T {
    return try {
        tryFunc()
    } catch (e: Exception) {
        console.log("tryOrDefault exception: ${messageForLog ?: e.message}" )
        defaultIfCatches
    }
}

fun <T : Any> tryOrNull(messageForLog: String? = null, tryFunc: () -> T): T? {
    return try {
        tryFunc()
    } catch (e: Exception) {
        console.log("tryOrNull exception: ${messageForLog ?: e.message}")
        null
    }
}

inline fun <reified T> T?.orIfNull(input: () -> T): T {
    return this ?: input()
}

// Проверка корректности введённой даты Wagmi
fun isValidShuttleDate(dateTime: String): Boolean {
    val validDates = setOf("2025-01-22", "2025-01-23", "2025-01-24")
    val date = dateTime.split("T").firstOrNull() // Получаем только дату до T (2025-01-23T10:30)
    return date in validDates
}