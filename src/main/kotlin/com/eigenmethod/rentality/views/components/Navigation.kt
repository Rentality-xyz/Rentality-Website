package com.eigenmethod.rentality.views.components

import com.eigenmethod.rentality.constants.HEADER_LINE_H
import com.eigenmethod.rentality.navigation_state.ConduitManager
import com.eigenmethod.rentality.navigation_state.Pages
import io.kvision.core.Container
import io.kvision.core.onEvent
import io.kvision.html.*
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import kotlinx.browser.document

val isActiveMenuMob = ObservableValue(false)
private const val arrowDown = "⮟"
private const val arrowRight = "⮞"
private val arrowDirection = ObservableValue(arrowDown)
var imgMenu = "images/ic-menu-burge-white-20.svg"

fun Container.menuMain() {
    nav(className = "hidden xl:block ml-auto") {
        id = "menu-main"

        div(className = "flex items-center text-base justify-center") {
            // About Project dropdown
            div(className = "relative group").bind(arrowDirection) {
                // Main link for dropdown
                div(content = "About project ${arrowDirection.value}", className = "w-[130px] cursor-pointer mr-6 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                    setEventListener<Div> {
                        mouseover = { e ->
                            e.preventDefault()
                            arrowDirection.value = arrowRight
                        }
                        mouseout = { e ->
                            e.preventDefault()
                            arrowDirection.value = arrowDown
                        }
                    }
                }

                // Dropdown content
                div(className = "absolute hidden group-hover:block bg-[#1E1E30] rounded-lg shadow-md py-2 px-4 z-10 w-[150px]") {
                    setEventListener<Div> {
                        mouseover = { e ->
                            e.preventDefault()
                            arrowDirection.value = arrowRight
                        }
                        mouseout = { e ->
                            e.preventDefault()
                            arrowDirection.value = arrowDown
                        }
                    }
                    link(label = "How it work", url = "", className = "cursor-pointer block mb-2 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                        onClick {
                            it.preventDefault()
                            ConduitManager.redirectPage(Pages.HOME)
                            ConduitManager.redirectUrl("#how_it_work")
                        }
                    }
                    link(label = "Rental process", url = "", className = "cursor-pointer block mb-2 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                        onClick {
                            it.preventDefault()
                            ConduitManager.redirectPage(Pages.HOME)
                            ConduitManager.redirectUrl("#rental_process")
                        }
                    }
                    link(label = "Solution", url = "", className = "cursor-pointer block mb-2 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                        onClick {
                            it.preventDefault()
                            ConduitManager.redirectPage(Pages.HOME)
                            ConduitManager.redirectUrl("#solution")
                        }
                    }
                    link(label = "Roadmap", url = "", className = "cursor-pointer block mb-2 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                        onClick {
                            it.preventDefault()
                            ConduitManager.redirectPage(Pages.HOME)
                            ConduitManager.redirectUrl("#roadmap")
                        }
                    }
                }
            }

            // Remaining menu items
            link(label = "Partners", url = "", className = "cursor-pointer mr-6 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                onClick {
                    it.preventDefault()
                    ConduitManager.redirectPage(Pages.HOME)
                    ConduitManager.redirectUrl("#partners")
                }
            }
            link(label = "Built on", url = "", className = "cursor-pointer mr-6 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                onClick {
                    it.preventDefault()
                    ConduitManager.redirectPage(Pages.HOME)
                    ConduitManager.redirectUrl("#built-on")
                }
            }
            link(label = "Legal matters", url = "", className = "cursor-pointer mr-6 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                onClick {
                    //делаем через onClick, а не через url = "", чтобы не перегружалась страница
                    it.preventDefault()
                    ConduitManager.redirectPage(Pages.LEGAL_MATTERS)
                }
            }
            link(label = "Trip rules", url = "", className = "cursor-pointer mr-6 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                onClick {
                    //делаем через onClick, а не через url = "", чтобы не перегружалась страница
                    it.preventDefault()
                    ConduitManager.redirectPage(Pages.TRIP_RULES)
                }
            }
            link(label = "", url = "https://docs.google.com/forms/d/e/1FAIpQLSdghhZVBqI0xVh8SA1F8O_BjDxuvQnP-sZ4LUR6TahysyOj4g/viewform") {
                button(text = "Join Whitelist ●", className = "inline-flex items-center border px-5 hover:text-black hover:bg-white rounded-full text-sm h-[40px] font-['Montserrat',Arial,sans-serif] font-bold")
            }
            link(label = "", url = "https://demo.rentality.xyz/guest") {
                button(text = "Try Demo ●", className = "inline-flex items-center border px-5 ml-2.5 hover:text-black hover:bg-white rounded-full text-sm h-[40px] font-['Montserrat',Arial,sans-serif] font-bold")
            }

        }
    }
}

