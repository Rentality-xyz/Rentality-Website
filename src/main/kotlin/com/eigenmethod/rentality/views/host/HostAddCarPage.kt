package com.eigenmethod.rentality.views.host

import com.eigenmethod.rentality.constants.MAX_WITH_CONTENT
import com.eigenmethod.rentality.controllers.host.saveCar
import com.eigenmethod.rentality.css.cssInputElement
import com.eigenmethod.rentality.models.Car
import com.eigenmethod.rentality.navigation_state.ConduitState
import com.eigenmethod.rentality.utilites.orIfNull
import com.eigenmethod.rentality.views.components.hostButtonMenu
import com.eigenmethod.rentality.views.components.withProgress
import io.kvision.core.Container
import io.kvision.core.onChange
import io.kvision.form.form
import io.kvision.form.select.selectInput
import io.kvision.form.text.textAreaInput
import io.kvision.form.text.textInput
import io.kvision.form.upload.uploadInput
import io.kvision.html.*
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import io.kvision.types.KFile
import io.kvision.utils.auto
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import org.w3c.dom.url.URL
import org.w3c.files.File

private val coroutine = CoroutineScope(window.asCoroutineDispatcher())
private val enabledSaveBtn = ObservableValue(false)
private var car = Car()
private var isCarSaving = ObservableValue(false)
private var kFile: KFile? = null
private var file: File? = null

fun Container.hostAddCarPage(state: ConduitState) {
    div(className = "w-screen text-white") {
        id = "host-content"
        hostButtonMenu(state)
        div(className = "mx-auto max-w-[$MAX_WITH_CONTENT] pb-[80px]") {
            id = "host-add-car"
            formAddCar()
        }
    }
}

