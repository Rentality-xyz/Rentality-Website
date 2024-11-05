package com.eigenmethod.rentality.views

import com.eigenmethod.rentality.constants.BAGS
import com.eigenmethod.rentality.constants.DATE_FORMAT_yyyy_mm_dd
import com.eigenmethod.rentality.constants.MAX_WITH_CONTENT
import com.eigenmethod.rentality.constants.PEOPLE
import com.eigenmethod.rentality.controllers.sendRentCarRequest
import com.eigenmethod.rentality.css.btnRentProcessStyle
import com.eigenmethod.rentality.css.cssBtnRentProcessDisabled
import com.eigenmethod.rentality.css.cssGradientButton
import com.eigenmethod.rentality.css.cssImgCarHostListing
import com.eigenmethod.rentality.models.TripStatus
import com.eigenmethod.rentality.models.UserStatus
import com.eigenmethod.rentality.models.blockchain.RentalityContract
import com.eigenmethod.rentality.navigation_state.ConduitManager
import com.eigenmethod.rentality.navigation_state.ConduitState
import com.eigenmethod.rentality.navigation_state.Pages
import com.eigenmethod.rentality.utilites.orIfNull
import com.eigenmethod.rentality.views.components.guestButtonMenu
import com.eigenmethod.rentality.views.components.hostButtonMenu
import io.kvision.core.*
import io.kvision.html.*
import io.kvision.types.toStringF
import io.kvision.utils.px
import js.core.BigInt
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.await
import kotlinx.coroutines.launch

private val coroutine = CoroutineScope(window.asCoroutineDispatcher())
private val priceDivStyle = Style {
    fontSize = 20.px
    fontWeight = FontWeight.BOLD
}

fun Container.carInfoPage(state: ConduitState) {
    val car = state.car

    div(className = "w-screen text-white") {
        id = "host-content"
        when (state.userStatus) {
            UserStatus.HOST -> hostButtonMenu(state)
            UserStatus.GUEST -> guestButtonMenu(state)
        }

        div(className = "mx-auto max-w-[$MAX_WITH_CONTENT] pb-[80px]") {
            id = "host-car-info"
            div(className = "grid grid-cols-2 gap-4") {
                div(className = "w-full") {
                    div(className = "flex") {
                        div("Details", className = "mr-[20%]")
                        div(className = "grid grid-cols-3 items-center bg-[#131320] w-[350px] h-[32px] rounded-lg") {
                            div(car?.transmission.orEmpty(), className = "flex text-xs") {
                                image(src = "images/icon-speed-box.svg", className = cssImgCarHostListing)
                            }
                            div("${car?.seatsNumber.orEmpty()} $PEOPLE", className = "flex text-xs") {
                                image(src = "images/icon-people.svg", className = cssImgCarHostListing)
                            }
                            div("${car?.trunkSize.orEmpty()} $BAGS", className = "flex text-xs") {
                                image(src = "images/icon-bag.svg", className = cssImgCarHostListing)
                            }
                        }
                    }
                    div(className = "flex mt-7") {
                        div("Features", className = "mr-[20%]")
                        div {
                            p("— ${car?.model.orEmpty()}")
                            p("— ${car?.fuelType.orEmpty()}")
                            p("— Release Year ${car?.releaseYear.orEmpty()}")
                            p("— Bluetooth")
                            p("— Cruise Control")
                            p("— Air Conditioning")
                        }
                    }
                }
                div(className = "w-full") {
                    div(car?.description.orEmpty())
                    when (state.userStatus) {
                        UserStatus.HOST -> {
                            getContentPricesHost(state)
                            if (state.fromPage != Pages.HOST_CAR_LISTING) getBtnForHost(state)
                        }
                        UserStatus.GUEST -> {
                            getContentPricesGuest(state)
                            getBtnForGuest(state)
                        }
                    }
                }
            }

            //так по дизайну, но пока в контрактах есть только одна картинка
//            div(className = "grid grid-cols-3 gap-4 w-full h-auto mt-[80px]") {
//                div(className = "bg-[url('images/tmpCarInfo_1.jpeg')] bg-cover bg-no-repeat bg-center max-w-[375px] h-[280px] rounded-2xl")
//                div(className = "bg-[url('images/tmpCarInfo_2.jpeg')] bg-cover bg-no-repeat bg-center max-w-[375px] h-[280px] rounded-2xl")
//                div(className = "bg-[url('images/tmpCarInfo_3.jpeg')] bg-cover bg-no-repeat bg-center max-w-[375px] h-[280px] rounded-2xl")
//            }
            div(className = "grid grid-cols-2 gap-4 mt-[80px]") {
                div(className = "bg-[url('${car?.imageURL.orEmpty()}')] bg-cover bg-no-repeat bg-center max-w-[570px] h-[280px] rounded-2xl")
                state
                    .bookedCar
                    ?.also { bookedCar ->
                        div {
                            val status = TripStatus.values().firstOrNull {
                                it.ordinal == bookedCar.trips.status.orIfNull { -1 }
                            }?.name.orEmpty()
                            p(status) {
                                color = Color.hex(0xc026d3)
                            }
                            p("Trip start: ${bookedCar.rentCar.dateFrom.toStringF(DATE_FORMAT_yyyy_mm_dd)}")
                            p("Trip end: ${bookedCar.rentCar.dateTo.toStringF(DATE_FORMAT_yyyy_mm_dd)}")
                            p("Pickup location: ${bookedCar.rentCar.location}")
                            p("Return location: ${bookedCar.rentCar.location}")
                        }
                    }

            }
        }
    }
}

