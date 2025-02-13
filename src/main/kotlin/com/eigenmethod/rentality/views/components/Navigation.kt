package com.eigenmethod.rentality.views.components

import com.eigenmethod.rentality.constants.HEADER_LINE_H
import com.eigenmethod.rentality.constants.RENTALITY_DEMO_URL
import com.eigenmethod.rentality.navigation_state.ConduitManager
import com.eigenmethod.rentality.navigation_state.Pages
import io.kvision.core.Container
import io.kvision.core.onClick
import io.kvision.core.onEvent
import io.kvision.html.*
import io.kvision.pace.Pace
import io.kvision.state.ObservableValue
import kotlinx.browser.document

val isActiveMenuMob = ObservableValue(false)
var imgMenu = "/images/ic-menu-burge-white-20.svg"

fun Container.menuMain() {
    nav(className = "hidden xl:block ml-auto") {
        id = "menu-main"

        div(className = "flex items-center text-base justify-center") {
            // About Project dropdown
            div(className = "relative group") {
                // Main link for dropdown
                div(className = "flex w-[136px] cursor-pointer mr-6 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                    + "About project"
                    image(src = "images/ic_menu_arrow.svg", className = "ml-1.5")
                }

                // Dropdown content
                div(className = "absolute hidden group-hover:block bg-[#1E1E30] rounded-lg shadow-md py-2 px-4 z-10 w-[150px]") {
                    link(label = "How it works", url = "", className = "cursor-pointer block mb-2 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                        onClick {
                            it.preventDefault()
                            ConduitManager.redirectPage(Pages.HOME)
                            ConduitManager.redirectUrl("#how_it_work")
                            Pace.stop()
                        }
                    }
                    link(label = "Rental process", url = "", className = "cursor-pointer block mb-2 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                        onClick {
                            it.preventDefault()
                            ConduitManager.redirectPage(Pages.HOME)
                            ConduitManager.redirectUrl("#rental_process")
                            Pace.stop()
                        }
                    }
                    link(label = "Solution", url = "", className = "cursor-pointer block mb-2 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                        onClick {
                            it.preventDefault()
                            ConduitManager.redirectPage(Pages.HOME)
                            ConduitManager.redirectUrl("#solution")
                            Pace.stop()
                        }
                    }
                    link(label = "Roadmap", url = "", className = "cursor-pointer block mb-2 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                        onClick {
                            it.preventDefault()
                            ConduitManager.redirectPage(Pages.HOME)
                            ConduitManager.redirectUrl("#roadmap")
                            Pace.stop()
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
                    Pace.stop()
                }
            }
            link(label = "Built on", url = "", className = "cursor-pointer mr-6 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                onClick {
                    it.preventDefault()
                    ConduitManager.redirectPage(Pages.HOME)
                    ConduitManager.redirectUrl("#built-on")
                    Pace.stop()
                }
            }
            link(label = "Media", url = "", className = "cursor-pointer mr-6 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                onClick {
                    it.preventDefault()
                    ConduitManager.redirectPage(Pages.HOME)
                    ConduitManager.redirectUrl("#media")
                    Pace.stop()
                }
            }
            link(label = "Legal matters", url = "", className = "cursor-pointer mr-6 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                onClick {
                    //делаем через onClick, а не через url = "", чтобы не перегружалась страница
                    it.preventDefault()
                    ConduitManager.redirectPage(Pages.LEGAL_MATTERS)
                    Pace.stop()
                }
            }
            link(label = "Trip rules", url = "", className = "cursor-pointer mr-6 text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
                onClick {
                    //делаем через onClick, а не через url = "", чтобы не перегружалась страница
                    it.preventDefault()
                    ConduitManager.redirectPage(Pages.TRIP_RULES)
                    Pace.stop()
                }
            }
            link(label = "", url = "https://docs.google.com/forms/d/e/1FAIpQLSdghhZVBqI0xVh8SA1F8O_BjDxuvQnP-sZ4LUR6TahysyOj4g/viewform") {
                button(text = "Join Waitlist ●", className = "inline-flex items-center border px-5 hover:text-black hover:bg-white rounded-full text-sm h-[40px] font-['Montserrat',Arial,sans-serif] font-bold")
            }
            link(label = "", url = "$RENTALITY_DEMO_URL/guest") {
                button(text = "Try Demo ●", className = "inline-flex items-center border px-5 ml-2.5 hover:text-black hover:bg-white rounded-full text-sm h-[40px] font-['Montserrat',Arial,sans-serif] font-bold")
            }

        }
    }
}

