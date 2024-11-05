package com.eigenmethod.rentality.controllers

import com.eigenmethod.rentality.models.CommunityData
import com.eigenmethod.rentality.models.GoogleTableResponse
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json

suspend fun sendCommunityDataToGoogleTable(data: CommunityData) {
    val url = "https://script.google.com/macros/s/AKfycbz62lbmv99ycs0fF9PNx8sSsUvOlUimnUudyR765JrHvVDZIRBY0vZRxp2b5rErbQzl/exec?GoogleSheetId=1AbYFfe7WSpd8KyPL571Qp3IlUsF14HfTz8ZEzbLIePk&name=${data.name}&email=${data.email}&phone=${data.phoneNumber}" // Замените на ваш URL
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