package com.eigenmethod.rentality.views

import com.eigenmethod.rentality.constants.DATE_FORMAT_yyyy_mm_dd
import com.eigenmethod.rentality.constants.MAX_WITH_CONTENT
import com.eigenmethod.rentality.controllers.getHistoryDetails
import com.eigenmethod.rentality.css.cssInputElement
import com.eigenmethod.rentality.models.blockchain.HistoryDetails
import com.eigenmethod.rentality.navigation_state.ConduitState
import com.eigenmethod.rentality.utilites.orIfNull
import com.eigenmethod.rentality.views.components.guestButtonMenu
import com.eigenmethod.rentality.views.components.withProgress
import io.kvision.core.Container
import io.kvision.form.form
import io.kvision.form.text.textInput
import io.kvision.html.InputType
import io.kvision.html.div
import io.kvision.html.label
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import io.kvision.types.LocalDate
import io.kvision.types.toStringF
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch

private val coroutine = CoroutineScope(window.asCoroutineDispatcher())

fun Container.historyDetailsPage(state: ConduitState) {
    val isHistoryDetailsLoaded = ObservableValue(false)
    var historyDetails: HistoryDetails? = null
    coroutine.launch {
        withProgress {
            historyDetails = getHistoryDetails(state.bookedCar?.trips?.tripId.orIfNull { 0 })
            isHistoryDetailsLoaded. value = true
        }
    }

    div(className = "w-screen text-white") {
        id = "guest-content"
        guestButtonMenu(state)
        div(className = "mx-auto max-w-[$MAX_WITH_CONTENT] pb-[80px]").bind(isHistoryDetailsLoaded) {
            id = "history-details"
            if (isHistoryDetailsLoaded.value) {
                getHistoryDetailsItem(historyDetails)
            } else {
                div("Loading ...")
            }
        }
    }
}

