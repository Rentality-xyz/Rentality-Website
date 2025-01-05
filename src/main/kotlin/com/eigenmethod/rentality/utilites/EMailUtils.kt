package com.eigenmethod.rentality.utilites

// Регулярное выражение для проверки email
private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

// Функция проверки
fun isValidEmail(email: String): Boolean {
    return emailRegex.matches(email)
}