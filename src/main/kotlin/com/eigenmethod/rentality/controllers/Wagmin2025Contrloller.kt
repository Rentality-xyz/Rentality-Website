package com.eigenmethod.rentality.controllers

import com.eigenmethod.rentality.models.GoogleTableResponse
import com.eigenmethod.rentality.models.Wagmi2025Data
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json

suspend fun sendWagmi2025DataToGoogleTable(data: Wagmi2025Data) {
    val url = "https://script.google.com/macros/s/AKfycbxdx0yOiKvtTr5kxbGdRAU4FYAP3C-qmfNjoz5w2S8641vLiF6z50LlIfDExFnTR8Sr/exec?GoogleSheetId=1x5Zu_7JyaiaXk7mZ8FXXZ09n1YnRb6zbtUz7iLpoGzM&first_name=${data.firstName}&last_name=${data.lastName}&company=${data.company}&hotel=${data.hotel}&email=${data.email}&phone=${data.phone}&date=${data.date}" // Замените на ваш URL
    val response = window.fetch(url).await()
    // Преобразование ответа в формат JSON
    val json = response.json().await()
    val googleTableResponse = Json.decodeFromString<GoogleTableResponse>(JSON.stringify(json))
//    console.log("google-table-response-json=${JSON.stringify(json)}")
//    console.log("ddi-test-result=${googleTableResponse.result}") //success
//    console.log("ddi-test-error=${googleTableResponse.error?.name.orEmpty()}")

    if (response.ok) {
        if (googleTableResponse.result == "success") {
            window.alert("Success")
        } else {
            window.alert(googleTableResponse.error?.name.orEmpty())
        }
    } else {
        // обработка ошибки
        window.alert(JSON.stringify(json))
    }
}