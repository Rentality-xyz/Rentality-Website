package com.eigenmethod.rentality.models.abis

import kotlinx.serialization.Serializable

@Serializable
data class Rentality(
    val address: String? = null,
    val abi: List<Abi>? = null
)
@Serializable
data class Abi(
    val anonymous: Boolean? = null,
    val constant: Boolean? = null,
    val inputs: List<Input>? = null,
    val name: String? = null,
    val outputs: List<Output>? = null,
    val payable: Boolean? = null,
    val stateMutability: String? = null,
    val type: String? = null
)
@Serializable
data class Input(
    val components: List<Component>? = null,
    val indexed: Boolean? = null,
    val name: String? = null,
    val type: String? = null
)
@Serializable
data class Output(
    val components: List<ComponentX>? = null,
    val type: String? = null
)
@Serializable
data class Component(
    val name: String? = null,
    val type: String? = null
)
@Serializable
data class ComponentX(
    val components: List<Component>? = null,
    val name: String? = null,
    val type: String? = null
)