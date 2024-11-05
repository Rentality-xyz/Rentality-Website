package com.eigenmethod.rentality.models

enum class ELegalMatters (val value: String) {
    TERMS("terms"),
    CANCELLATION("cancellation"),
    PROHIBITEDUSES("prohibiteduses"),
    PRIVACY("privacy");

    companion object {
        fun convertToELegalMatters(value: String): ELegalMatters? {
            return values().firstOrNull { it.value == value.lowercase() }
        }
    }

}