fun Container.menuMob() {
    nav(className = "xl:hidden duration-500 fixed top-[-100%] right-0 w-full bg-[#8222DD] z-[100] flex flex-col items-start space-y-4 pl-6 py-2") {
        id = "menu-mob"
        link(label = "How it work", url = "#how_it_work", className = "font-['Montserrat',Arial,sans-serif]") {
            onClick {
                it.preventDefault()
                hideMenuMob()
                ConduitManager.redirectUrl("#how_it_work_mob")
            }
        }
        link(label = "Rental process", url = "#rental_process_mob", className = "font-['Montserrat',Arial,sans-serif]") {
            onClick {
                it.preventDefault()
                hideMenuMob()
                ConduitManager.redirectUrl("#rental_process_mob")
            }
        }
        link(label = "Solution", url = "#solution", className = "font-['Montserrat',Arial,sans-serif]") {
            onClick {
                it.preventDefault()
                hideMenuMob()
                ConduitManager.redirectUrl("#solution")
            }
        }
        link(label = "Roadmap", url = "#roadmap", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    hideMenuMob()
                    ConduitManager.redirectUrl("#roadmap")
                }
            }
        }
        link(label = "Partners", url = "#partners", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    ConduitManager.redirectPage(Pages.HOME)
                    hideMenuMob()
                    ConduitManager.redirectUrl("#partners")
                }
            }
        }
        link(label = "Built on", url = "#built-on", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    ConduitManager.redirectPage(Pages.HOME)
                    hideMenuMob()
                    ConduitManager.redirectUrl("#built-on")
                }
            }
        }
        link(label = "Legal matters", url = "#${Pages.LEGAL_MATTERS.url}", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    hideMenuMob()
                    ConduitManager.redirectPage(Pages.LEGAL_MATTERS)
                }
            }
        }
        link(label = "Trip rules", url = "#${Pages.TRIP_RULES.url}", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    hideMenuMob()
                    ConduitManager.redirectPage(Pages.TRIP_RULES)
                }
            }
        }
        link(label = "Join Whitelist", url = "https://docs.google.com/forms/d/e/1FAIpQLSdghhZVBqI0xVh8SA1F8O_BjDxuvQnP-sZ4LUR6TahysyOj4g/viewform", className = "font-['Montserrat',Arial,sans-serif]")
        link(label = "Try Demo", url = "https://demo.rentality.xyz/guest", className = "font-['Montserrat',Arial,sans-serif]")
    }
}

private fun hideMenuMob() {
    val menuMob = document.getElementById("menu-mob")
    menuMob?.classList?.remove("top-[$HEADER_LINE_H]")
    val body = document.getElementById("body")
    body?.classList?.remove("overflow-hidden")
    body?.classList?.remove("overflow-hidden")
    imgMenu = "images/ic-menu-burge-white-20.svg"
    isActiveMenuMob.value = !isActiveMenuMob.value
//    val targetText = document.getElementById(section)
//    targetText?.scrollIntoView(section)
}