package com.eigenmethod.rentality.utilites

import io.kvision.utils.createInstance
import kotlin.reflect.KClass

//если класс используется в ф-ции toKotlinObj (в нашем случае это класс ContractCarToRent), то в этом классе необходимо поля помечать аннотацией @JsName("tokenId") и указывать имя поля как оно называется в JavaScript
fun <T : Any> toKotlinObj(data: dynamic, kClass: KClass<T>): T {
    val newT = kClass.js.createInstance<T>()
    for (key in js("Object").keys(data)) {
        newT.asDynamic()[key] = data[key]
    }
    return newT
}