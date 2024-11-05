package com.eigenmethod.rentality.views.host

import com.eigenmethod.rentality.constants.*
import com.eigenmethod.rentality.controllers.host.getHistoryCarsForHost
import com.eigenmethod.rentality.css.cssImgCarHostListing
import com.eigenmethod.rentality.models.BookedCar
import com.eigenmethod.rentality.models.TripStatus
import com.eigenmethod.rentality.models.UserStatus
import com.eigenmethod.rentality.navigation_state.ConduitManager.routToHistoryDetailsPage
import com.eigenmethod.rentality.navigation_state.ConduitState
import com.eigenmethod.rentality.views.components.hostButtonMenu
import com.eigenmethod.rentality.views.components.withProgress
import io.kvision.core.Col
import io.kvision.core.Color
import io.kvision.core.Container
import io.kvision.core.onClick
import io.kvision.html.button
import io.kvision.html.div
import io.kvision.html.image
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import io.kvision.types.toStringF
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch

private val coroutine = CoroutineScope(window.asCoroutineDispatcher())
private var isHistoryCarsLoaded = ObservableValue(false)
private var historyCars: List<BookedCar> = emptyList()

fun Container.hostHistoryPage(state: ConduitState) {
    setFoundHistoryCars()

    div(className = "w-screen text-white") {
        id = "host-content"
        hostButtonMenu(state)
        div(className = "mx-auto max-w-[$MAX_WITH_CONTENT] pb-[80px]") {
            id = "host-history"
            div(className = "grid grid-cols-2 gap-4").bind(isHistoryCarsLoaded) {
                if (isHistoryCarsLoaded.value) {
                    if (historyCars.isEmpty()) {
                        div("You dont have booked trips")
                    } else {
                        historyCars.forEach { historyCar ->
                            hostListingHistoryCar(historyCar)
                        }
                    }
                } else {
                    div("Loading ...")
                }
            }
        }
    }
}

private fun Container.hostListingHistoryCar(historyCar: BookedCar) {
    val status = TripStatus.values().firstOrNull {
        it.ordinal == historyCar.trips.status
    }?.name.orEmpty()
    div {
        id = "host-search-car-info"
        div(className = "relative overflow-hidden") {
            div(historyCar.rentCar.car.model.orEmpty(), className = "absolute top-[20px] left-6 bg-[$MAIN_COLOR_BG]/[$TRANSPARENCY_PERC] px-7 pt-1 pb-2 rounded-[30px] text-white text-xl font-semibold")
            div("Trip start: ${historyCar.rentCar.dateFrom.toStringF(DATE_FORMAT_yyyy_mm_dd)}", className = "absolute top-[80px] left-6 bg-[$MAIN_COLOR_BG]/[$TRANSPARENCY_PERC] px-7 pt-1 pb-2 rounded-[30px] text-white text-base font-semibold")
            div("Trip end: ${historyCar.rentCar.dateTo.toStringF(DATE_FORMAT_yyyy_mm_dd)}", className = "absolute top-[120px] left-6 bg-[$MAIN_COLOR_BG]/[$TRANSPARENCY_PERC] px-7 pt-1 pb-2 rounded-[30px] text-white text-base font-semibold")
            div("Total \$${historyCar.rentCar.getTotalPrice()}", className = "absolute top-[160px] left-6 bg-[$MAIN_COLOR_BG]/[$TRANSPARENCY_PERC] px-7 pt-1 pb-2 rounded-[30px] text-base font-semibold") {
                color = Color.name(Col.YELLOW)
            }
            div(status, className = "absolute top-[200px] left-6 bg-[$MAIN_COLOR_BG]/[$TRANSPARENCY_PERC] px-7 pt-1 pb-2 rounded-[30px] text-base font-semibold") {
                color = Color.hex(0xc026d3)
            }
            div(className = "cursor-pointer bg-[url('${historyCar.rentCar.car.imageURL.orEmpty()}')] bg-cover bg-no-repeat bg-center duration-500 hover:scale-[1.18] max-w-[570px] h-[280px] rounded-2xl") {
                onClick {
                    it.preventDefault()
                    routToHistoryDetailsPage(UserStatus.HOST, historyCar)
                }
            }
        }
        div(className = "flex justify-between mt-2.5") {
            div(className = "grid grid-cols-3 items-center bg-[#131320] w-[420px] h-[32px] rounded-lg") {
                div(historyCar.rentCar.car.transmission.orEmpty(), className = "flex text-xs") {
                    image(src = "images/icon-speed-box.svg", className = cssImgCarHostListing)
                }
                div("${historyCar.rentCar.car.seatsNumber.orEmpty()} $PEOPLE", className = "flex text-xs") {
                    image(src = "images/icon-people.svg", className = cssImgCarHostListing)
                }
                div("${historyCar.rentCar.car.trunkSize.orEmpty()} $BAGS", className = "flex text-xs") {
                    image(src = "images/icon-bag.svg", className = cssImgCarHostListing)
                }
            }
            button(text = "View details", className= "bg-button-view-details bg-[#131320] w-[140px] h-[32px] rounded-[10px] text-xs font-['Montserrat',Arial,sans-serif]") {
                onClick {
                    it.preventDefault()
                    routToHistoryDetailsPage(UserStatus.HOST, historyCar)
                }
            }
        }
    }
}

private fun setFoundHistoryCars() {
    coroutine.launch {
        withProgress {
            try {
                isHistoryCarsLoaded.value = false
                historyCars = getHistoryCarsForHost()
            } catch (error: Throwable) {
                console.log("setFoundHistoryCars Error = $error")
            } finally {
                isHistoryCarsLoaded.value = true
            }
        }
    }
}