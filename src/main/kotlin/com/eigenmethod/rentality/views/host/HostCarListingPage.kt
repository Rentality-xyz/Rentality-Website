package com.eigenmethod.rentality.views.host

import com.eigenmethod.rentality.constants.BAGS
import com.eigenmethod.rentality.constants.MAIN_COLOR_BG
import com.eigenmethod.rentality.constants.MAX_WITH_CONTENT
import com.eigenmethod.rentality.constants.PEOPLE
import com.eigenmethod.rentality.controllers.host.getInfoCarsOfCurrentHost
import com.eigenmethod.rentality.css.cssImgCarHostListing
import com.eigenmethod.rentality.models.Car
import com.eigenmethod.rentality.models.UserStatus
import com.eigenmethod.rentality.navigation_state.ConduitManager.routToCarInfoPage
import com.eigenmethod.rentality.navigation_state.ConduitState
import com.eigenmethod.rentality.navigation_state.Pages
import com.eigenmethod.rentality.views.components.hostButtonMenu
import com.eigenmethod.rentality.views.components.withProgress
import io.kvision.core.Container
import io.kvision.core.onClick
import io.kvision.html.button
import io.kvision.html.div
import io.kvision.html.image
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch

fun Container.hostCarListingPage(state: ConduitState) {
    var isCarsLoaded = false
    val cars = ObservableValue<List<Car>>(emptyList())
    CoroutineScope(window.asCoroutineDispatcher())
        .launch {
            withProgress {
                cars.value = getInfoCarsOfCurrentHost()
                isCarsLoaded = true
            }
        }

    div(className = "w-screen text-white") {
        id = "host-content"
        hostButtonMenu(state)
        div(className = "grid grid-cols-2 gap-4 mx-auto max-w-[$MAX_WITH_CONTENT] pb-[80px]").bind(cars) {
            id = "host-listing-cars"
            if (isCarsLoaded) {
                cars.value.forEach { car ->
                    hostListingCarInfo(car)
                }
            } else {
                div("Loading ...")
            }
        }
    }
}

private fun Container.hostListingCarInfo(car: Car) {
    div {
        id = "host-listing-car-info"
        div(className = "relative overflow-hidden") {
            div(car.model.orEmpty(), className = "absolute top-5 left-6 bg-[$MAIN_COLOR_BG]/[.5] px-7 pt-1 pb-2 rounded-[30px] text-white text-xl font-semibold")
            div(className = "cursor-pointer bg-[url('${car.imageURL.orEmpty()}')] bg-cover bg-no-repeat bg-center duration-500 hover:scale-[1.18] max-w-[570px] h-[280px] rounded-2xl") {
                onClick {
                    it.preventDefault()
                    routToCarInfoPage(UserStatus.HOST, Pages.HOST_CAR_LISTING, car)
                }
            }
        }
        div(className = "flex justify-between mt-2.5") {
            div(className = "grid grid-cols-3 items-center bg-[#131320] w-[420px] h-[32px] rounded-lg") {
                div(car.transmission.orEmpty(), className = "flex text-xs") {
                    image(src = "images/icon-speed-box.svg", className = cssImgCarHostListing)
                }
                div("${car.seatsNumber.orEmpty()} $PEOPLE", className = "flex text-xs") {
                    image(src = "images/icon-people.svg", className = cssImgCarHostListing)
                }
                div("${car.trunkSize.orEmpty()} $BAGS", className = "flex text-xs") {
                    image(src = "images/icon-bag.svg", className = cssImgCarHostListing)
                }
            }
            button(text = "View details", className= "bg-button-view-details bg-[#131320] w-[140px] h-[32px] rounded-[10px] text-xs font-['Montserrat',Arial,sans-serif]") {
                onClick {
                    it.preventDefault()
                    routToCarInfoPage(UserStatus.HOST, Pages.HOST_CAR_LISTING, car)
                }
            }
        }
    }
}