fun Container.formAddCar() {
    form {
        div(className = "grid grid-cols-4 gap-4") {
            div {
                label("VIN Number", forId = "vin_number")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "vin_number"
                    placeholder = "e.g. 4Y1SL65848Z411439"
                    onChange {
                        car.vinNumber = value
                        updateSaveButtonState()
                    }
                }
            }
            div {
                label("Brand", forId = "brand")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "brand"
                    placeholder = "e.g. Shelby"
                    onChange {
                        car.brand = value
                        updateSaveButtonState()
                    }
                }
            }
            div {
                label("Model", forId = "model")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "model"
                    placeholder = "e.g. Mustang GT500"
                    onChange {
                        car.model = value
                        updateSaveButtonState()
                    }
                }
            }
            div {
                label("Year of manufacture", forId = "year_of_manufacture")
                textInput(type = InputType.NUMBER, className = cssInputElement) {
                    id = "year_of_manufacture"
                    placeholder = "e.g. 2023"
                    onChange {
                        car.releaseYear = value
                        updateSaveButtonState()
                    }
                }
            }
        }

        div(className = "grid grid-cols-3 gap-4 mt-10") {
            div {
                label("Car name", forId = "car_name")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "car_name"
                    placeholder = "e.g. Eleanor"
                    onChange {
                        car.name = value
                        updateSaveButtonState()
                    }
                }
            }
            div {
                label("License plate number", forId = "license_plate_number")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "license_plate_number"
                    placeholder = "e.g. ABC-12D"
                    onChange {
                        car.licensePlate = value
                        updateSaveButtonState()
                    }
                }
            }
            div {
                label("State", forId = "state")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "state"
                    placeholder = "e.g. New Jersey"
                    onChange {
                        car.state = value
                        updateSaveButtonState()
                    }
                }
            }
        }

        div(className = "grid grid-cols-6 gap-4 mt-10") {
            div {
                label("Number of seats", forId = "number_of_seats")
                textInput(type = InputType.NUMBER, className = cssInputElement) {
                    id = "number_of_seats"
                    placeholder = "e.g. 5"
                    onChange {
                        car.seatsNumber = value
                        updateSaveButtonState()
                    }
                }
            }
            div {
                label("Number of doors", forId = "number_of_doors")
                textInput(type = InputType.NUMBER, className = cssInputElement) {
                    id = "number_of_doors"
                    placeholder = "e.g. 2"
                    onChange {
                        car.doorsNumber = value
                        updateSaveButtonState()
                    }
                }
            }
            div {
                label("Fuel type", forId = "fuel_type")
                selectInput(className = cssInputElement, options = listOf("1" to "Gasoline", "2" to "Diesel", "3" to "Bio-diesel", "4" to "Electro")) {
                    id = "fuel_type"
                    onChange {
                        car.fuelType = selectedLabel
                        updateSaveButtonState()
                    }
                }
            }
            div {
                label("Tank size", forId = "tank_size")
                textInput(type = InputType.NUMBER, className = cssInputElement) {
                    id = "tank_size"
                    placeholder = "e.g. 16"
                    onChange {
                        car.tankVolumeInGal = value
                        updateSaveButtonState()
                    }
                }
            }
            div {
                label("Transmission", forId = "transmission")
                selectInput(className = cssInputElement, options = listOf("1" to "Manual", "2" to "Automatic")) {
                    id = "transmission"
                    onChange {
                        car.transmission = selectedLabel
                        updateSaveButtonState()
                    }
                }
            }
            div {
                label("Color", forId = "color")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "color"
                    placeholder = "e.g. Gray"
                    onChange {
                        car.color = value
                        updateSaveButtonState()
                    }
                }
            }
        }

        div(className = "grid grid-cols-1 mt-10") {
            label("More about the car", forId = "about_car")
            textAreaInput(className = cssInputElement, rows = 5) {
                id = "about_car"
                height = auto
                placeholder = "e.g. Dupont Pepper Grey 1967 Ford Mustang fastback"
                onChange {
                    car.description = value
                    updateSaveButtonState()
                }
            }
        }

        div(className = "grid grid-cols-2 gap-4 mt-10") {
            div {
                label("Rental price", forId = "rental_price")
                textInput(type = InputType.NUMBER, className = cssInputElement) {
                    id = "rental_price"
                    placeholder = "e.g. 100"
                    onChange {
                        car.pricePerDay = value
                        updateSaveButtonState()
                    }
                }
            }
            div {
                label("Number of miles per day", forId = "number_miles_per_day")
                textInput(type = InputType.NUMBER, className = cssInputElement) {
                    id = "number_miles_per_day"
                    placeholder = "e.g. 200"
                    onChange {
                        car.distanceIncludedInMi = value
                        updateSaveButtonState()
                    }
                }
            }
        }

        val imgAddCircle = "images/add_circle_outline_white_48dp.svg"
        val carImg = ObservableValue(imgAddCircle)
        val fileInputId = "add_photo_file"
        label(forId = fileInputId, className="flex justify-center items-center overflow-hidden mt-10 bg-[#141320] w-[570px] h-[280px] rounded-2xl cursor-pointer text-xl").bind(carImg) {
            val imgMR = if (carImg.value == imgAddCircle) "mr-2" else "mr-0"
            image(src = carImg.value, className = "$imgMR max-w-[570px] rounded-2xl") {
                id = "img_car_photo"
            }
            if (carImg.value == imgAddCircle) {
                + "Add photo"
            }
        }
        uploadInput(multiple = false, accept = listOf("image/*"), className = "hidden") {
            id = fileInputId
            onChange {
                kFile = value?.getOrNull(0)
                file = kFile?.let {this.getNativeFile(it) }
                file?.also { _file ->
//                    console.log("_file.name=${_file.name}")
                    carImg.value = URL.createObjectURL(_file) //создает http адрес для картинки на localhost чтобы сослаться и отобразить картинку
                    car.imageURL = carImg.value
//                    console.log("carImg.value=${carImg.value}")
                    //код ниже возвращает файл в виде строки
//                    val reader = FileReader()
//                    reader.onload = {
//                        val objFile = reader.result.toString()
//                        Unit
//                    }
//                    reader.readAsDataURL(_file)
                }
                updateSaveButtonState()
            }
        }

        div(className = "flex items-center mt-10") {
            button("Save →", type = ButtonType.SUBMIT, className = "mr-7 disabled:bg-gray-500 bg-white enabled:text-[#7833fb] enabled:hover:text-[#0b1019] pt-0 pb-0 pl-4 pr-4 w-[160px] h-[50px] rounded-[10px] enabled:text-xl font-['Montserrat',Arial,sans-serif] enabled:font-semibold").bind(enabledSaveBtn) {
                disabled = !enabledSaveBtn.value
                onClick {
                    it.preventDefault()
                    carSaving()
                }
            }
            div("Please wait... saving may take some time").bind(isCarSaving) {
                visible = isCarSaving.value
            }
        }
    }
}

private fun updateSaveButtonState() {
    enabledSaveBtn.value =
        car.checkAllPropertiesTrue()
}

private fun carSaving() {
    coroutine.launch {
        withProgress {
            try {
                isCarSaving.value = true
                file?.also {
                    saveCar(car, it)
                }.orIfNull {
                    console.log("Image file is null")
                }
            } catch (error: Throwable) {
                console.log("saver car = $error")
            } finally {
                isCarSaving.value = false
            }
        }
    }
}