private fun Container.getHistoryDetailsItem(historyDetails: HistoryDetails?) {
    form(className = "text-gray-300") {
        div(className = "grid grid-cols-2 gap-4") {
            div(className = "flex justify-between") {
                div {
                    label("Trip Id", forId = "trip_id")
                    textInput(type = InputType.TEXT, className = cssInputElement) {
                        id = "trip_id"
                        readonly = true
                        value = historyDetails?.tripId.orIfNull { "" }.toString()
                    }
                }
                div {
                    label("Car Id", forId = "car_id")
                    textInput(type = InputType.TEXT, className = cssInputElement) {
                        id = "car_id"
                        readonly = true
                        value = historyDetails?.carId.orIfNull { "" }.toString()
                    }
                }
            }

            div {
                label("Status", forId = "status")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "status"
                    readonly = true
                    value = historyDetails?.status.orEmpty()
                }
            }

            div {
                label("Host address", forId = "host_addr")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "host_addr"
                    readonly = true
                    value = historyDetails?.host.orEmpty()
                }
            }

            div {
                label("Guest address", forId = "guest_addr")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "guest_addr"
                    readonly = true
                    value = historyDetails?.host.orEmpty()
                }
            }

            div {
                label("Start date", forId = "start_date")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "start_date"
                    readonly = true
                    value = LocalDate(historyDetails?.startDateTime.orIfNull { "" }.toString().toLongOrNull().orIfNull { 0 }).toStringF(DATE_FORMAT_yyyy_mm_dd)
                }
            }

            div {
                label("End date", forId = "end_date")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "end_date"
                    readonly = true
                    value = LocalDate(historyDetails?.endDateTime.orIfNull { "" }.toString().toLongOrNull().orIfNull { 0 }).toStringF(DATE_FORMAT_yyyy_mm_dd)
                }
            }

            div {
                label("Start Location", forId = "start_location")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "start_location"
                    readonly = true
                    value = historyDetails?.startLocation.orEmpty()
                }
            }

            div {
                label("End Location", forId = "end_location")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "end_location"
                    readonly = true
                    value = historyDetails?.endLocation.orEmpty()
                }
            }

            div {
                label("Miles Included", forId = "milesIncluded")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "milesIncluded"
                    readonly = true
                    value = historyDetails?.milesIncluded.orEmpty()
                }
            }

            div {
                label("Fuel Price Per Gal In Usd", forId = "fuelPricePerGalInUsd")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "fuelPricePerGalInUsd"
                    readonly = true
                    value = historyDetails?.fuelPricePerGalInUsd.orEmpty()
                }
            }

            div {
                label("Approved Date", forId = "approvedDateTime")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "approvedDateTime"
                    readonly = true
                    value = LocalDate(historyDetails?.approvedDateTime.orIfNull { "" }.toString().toLongOrNull().orIfNull { 0 }).toStringF(DATE_FORMAT_yyyy_mm_dd)
                }
            }

            div {
                label("Checked In By Host Date", forId = "checkedInByHostDateTime")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "checkedInByHostDateTime"
                    readonly = true
                    value = LocalDate(historyDetails?.checkedInByHostDateTime.orIfNull { "" }.toString().toLongOrNull().orIfNull { 0 }).toStringF(DATE_FORMAT_yyyy_mm_dd)
                }
            }

            div {
                label("Start Fuel Level In Gal", forId = "startFuelLevelInGal")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "startFuelLevelInGal"
                    readonly = true
                    value = historyDetails?.startFuelLevelInGal.orIfNull { "" }.toString()
                }
            }

            div {
                label("Start Odometer", forId = "startOdometer")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "startOdometer"
                    readonly = true
                    value = historyDetails?.startOdometer.orIfNull { "" }.toString()
                }
            }

            div {
                label("Checked In By Guest Date", forId = "checkedInByGuestDateTime")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "checkedInByGuestDateTime"
                    readonly = true
                    value = LocalDate(historyDetails?.checkedInByGuestDateTime.orIfNull { "" }.toString().toLongOrNull().orIfNull { 0 }).toStringF(DATE_FORMAT_yyyy_mm_dd)
                }
            }

            div {
                label("Checked Out By Guest Date", forId = "checkedOutByGuestDateTime")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "checkedOutByGuestDateTime"
                    readonly = true
                    value = LocalDate(historyDetails?.checkedOutByGuestDateTime.orIfNull { "" }.toString().toLongOrNull().orIfNull { 0 }).toStringF(DATE_FORMAT_yyyy_mm_dd)
                }
            }

            div {
                label("End Fuel Level In Gal", forId = "endFuelLevelInGal")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "endFuelLevelInGal"
                    readonly = true
                    value = historyDetails?.endFuelLevelInGal.orIfNull { "" }.toString()
                }
            }

            div {
                label("End Odometer", forId = "endOdometer")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "endOdometer"
                    readonly = true
                    value = historyDetails?.startOdometer.orIfNull { "" }.toString()
                }
            }

            div {
                label("Checked Out By Host Date", forId = "checkedOutByHostDateTime")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "checkedOutByHostDateTime"
                    readonly = true
                    value = LocalDate(historyDetails?.checkedOutByHostDateTime.orIfNull { "" }.toString().toLongOrNull().orIfNull { 0 }).toStringF(DATE_FORMAT_yyyy_mm_dd)
                }
            }

            div {
                label("Resolve Amount In Usd", forId = "resolveAmountInUsd")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "resolveAmountInUsd"
                    readonly = true
                    value = historyDetails?.resolveAmountInUsd.orIfNull { "" }.toString()
                }
            }

            div {
                label("Payment From", forId = "paymentFrom")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "paymentFrom"
                    readonly = true
                    value = historyDetails?.paymentFrom.orEmpty()
                }
            }

            div {
                label("Payment To", forId = "paymentTo")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "paymentTo"
                    readonly = true
                    value = historyDetails?.paymentTo.orEmpty()
                }
            }

            div {
                label("Price Per Day In Usd Cents", forId = "pricePerDayInUsdCents")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "pricePerDayInUsdCents"
                    readonly = true
                    value = historyDetails?.pricePerDayInUsdCents.orIfNull { "" }.toString()
                }
            }

            div {
                label("Total Day Price In Usd", forId = "totalDayPriceInUsd")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "totalDayPriceInUsd"
                    readonly = true
                    value = historyDetails?.totalDayPriceInUsd.orIfNull { "" }.toString()
                }
            }

            div {
                label("Tax Price In Usd", forId = "taxPriceInUsd")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "taxPriceInUsd"
                    readonly = true
                    value = historyDetails?.taxPriceInUsd.orIfNull { "" }.toString()
                }
            }

            div {
                label("Deposit In Usd", forId = "depositInUsd")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "depositInUsd"
                    readonly = true
                    value = historyDetails?.depositInUsd.orIfNull { "" }.toString()
                }
            }

            div {
                label("Currency Type", forId = "currencyType")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "currencyType"
                    readonly = true
                    value = historyDetails?.currencyType.orIfNull { "" }.toString()
                }
            }

            div {
                label("Eth To Currency Rate", forId = "ethToCurrencyRate")
                textInput(type = InputType.TEXT, className = cssInputElement) {
                    id = "ethToCurrencyRate"
                    readonly = true
                    value = historyDetails?.ethToCurrencyRate.orIfNull { "" }.toString()
                }
            }

        }
    }
}