fun Container.menuMob() {
    nav(className = "xl:hidden duration-500 fixed top-[-100%] right-0 w-full bg-[#8222DD] z-[100] flex flex-col items-start space-y-4 pl-6 py-2") {
        id = "menu-mob"
        link(label = "How it works", url = "#how_it_work", className = "font-['Montserrat',Arial,sans-serif]") {
            onClick {
                it.preventDefault()
                hideMenuMob()
                ConduitManager.redirectUrl("#how_it_work_mob")
                Pace.stop()
            }
        }
        link(label = "Rental process", url = "#rental_process_mob", className = "font-['Montserrat',Arial,sans-serif]") {
            onClick {
                it.preventDefault()
                hideMenuMob()
                ConduitManager.redirectUrl("#rental_process_mob")
                Pace.stop()
            }
        }
        link(label = "Solution", url = "#solution", className = "font-['Montserrat',Arial,sans-serif]") {
            onClick {
                it.preventDefault()
                hideMenuMob()
                ConduitManager.redirectUrl("#solution")
                Pace.stop()
            }
        }
        link(label = "Roadmap", url = "#roadmap", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    hideMenuMob()
                    ConduitManager.redirectUrl("#roadmap")
                    Pace.stop()
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
                    Pace.stop()
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
                    Pace.stop()
                }
            }
        }
        link(label = "Media", url = "#media", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    ConduitManager.redirectPage(Pages.HOME)
                    hideMenuMob()
                    ConduitManager.redirectUrl("#media")
                    Pace.stop()
                }
            }
        }
        link(label = "Legal matters", url = "#${Pages.LEGAL_MATTERS.url}", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    hideMenuMob()
                    ConduitManager.redirectPage(Pages.LEGAL_MATTERS)
                    Pace.stop()
                }
            }
        }
        link(label = "Trip rules", url = "#${Pages.TRIP_RULES.url}", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    hideMenuMob()
                    ConduitManager.redirectPage(Pages.TRIP_RULES)
                    Pace.stop()
                }
            }
        }
        link(label = "Join Waitlist", url = "https://docs.google.com/forms/d/e/1FAIpQLSdghhZVBqI0xVh8SA1F8O_BjDxuvQnP-sZ4LUR6TahysyOj4g/viewform", className = "font-['Montserrat',Arial,sans-serif]")
        link(label = "Try Demo", url = "$RENTALITY_DEMO_URL/guest", className = "font-['Montserrat',Arial,sans-serif]")
    }
}

private fun hideMenuMob() {
    val menuMob = document.getElementById("menu-mob")
    menuMob?.classList?.remove("top-[$HEADER_LINE_H]")
    val body = document.getElementById("body")
    body?.classList?.remove("overflow-hidden")
    body?.classList?.remove("overflow-hidden")
    imgMenu = "/images/ic-menu-burge-white-20.svg"
    isActiveMenuMob.value = !isActiveMenuMob.value
//    val targetText = document.getElementById(section)
//    targetText?.scrollIntoView(section)
}

