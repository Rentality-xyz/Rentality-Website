package com.eigenmethod.rentality.controllers.host

import com.eigenmethod.rentality.models.Car
import com.eigenmethod.rentality.models.Pinata
import com.eigenmethod.rentality.models.blockchain.RentalityContract
import com.eigenmethod.rentality.utilites.orIfNull
import com.eigenmethod.rentality.utilites.orZero
import js.core.BigInt
import kotlinx.coroutines.await
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.files.File

suspend fun saveCar(car: Car, image: File): Boolean {
    val pinataURL = Pinata.uploadFileToIPFS(image)
    car.imageURL = pinataURL
//        console.log("pinata URL=${this.imageURL}")

    if (pinataURL.isEmpty()) {
        console.error("Uploaded image to Pinata error");
        return false
    }

    val carMetadataUri = Json.encodeToString(car.toContractCarInfo())

        val doubleNumber = car.pricePerDay?.replace(Regex("[^0-9.]+"), "")?.toDouble().orZero()
        val pricePerDay = ((doubleNumber * 100).toInt()).toString()
    val resultTransaction = RentalityContract.addCar(
        carMetadataUri,
        car.vinNumber.orEmpty(),
        BigInt(pricePerDay),
        BigInt(car.tankVolumeInGal.orIfNull { "0" }),
        BigInt(car.distanceIncludedInMi.orIfNull { "0" })
    ).await()
//        console.log("add-car-result=${JSON.stringify(resultTransaction)}")
    return true
}