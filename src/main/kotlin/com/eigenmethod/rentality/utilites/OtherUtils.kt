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
//fun isValidShuttleDate(dateTime: String): Boolean {
//    val validDates = setOf("2025-01-23", "2025-01-24")
//    val date = dateTime.split("T").firstOrNull() // Получаем только дату до T (2025-01-23T10:30)
//    return date in validDates
//}
fun isValidShuttleDate(dateTime: String): Boolean {
    val validDates = setOf("2025-01-23", "2025-01-24")
    val dateTimeParts = dateTime.split("T") // Разделяем дату и время
    val date = dateTimeParts.getOrNull(0) // Получаем дату
    val time = dateTimeParts.getOrNull(1) // Получаем время
    console.log("ddiLog dateTimeParts=$dateTimeParts")
    console.log("ddiLog date=$date")
    console.log("ddiLog time=$time")

    if (date !in validDates) return false // Проверяем дату

    if (time != null) {
        val parsedTime = time.split(":") // Парсим время из строки
        val parsedHours = parsedTime[0].toInt()
        val parsedMinutes = parsedTime[1].toInt()

        val cutoffHours = 19 // Граничное время
        val cutoffMinutes = 30 // Граничное время
        console.log("ddiLog parsedHours=$parsedHours")
        console.log("ddiLog parsedMinutes=$parsedMinutes")

        // Сравниваем часы и минуты вручную
        if (parsedHours > cutoffHours ||
                (parsedHours == cutoffHours && parsedMinutes > cutoffMinutes)) {
            return false
        }
    } else {
        return false
    }

    return true
}