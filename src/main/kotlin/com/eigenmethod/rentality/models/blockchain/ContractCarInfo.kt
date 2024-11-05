package com.eigenmethod.rentality.models.blockchain

import com.eigenmethod.rentality.utilites.orIfNull
import com.eigenmethod.rentality.utilites.toKotlinObj
import kotlinx.coroutines.await
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ContractCarInfo(
    val carId: Long? = null,
    val name: String? = null,
    val host: String? = null,
    val description: String? = null,
    val image: String? = null,
    val attributes: List<ContractCarInfoAttribute>? = null,
) {
    fun getVinNumber() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.VinNumber.value }?.value.orEmpty()
    fun getLicensePlate() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.LicensePlate.value }?.value.orEmpty()
    fun getState() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.State.value }?.value.orEmpty()
    fun getBrand() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.Brand.value }?.value.orEmpty()
    fun getModel() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.Model.value }?.value.orEmpty()
    fun getReleaseYear() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.ReleaseYear.value }?.value.orEmpty()
    fun getBodyType() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.BodyType.value }?.value.orEmpty()
    fun getColor() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.Color.value }?.value.orEmpty()
    fun getDoorsNumber() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.DoorsNumber.value }?.value.orEmpty()
    fun getSeatsNumber() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.SeatsNumber.value }?.value.orEmpty()
    fun getTrunkSize() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.TrunkSize.value }?.value.orEmpty()
    fun getTransmission() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.Transmission.value }?.value.orEmpty()
    fun getWheelDrive() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.WheelDrive.value }?.value.orEmpty()
    fun getFuelType() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.FuelType.value }?.value.orEmpty()
    fun getTankVolumeInGal() = this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.TankVolumeInGal.value }?.value.orEmpty()
    fun getDistanceIncludedInMi()  = ((this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.DistanceIncludedInMi.value }?.value.orIfNull { "0" }.toIntOrNull() ?: 0) / 100).toString()
    fun getPricePerDay() = ((this.attributes?.firstOrNull { it.trait_type == TraitTypeCarInfo.PricePerDay.value }?.value.orIfNull { "0" }.toIntOrNull() ?: 0) / 100).toString()

    companion object {
        suspend fun contractCartoKotlinContractCarInfo(
            car: dynamic,
            rentalityContract: RentalityContract
        ): ContractCarInfo {
//            console.log("ContractCarListing-car=$car")
            //если класс используется в ф-ции toKotlinObj (в нашем случае это класс ContractCarToRent), то в этом классе необходимо поля помечать аннотацией @JsName("tokenId") и указывать имя поля как оно называется в JavaScript
            val contractCarListing = toKotlinObj(car, ContractCarListing::class)
            val carId = contractCarListing.carId.orIfNull { 0 }
//            console.log("ContractCarListing[$carId]=$contractCarListing")

            val carMetadataURI = rentalityContract.getCarMetadataURI(carId).await() as? String
//            console.log("carMetadataURI=${carMetadataURI}")

            val json = Json { ignoreUnknownKeys = true }
            val contractCarInfo = json.decodeFromString<ContractCarInfo>(carMetadataURI.orEmpty())
            return contractCarInfo.copy(carId = carId, host = contractCarListing.host)
        }
    }
}
@Serializable
data class ContractCarInfoAttribute(
    val trait_type: String? = null,
    val value: String? = null
)

enum class TraitTypeCarInfo(val value: String) {
    VinNumber("VIN number"),
    LicensePlate("License plate"),
    State("State"),
    Brand("Brand"),
    Model("Model"),
    ReleaseYear("Release year"),
    BodyType("Body type"),
    Color("Color"),
    DoorsNumber("Doors number"),
    SeatsNumber("Seats number"),
    TrunkSize("Trunk size"),
    Transmission("Transmission"),
    WheelDrive("Wheel drive"),
    FuelType("Fuel type"),
    TankVolumeInGal("Tank volume(gal)"),
    DistanceIncludedInMi("Distance included(mi)"),
    PricePerDay("Price per Day (USD cents)")
}