private fun Container.getContentPricesHost(state: ConduitState) {
    val rentCar = state.rentCar
    if (state.fromPage == Pages.HOST_BOOKED) {
        div(className = "flex justify-between mt-[50px] mb-[20px]") {
            addCssStyle(priceDivStyle)
            div("\$${rentCar?.car?.pricePerDay.orEmpty()}/day")
            div("\$${rentCar?.deposit.orIfNull { "0" }} deposit")
            div("${rentCar?.getRentalPeriod().orIfNull { 0 }} day(s)")
            div("Total \$${rentCar?.getTotalPrice()}") {
                color = Color.name(Col.YELLOW)
            }
        }
        return
    }

    div("\$${state.car?.pricePerDay.orEmpty()}/day", className = "mt-[50px] mb-[20px]") {
        addCssStyle(priceDivStyle)
    }
}

private fun Container.getContentPricesGuest(state: ConduitState) {
    val rentCar = state.rentCar
    div(className = "flex justify-between mt-[50px] mb-[20px]") {
        addCssStyle(priceDivStyle)
        div("\$${rentCar?.car?.pricePerDay.orEmpty()}/day")
        div("\$${rentCar?.deposit.orIfNull { "0" }} deposit")
        div("${rentCar?.getRentalPeriod().orIfNull { 0 }} day(s)")
        div("Total \$${rentCar?.getTotalPrice()}") {
            color = Color.name(Col.YELLOW)
        }
    }
}

private fun Container.getBtnForGuest(state: ConduitState) {
    div(className = "flex justify-between") {
        getBtnGuestRent(state)
        getBtnGuestStart(state)
        getBtnGuestFinish(state)

    }
}

private fun Container.getBtnGuestRent(state: ConduitState) {
    val bookedCar = state.bookedCar
    if (state.fromPage == Pages.GUEST_SEARCH) {
        link(label = "", url = "#${Pages.GUEST_SEARCH.url}") {
            button(text = "Rent", className= cssGradientButton) {
                addCssStyle(btnRentProcessStyle)
                onClick { ev->
                    ev.preventDefault()
                    state.rentCar?.also { rentCar ->
                        coroutine.launch {
                            try {
                                sendRentCarRequest(rentCar)
                                bookedCar?.rentCar?.car?.also {car ->
                                    ConduitManager.routToCarInfoPage(UserStatus.GUEST, Pages.GUEST_BOOKED, car, bookedCar.rentCar, bookedCar)
                                }
                            } catch (error: Throwable) {
                                console.log("sendRentCarRequest Error = $error")
                            }
                        }
                    }.orIfNull {
                        console.log("clickSendRentCarRequest -> rentCar is null")
                    }

                }
            }
        }
        return
    }

    button(text = "Rent", className= cssBtnRentProcessDisabled) {
        addCssStyle(btnRentProcessStyle)
        disabled = true
    }
}

