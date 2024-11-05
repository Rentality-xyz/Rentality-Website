package com.eigenmethod.rentality.views.guest

import com.eigenmethod.rentality.constants.*
import com.eigenmethod.rentality.controllers.guest.searchAvailableCars
import com.eigenmethod.rentality.css.cssImgCarHostListing
import com.eigenmethod.rentality.css.cssInputElement
import com.eigenmethod.rentality.models.RentCar
import com.eigenmethod.rentality.models.UserStatus
import com.eigenmethod.rentality.navigation_state.ConduitManager.routToCarInfoPage
import com.eigenmethod.rentality.navigation_state.ConduitState
import com.eigenmethod.rentality.navigation_state.Pages
import com.eigenmethod.rentality.utilites.orIfNull
import com.eigenmethod.rentality.views.components.guestButtonMenu
import com.eigenmethod.rentality.views.components.withProgress
import io.kvision.core.*
import io.kvision.form.form
import io.kvision.form.text.textInput
import io.kvision.html.*
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import io.kvision.types.LocalDate
import io.kvision.types.toDateF
import io.kvision.types.toStringF
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch

private val coroutine = CoroutineScope(window.asCoroutineDispatcher())
private var location = ""
private var dateFrom = LocalDate()
private var dateTo = LocalDate()
private var isCarsLoaded = ObservableValue(false)
private var rentCars: List<RentCar> = emptyList()

fun Container.guestSearchPage(state: ConduitState) {
    setFoundCars()

    div(className = "w-screen text-white") {
        id = "guest-content"
        guestButtonMenu(state)
        div(className = "mx-auto max-w-[$MAX_WITH_CONTENT] pb-[80px]") {
            id = "guest-search"
            formSearch()
            div(className = "grid grid-cols-2 gap-4").bind(isCarsLoaded) {
                if (isCarsLoaded.value) {
                    if (rentCars.isEmpty()) {
                        div("You dont have listed cars")
                    } else {
                        rentCars.forEach { carInfo ->
                            guestListingAvailableCarInfo(carInfo)
                        }
                    }
                } else {
                    div("Loading ...")
                }
            }
        }
    }
}

private fun Container.formSearch() {
    div(className = "pb-[80px]") {
        form(className = "flex justify-between") {
            div {
                label("Pick up & Return Location", forId = "lab_location")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "lab_location"
                    placeholder = "Miami, FI, United States"
                    location = "Miami, FI, United States"
                    onChange {
                        it.preventDefault()
                        location = value.orEmpty()
                    }
                }
            }
            div {
                label("From", forId = "lab_from")
                textInput(type = InputType.DATE, className = cssInputElement) {
                    id = "lab_from"
                    value = dateFrom.toStringF(DATE_FORMAT_yyyy_mm_dd)
                    onChange {
                        it.preventDefault()
                        dateFrom = value?.toDateF(DATE_FORMAT_yyyy_mm_dd).orIfNull { LocalDate() }
                    }
                }
            }
            div {
                label("To", forId = "lab_to")
                textInput(type = InputType.DATE, className = cssInputElement) {
                    id = "lab_to"
                    value = dateTo.toStringF(DATE_FORMAT_yyyy_mm_dd)
                    onChange {
                        it.preventDefault()
                        dateTo = value?.toDateF(DATE_FORMAT_yyyy_mm_dd).orIfNull { LocalDate() }
                    }
                }
            }
            button(text = "", type = ButtonType.BUTTON, className = "flex items-center border bg-[#805EE7] w-[185px] h-[50px] pl-[4.5%] mt-[27px] rounded-[60px]") {
                image(src = "images/img_search.png", className = "mr-2")
                + "Search"
                onClick {
                    it.preventDefault()
                    setFoundCars()
                }
            }
        }
    }
}

private fun Container.guestListingAvailableCarInfo(rentCar: RentCar) {
    div {
        id = "guest-search-car-info"
        div(className = "relative overflow-hidden") {
            div(rentCar.car.model.orEmpty(), className = "absolute top-[20px] left-6 bg-[$MAIN_COLOR_BG]/[$TRANSPARENCY_PERC] px-7 pt-1 pb-2 rounded-[30px] text-white text-xl font-semibold")
            div("\$${rentCar.car.pricePerDay}/day", className = "absolute top-[80px] left-6 bg-[$MAIN_COLOR_BG]/[$TRANSPARENCY_PERC] px-7 pt-1 pb-2 rounded-[30px] text-white text-base font-semibold")
            div("\$${rentCar.deposit} deposit", className = "absolute top-[120px] left-6 bg-[$MAIN_COLOR_BG]/[$TRANSPARENCY_PERC] px-7 pt-1 pb-2 rounded-[30px] text-white text-base font-semibold")
            div("\$${rentCar.getRentalPeriod()} day(s)", className = "absolute top-[160px] left-6 bg-[$MAIN_COLOR_BG]/[$TRANSPARENCY_PERC] px-7 pt-1 pb-2 rounded-[30px] text-white text-base font-semibold")
            div("Total \$${rentCar.getTotalPrice()}", className = "absolute top-[200px] left-6 bg-[$MAIN_COLOR_BG]/[$TRANSPARENCY_PERC] px-7 pt-1 pb-2 rounded-[30px] text-base font-semibold") {
                color = Color.name(Col.YELLOW)
            }
            div(className = "cursor-pointer bg-[url('${rentCar.car.imageURL.orEmpty()}')] bg-cover bg-no-repeat bg-center duration-500 hover:scale-[1.18] max-w-[570px] h-[280px] rounded-2xl") {
                onClick {
                    it.preventDefault()
                    routToCarInfoPage(UserStatus.GUEST, Pages.GUEST_SEARCH, rentCar.car, rentCar)
                }
            }
        }
        div(className = "flex justify-between mt-2.5") {
            div(className = "grid grid-cols-3 items-center bg-[#131320] w-[420px] h-[32px] rounded-lg") {
                div(rentCar.car.transmission.orEmpty(), className = "flex text-xs") {
                    image(src = "images/icon-speed-box.svg", className = cssImgCarHostListing)
                }
                div("${rentCar.car.seatsNumber.orEmpty()} $PEOPLE", className = "flex text-xs") {
                    image(src = "images/icon-people.svg", className = cssImgCarHostListing)
                }
                div("${rentCar.car.trunkSize.orEmpty()} $BAGS", className = "flex text-xs") {
                    image(src = "images/icon-bag.svg", className = cssImgCarHostListing)
                }
            }
            button(text = "View details", className= "bg-button-view-details bg-[#131320] w-[140px] h-[32px] rounded-[10px] text-xs font-['Montserrat',Arial,sans-serif]") {
                onClick {
                    it.preventDefault()
                    routToCarInfoPage(UserStatus.GUEST, Pages.GUEST_SEARCH, rentCar.car, rentCar)
                }
            }
        }
    }
}

private fun setFoundCars() {
    coroutine.launch {
        withProgress {
            try {
                isCarsLoaded.value = false
                rentCars = searchAvailableCars(location, dateFrom, dateTo).toMutableList()
            } catch (error: Throwable) {
                console.log("searcheCarsError = $error")
            } finally {
                isCarsLoaded.value = true
            }
        }
    }
}