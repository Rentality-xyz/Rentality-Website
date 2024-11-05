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

fun Container.guestButtonMenu(state: ConduitState) {
    div(className = "flex justify-between mx-auto max-w-[$MAX_WITH_CONTENT] pb-[80px]") {
        id = "guest-button-menu"
        getBtnGuestSearch(state)
        getBtnGuestBooked(state)
        getBtnGuestHistory(state)
    }
}

private fun Container.getBtnGuestSearch(state: ConduitState) {
    if (state.page == Pages.GUEST_SEARCH) {
        button(text = "Search", className=cssGradientButtonDisabled) {
            disabled = true
        }
    } else {
        link(label = "", url = "#${Pages.GUEST_SEARCH.url}") {
            button(text = "Search →", className= cssGradientButton)
        }
    }
}

private fun Container.getBtnGuestBooked(state: ConduitState) {
    if (state.page == Pages.GUEST_BOOKED) {
        button(text = "Booked", className=cssGradientButtonDisabled) {
            disabled = true
        }
    } else {
        link(label = "", url = "#${Pages.GUEST_BOOKED.url}") {
            button(text = "Booked →", className=cssGradientButton)
        }
    }
}

private fun Container.getBtnGuestHistory(state: ConduitState) {
    if (state.page == Pages.GUEST_HISTORY) {
        button(text = "History", className=cssGradientButtonDisabled) {

        }
    } else {
        link(label = "", url = "#${Pages.GUEST_HISTORY.url}") {
            button(text = "History →", className=cssGradientButton)
        }
    }
}