private fun Container.getBtnGuestStart(state: ConduitState) {
    val bookedCar = state.bookedCar
    val status = state.bookedCar?.trips?.status.orIfNull { -1 }
    if (state.fromPage == Pages.GUEST_BOOKED && status == 2) {
        val tripId = state.bookedCar?.trips?.tripId.orIfNull { 0 }
        button(text = "Start", className= cssGradientButton) {
            addCssStyle(btnRentProcessStyle)
            onClick { ev->
                ev.preventDefault()
                coroutine.launch {
                    try {
                        RentalityContract.checkInByGuest(
                            BigInt(tripId.toString()),
                            BigInt(0),
                            BigInt(0)
                        ).await()
                        bookedCar?.rentCar?.car?.also {car ->
                            ConduitManager.routToCarInfoPage(UserStatus.GUEST, Pages.GUEST_BOOKED, car, bookedCar.rentCar, bookedCar)
                        }
                    } catch (error: Throwable) {
                        console.log("getBtnForGuest Start Error = $error")
                    }
                }
            }
        }
        return
    }

    button(text = "Start", className= cssBtnRentProcessDisabled) {
        addCssStyle(btnRentProcessStyle)
        disabled = true
    }
}

private fun Container.getBtnGuestFinish(state: ConduitState) {
    val bookedCar = state.bookedCar
    val status = state.bookedCar?.trips?.status.orIfNull { -1 }
    if (state.fromPage == Pages.GUEST_BOOKED && status == 3) {
        val tripId = state.bookedCar?.trips?.tripId.orIfNull { 0 }
        button(text = "Finish", className= cssGradientButton) {
            addCssStyle(btnRentProcessStyle)
            onClick { ev->
                ev.preventDefault()
                coroutine.launch {
                    try {
                        RentalityContract.checkOutByGuest(
                            BigInt(tripId.toString()),
                            BigInt(0),
                            BigInt(0)
                        ).await()
                        bookedCar?.rentCar?.car?.also {car ->
                            ConduitManager.routToCarInfoPage(UserStatus.GUEST, Pages.GUEST_BOOKED, car, bookedCar.rentCar, bookedCar)
                        }
                    } catch (error: Throwable) {
                        console.log("getBtnForGuest Finish Error = $error")
                    }
                }
            }
        }
        return
    }

    button(text = "Finish", className= cssBtnRentProcessDisabled) {
        addCssStyle(btnRentProcessStyle)
        disabled = true
    }
}

private fun Container.getBtnForHost(state: ConduitState) {
    div(className = "flex justify-between") {
        getBtnHostApprove(state)
        getBtnHostReject(state)
        getBtnHostStart(state)
        getBtnHostFinish(state)
        getBtnHostComplete(state)
    }
}

private fun Container.getBtnHostApprove(state: ConduitState) {
    val bookedCar = state.bookedCar
    val status = state.bookedCar?.trips?.status.orIfNull { -1 }
    if (state.fromPage == Pages.HOST_BOOKED && status == 0) {
        val tripId = state.bookedCar?.trips?.tripId.orIfNull { 0 }
        button(text = "Approve", className= cssGradientButton) {
            addCssStyle(btnRentProcessStyle)
            onClick { ev->
                ev.preventDefault()
                coroutine.launch {
                    try {
                        RentalityContract.approveTripRequest(
                            BigInt(tripId.toString())
                        ).await()
                        bookedCar?.rentCar?.car?.also {car ->
                            ConduitManager.routToCarInfoPage(UserStatus.HOST, Pages.HOST_BOOKED, car, bookedCar.rentCar, bookedCar)
                        }
                    } catch (error: Throwable) {
                        console.log("getBtnForHost Approve Error = $error")
                    }
                }
            }
        }
        return
    }

    button(text = "Approve", className= cssBtnRentProcessDisabled) {
        addCssStyle(btnRentProcessStyle)
        disabled = true
    }
}

