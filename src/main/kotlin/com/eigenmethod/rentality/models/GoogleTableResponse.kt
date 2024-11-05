package com.eigenmethod.rentality.models

import kotlinx.serialization.Serializable

@Serializable
data class GoogleTableResponse(
    val result: String? = null,
    val row: Int? = null,
    val error: Error? = null,
)

@Serializable
data class Error(
    val name: String? = null
)

