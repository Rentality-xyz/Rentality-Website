package com.eigenmethod.rentality.views.components

import com.eigenmethod.rentality.constants.MAX_WITH_CONTENT
import com.eigenmethod.rentality.css.cssGradientButton
import com.eigenmethod.rentality.css.cssGradientButtonDisabled
import com.eigenmethod.rentality.navigation_state.ConduitState
import com.eigenmethod.rentality.navigation_state.Pages
import io.kvision.core.Container
import io.kvision.html.button
import io.kvision.html.div
import io.kvision.html.link

fun Container.hostButtonMenu(state: ConduitState) {
    div(className = "flex justify-between mx-auto max-w-[$MAX_WITH_CONTENT] pb-[80px]") {
        id = "host-button-menu"
        getBtnHostListing(state)
        getBtnHostAddCar(state)
        getBtnHostBooked(state)
        getBtnHostHistory(state)
    }
}

private fun Container.getBtnHostListing(state: ConduitState) {
    if (state.page == Pages.HOST_CAR_LISTING) {
        button(text = "Listing", className= cssGradientButtonDisabled) {
            disabled = true
        }
    } else {
        link(label = "", url = "#${Pages.HOST_CAR_LISTING.url}") {
            button(text = "Listing →", className= cssGradientButton)
        }
    }
}

private fun Container.getBtnHostAddCar(state: ConduitState) {
    if (state.page == Pages.HOST_ADD_CAR) {
        button(text = "Add car", className= cssGradientButtonDisabled) {
            disabled = true
        }
    } else {
        link(label = "", url = "#${Pages.HOST_ADD_CAR.url}") {
            button(text = "Add car →", className= cssGradientButton)
        }
    }
}

private fun Container.getBtnHostBooked(state: ConduitState) {
    if (state.page == Pages.HOST_BOOKED) {
        button(text = "Booked", className= cssGradientButtonDisabled) {
            disabled = true
        }
    } else {
        link(label = "", url = "#${Pages.HOST_BOOKED.url}") {
            button(text = "Booked →", className= cssGradientButton)
        }
    }
}

private fun Container.getBtnHostHistory(state: ConduitState) {
    if (state.page == Pages.HOST_HISTORY) {
        button(text = "History", className= cssGradientButtonDisabled) {
            disabled = true
        }
    } else {
        link(label = "", url = "#${Pages.HOST_HISTORY.url}") {
            button(text = "History →", className= cssGradientButton)
        }
    }
}