private fun Container.getBtnHostReject(state: ConduitState) {
    val bookedCar = state.bookedCar
    val status = state.bookedCar?.trips?.status.orIfNull { -1 }
    if (state.fromPage == Pages.HOST_BOOKED && status == 0) {
        val tripId = state.bookedCar?.trips?.tripId.orIfNull { 0 }
        button(text = "Reject", className= cssGradientButton) {
            addCssStyle(btnRentProcessStyle)
            onClick { ev->
                ev.preventDefault()
                coroutine.launch {
                    try {
                        RentalityContract.rejectTripRequest(
                            BigInt(tripId.toString())
                        ).await()
                        bookedCar?.rentCar?.car?.also {car ->
                            ConduitManager.routToCarInfoPage(UserStatus.HOST, Pages.HOST_BOOKED, car, bookedCar.rentCar, bookedCar)
                        }
                    } catch (error: Throwable) {
                        console.log("getBtnForHost Reject Error = $error")
                    }
                }
            }
        }
        return
    }

    button(text = "Approve", className= cssBtnRentProcessDisabled) {
        addCssStyle(btnRentProcessStyle)
        disabled = true
    }
}

private fun Container.getBtnHostStart(state: ConduitState) {
    val bookedCar = state.bookedCar
    val status = state.bookedCar?.trips?.status.orIfNull { -1 }
    if (state.fromPage == Pages.HOST_BOOKED && status == 1) {
        val tripId = state.bookedCar?.trips?.tripId.orIfNull { 0 }
        button(text = "Start", className= cssGradientButton) {
            addCssStyle(btnRentProcessStyle)
            onClick { ev->
                ev.preventDefault()
                coroutine.launch {
                    try {
                        RentalityContract.checkInByHost(
                            BigInt(tripId.toString()),
                            BigInt(0),
                            BigInt(0)
                        ).await()
                        bookedCar?.rentCar?.car?.also {car ->
                            ConduitManager.routToCarInfoPage(UserStatus.HOST, Pages.HOST_BOOKED, car, bookedCar.rentCar, bookedCar)
                        }
                    } catch (error: Throwable) {
                        console.log("getBtnForHost Start Error = $error")
                    }
                }
            }
        }
        return
    }

    button(text = "Start", className= cssBtnRentProcessDisabled) {
        addCssStyle(btnRentProcessStyle)
        disabled = true
    }
}

private fun Container.getBtnHostFinish(state: ConduitState) {
    val bookedCar = state.bookedCar
    val status = state.bookedCar?.trips?.status.orIfNull { -1 }
    if (state.fromPage == Pages.HOST_BOOKED && status == 4) {
        val tripId = state.bookedCar?.trips?.tripId.orIfNull { 0 }
        button(text = "Finish", className= cssGradientButton) {
            addCssStyle(btnRentProcessStyle)
            onClick { ev->
                ev.preventDefault()
                coroutine.launch {
                    try {
                        RentalityContract.checkOutByHost(
                            BigInt(tripId.toString()),
                            BigInt(0),
                            BigInt(0)
                        ).await()
                        bookedCar?.rentCar?.car?.also {car ->
                            ConduitManager.routToCarInfoPage(UserStatus.HOST, Pages.HOST_BOOKED, car, bookedCar.rentCar, bookedCar)
                        }
                    } catch (error: Throwable) {
                        console.log("getBtnForHost Finish Error = $error")
                    }
                }
            }
        }
        return
    }

    button(text = "Finish", className= cssBtnRentProcessDisabled) {
        addCssStyle(btnRentProcessStyle)
        disabled = true
    }
}

private fun Container.getBtnHostComplete(state: ConduitState) {
    val bookedCar = state.bookedCar
    val status = state.bookedCar?.trips?.status.orIfNull { -1 }
    if (state.fromPage == Pages.HOST_BOOKED && status == 5) {
        val tripId = state.bookedCar?.trips?.tripId.orIfNull { 0 }
        button(text = "Complete", className= cssGradientButton) {
            addCssStyle(btnRentProcessStyle)
            onClick { ev->
                ev.preventDefault()
                coroutine.launch {
                    try {
                        RentalityContract.finishTrip(
                            BigInt(tripId.toString())
                        ).await()
                        bookedCar?.rentCar?.car?.also {car ->
                            ConduitManager.routToCarInfoPage(UserStatus.HOST, Pages.HOST_BOOKED, car, bookedCar.rentCar, bookedCar)
                        }
                    } catch (error: Throwable) {
                        console.log("getBtnForHost Complete Error = $error")
                    }
                }
            }
        }
        return
    }

    button(text = "Complete", className= cssBtnRentProcessDisabled) {
        addCssStyle(btnRentProcessStyle)
        disabled = true
    }
}

