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