fun Container.menuWagmi2025() {
    nav(className = "hidden xl:block ml-auto") {
        id = "menu-wagmi2025"
        div(className = "flex items-center text-base justify-center") {
            button(text = "", className= "border-gradient items-center px-4 h-[40px] rounded-[10px] text-base font-['Montserrat',Arial,sans-serif] mr-[10px] active:opacity-75 active:scale-95 transition duration-150") {
                + "Reserve free shuttle"
                onClick {
                    it.preventDefault()
                    ConduitManager.redirectPage(Pages.WAGMI_2025)
                    ConduitManager.redirectUrl("${Pages.WAGMI_2025.url}/#reserve-shuttle")
                    Pace.stop()
                }
            }
            button(text = "", className= "border-gradient items-center px-4 h-[40px] rounded-[10px] text-base font-['Montserrat',Arial,sans-serif] mr-[10px] active:opacity-75 active:scale-95 transition duration-150") {
                + "Rentality App"
                onClick {
                    it.preventDefault()
                    ConduitManager.redirectPage(Pages.WAGMI_2025)
                    ConduitManager.redirectUrl("${Pages.WAGMI_2025.url}/#rentality-app")
                    Pace.stop()
                }
            }
            button(text = "", className= "border-gradient items-center px-4 h-[40px] rounded-[10px] text-base font-['Montserrat',Arial,sans-serif] mr-[10px] active:opacity-75 active:scale-95 transition duration-150") {
                + "Become a host"
                onClick {
                    it.preventDefault()
                    ConduitManager.redirectPage(Pages.WAGMI_2025)
                    ConduitManager.redirectUrl("${Pages.WAGMI_2025.url}/#register-as-a-host")
                    Pace.stop()
                }
            }
        }
    }
}

fun Container.menuMobWagmi2025() {
    nav(className = "xl:hidden duration-500 fixed top-[-100%] right-0 w-full bg-white z-[100] flex flex-col items-center text-black space-y-4 px-6 py-6 font-medium") {
        id = "menu-mob-wagmi2025"
        div(className = "flex items-center justify-between w-full border-b border-[#EFEFEF] pb-6") {
            image(src = "/images/logo_rentality_wagmi_mob.svg", className = "w-[300px]")
            image(src = "/images/ic_close.svg") {
                onClick {
                    it.preventDefault()
                    val menuMob = document.getElementById("menu-mob-wagmi2025")
                    val body = document.getElementById("body")
                    menuMob?.classList?.remove("top-[0px]")
                    body?.classList?.remove("overflow-hidden")
                    imgMenu = "/images/ic-menu-burge-white-20.svg"
                    isActiveMenuMob.value = false
                }
            }
        }
        link(label = "Reserve free shuttle", url = "#reserve-shuttle", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    ConduitManager.redirectPage(Pages.HOME)
                    hideMenuMobWagmi()
                    ConduitManager.redirectUrl("${Pages.WAGMI_2025.url}/#reserve-shuttle")
                    Pace.stop()
                }
            }
        }
        link(label = "Rentality App", url = "#rentality-app", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    ConduitManager.redirectPage(Pages.HOME)
                    hideMenuMobWagmi()
                    ConduitManager.redirectUrl("${Pages.WAGMI_2025.url}/#rentality-app")
                    Pace.stop()
                }
            }
        }
        link(label = "Become a host", url = "#register-as-a-host", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    ConduitManager.redirectPage(Pages.HOME)
                    hideMenuMobWagmi()
                    ConduitManager.redirectUrl("${Pages.WAGMI_2025.url}/#register-as-a-host")
                    Pace.stop()
                }
            }
        }
    }
}

private fun hideMenuMobWagmi() {
    val menuMob = document.getElementById("menu-mob-wagmi2025")
    menuMob?.classList?.remove("top-[0px]")
    val body = document.getElementById("body")
    body?.classList?.remove("overflow-hidden")
    body?.classList?.remove("overflow-hidden")
    imgMenu = "/images/ic-menu-burge-white-20.svg"
    isActiveMenuMob.value = !isActiveMenuMob.value
//    val targetText = document.getElementById(section)
//    targetText?.scrollIntoView(section)
}