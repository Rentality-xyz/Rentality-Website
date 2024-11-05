package com.eigenmethod.rentality.models

import com.eigenmethod.rentality.models.blockchain.ContractCarInfo
import com.eigenmethod.rentality.models.blockchain.ContractCarInfoAttribute
import com.eigenmethod.rentality.models.blockchain.TraitTypeCarInfo

data class Car(
    var carId: Long? = null,
    var host: String? = null,
    var vinNumber: String? = null,
    var imageURL: String? = null,
    var name: String? = null,
    var description: String? = null,
    var licensePlate: String? = null,
    var state: String? = null,
    var brand: String? = null,
    var model: String? = null,
    var releaseYear: String? = null,
    var bodyType: String? = null,
    var color: String? = null,
    var doorsNumber: String? = null,
    var seatsNumber: String? = null,
    var trunkSize: String? = null,
    var transmission: String? = null,
    var wheelDrive: String? = null,
    var fuelType: String? = null,
    var tankVolumeInGal: String? = null,
    var distanceIncludedInMi: String? = null,
    var pricePerDay: String? = null
) {
    fun checkAllPropertiesTrue(): Boolean {
        return vinNumber?.isNotBlank() == true
                && imageURL?.isNotBlank() == true
                && name?.isNotBlank() == true
                && description?.isNotBlank() == true
                && licensePlate?.isNotBlank() == true
                && state?.isNotBlank() == true
                && brand?.isNotBlank() == true
                && model?.isNotBlank() == true
                && releaseYear?.isNotBlank() == true
//                && bodyType?.isNotBlank() == true
                && color?.isNotBlank() == true
                && doorsNumber?.isNotBlank() == true
                && seatsNumber?.isNotBlank() == true
//                && trunkSize?.isNotBlank() == true
                && transmission?.isNotBlank() == true
//                && wheelDrive?.isNotBlank() == true
                && fuelType?.isNotBlank() == true
                && tankVolumeInGal?.isNotBlank() == true
                && distanceIncludedInMi?.isNotBlank() == true
                && pricePerDay?.isNotBlank() == true
    }

    fun toContractCarInfo(): ContractCarInfo {
        return ContractCarInfo(
            carId = this.carId ?: 0,
            name = this.name.orEmpty(),
            host = this.host.orEmpty(),
            description = this.description.orEmpty(),
            image = this.imageURL.orEmpty(),
            attributes = listOf(
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.VinNumber.value,
                    value = this.vinNumber.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.LicensePlate.value,
                    value = this.licensePlate.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.State.value,
                    value = this.state.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.Brand.value,
                    value = this.brand.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.Model.value,
                    value = this.model.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.ReleaseYear.value,
                    value = this.releaseYear.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.BodyType.value,
                    value = this.bodyType.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.Color.value,
                    value = this.color.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.DoorsNumber.value,
                    value = this.doorsNumber.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.SeatsNumber.value,
                    value = this.seatsNumber.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.TrunkSize.value,
                    value = this.trunkSize.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.Transmission.value,
                    value = this.transmission.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.WheelDrive.value,
                    value = this.wheelDrive.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.FuelType.value,
                    value = this.fuelType.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.TankVolumeInGal.value,
                    value = this.tankVolumeInGal.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.DistanceIncludedInMi.value,
                    value = this.distanceIncludedInMi.orEmpty()
                ),
                ContractCarInfoAttribute(
                    trait_type = TraitTypeCarInfo.PricePerDay.value,
                    value = this.pricePerDay.orEmpty()
                )
            )
        )
    }

    companion object {
        fun carFromContractCarInfo(contractCarInfo: ContractCarInfo): Car {
            return Car(
                carId = contractCarInfo.carId,
                host = contractCarInfo.host,
                vinNumber = contractCarInfo.getVinNumber(),
                imageURL = contractCarInfo.image,
                name = contractCarInfo.name,
                description = contractCarInfo.description,
                licensePlate = contractCarInfo.getLicensePlate(),
                state = contractCarInfo.getState(),
                brand = contractCarInfo.getBrand(),
                model = contractCarInfo.getModel(),
                releaseYear = contractCarInfo.getReleaseYear(),
                bodyType = contractCarInfo.getBodyType(),
                color = contractCarInfo.getColor(),
                doorsNumber = contractCarInfo.getDoorsNumber(),
                seatsNumber = contractCarInfo.getSeatsNumber(),
                trunkSize = contractCarInfo.getTrunkSize(),
                transmission = contractCarInfo.getTransmission(),
                wheelDrive = contractCarInfo.getWheelDrive(),
                fuelType = contractCarInfo.getFuelType(),
                tankVolumeInGal = contractCarInfo.getTankVolumeInGal(),
                distanceIncludedInMi = contractCarInfo.getDistanceIncludedInMi(),
                pricePerDay = contractCarInfo.getPricePerDay()
            )
        }
    }
}