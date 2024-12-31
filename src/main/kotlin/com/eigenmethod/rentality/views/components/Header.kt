package com.eigenmethod.rentality.views.components

import com.eigenmethod.rentality.constants.CONTAINER_PX
import com.eigenmethod.rentality.constants.HEADER_LINE_H
import com.eigenmethod.rentality.constants.MAIN_COLOR_BG
import com.eigenmethod.rentality.constants.MAX_WITH_CONTENT
import com.eigenmethod.rentality.models.UserStatus
import com.eigenmethod.rentality.navigation_state.ConduitManager
import com.eigenmethod.rentality.navigation_state.ConduitState
import com.eigenmethod.rentality.navigation_state.Pages
import io.kvision.core.Container
import io.kvision.core.onClick
import io.kvision.html.*
import io.kvision.state.bind
import kotlinx.browser.document
import kotlinx.dom.addClass

fun Container.header() {
    val state = ConduitManager.conduitStore.getState()

    val heightHeader = if (state.page == Pages.WAGMI_2025) {
        "max-md:h-[840px]"
    } else {
        ""
    }

    header(className = "bg-[$MAIN_COLOR_BG] text-white $heightHeader") {
        when (state.page) {
            Pages.WAGMI_2025 -> menuMobWagmi2025()
            else -> menuMob()
        }
        when (state.page) {
            Pages.HOME -> {
                div(className = "bg-[url('images/img_header.png')] bg-cover bg-no-repeat bg-center bg-scroll h-full") {
                    mainHeader(state)
                }
            }
            Pages.WAGMI_2025 -> {
                div(className = "bg-[url('images/img_header_wagmi_mob.png')] md:bg-[url('images/img_header_wagmi.png')] bg-cover bg-no-repeat bg-center bg-scroll max-md:h-[700px] md:min-h-[1080px]") {
                    wagmiHeader(state)
                }
            }
            else -> mainHeader(state)
        }
    }
}

private fun Container.mainHeader(state: ConduitState) {
    div(className = "mx-auto $CONTAINER_PX flex flex-wrap flex-col md:flex-row max-w-[$MAX_WITH_CONTENT]") {
        id = "header-content"
        div(className = "flex items-center w-screen h-[$HEADER_LINE_H] xl:h-[140px]").bind(isActiveMenuMob) {
            id = "header-line"
            link(label = "", url = "", className = "max-w-[200px] min-w-[200px] h-auto", image = "images/Logo_rentality.svg") {
                onClick {
                    //делаем через onClick, а не через url = "${Pages.HOME.url}", чтобы не перегружалась страница
                    it.preventDefault()
                    ConduitManager.redirectPage(Pages.HOME)
                }
            }
            image(src = imgMenu, className = "xl:hidden ml-auto pr-8") {
                onClick {
                    it.preventDefault()
                    val menuMob = document.getElementById("menu-mob")
                    val body = document.getElementById("body")
                    imgMenu = if (isActiveMenuMob.value) {
                        menuMob?.classList?.remove("top-[$HEADER_LINE_H]")
                        body?.classList?.remove("overflow-hidden")
                        "images/ic-menu-burge-white-20.svg"
                    } else {
                        menuMob?.addClass("top-[$HEADER_LINE_H]")
                        body?.addClass("overflow-hidden")
                        "images/ic-menu-burge-close-white-20.svg"
                    }
                    isActiveMenuMob.value = !isActiveMenuMob.value
                }
            }
            menuMain()

        }
        when (state.page) {
            Pages.HOME -> headHomePage()
            else -> breadcrumbs(state)
        }
    }
}

private fun Container.wagmiHeader(state: ConduitState) {
    div(className = "mx-auto $CONTAINER_PX flex flex-wrap flex-col md:flex-row max-w-[$MAX_WITH_CONTENT]") {
        id = "header-content-wagmi"
        div(className = "flex items-center w-screen h-[$HEADER_LINE_H] xl:h-[140px]").bind(isActiveMenuMob) {
            id = "header-line-wagmi"
//            image(src = "images/logo_rentality_wagmi.svg")
            link(label = "", url = "", className = "max-md:w-[300px]", image = "images/logo_rentality_wagmi.svg") {
                onClick {
                    //делаем через onClick, а не через url = "${Pages.HOME.url}", чтобы не перегружалась страница
                    it.preventDefault()
                    ConduitManager.redirectPage(Pages.WAGMI_2025)
                }
            }
            //TODO разкоммитеть, это мобильное меню
//            image(src = imgMenu, className = "xl:hidden ml-auto pr-8") {
//                onClick {
//                    it.preventDefault()
//                    val menuMob = document.getElementById("menu-mob")
//                    val body = document.getElementById("body")
//                    imgMenu = if (isActiveMenuMob.value) {
//                        menuMob?.classList?.remove("top-[$HEADER_LINE_H]")
//                        body?.classList?.remove("overflow-hidden")
//                        "images/ic-menu-burge-white-20.svg"
//                    } else {
//                        menuMob?.addClass("top-[$HEADER_LINE_H]")
//                        body?.addClass("overflow-hidden")
//                        "images/ic-menu-burge-close-white-20.svg"
//                    }
//                    isActiveMenuMob.value = !isActiveMenuMob.value
//                }
//            }
            menuWagmi2025()

        }
        headWagmi2025Page()
    }
}

private fun Container.headHomePage() {
    div(className = "relative w-full") {
        div(className = "mt-2 xl:mt-8") {
            h1(className = "text-[5.8vw] xl:text-[64px] leading-[6.6vw] xl:leading-[77px] text-white font-['Montserrat',Arial,sans-serif] font-bold") {
                + "The first blockchain car rental"
                br()
                + "powered by WEB 3.0"
            }
            div(className = "flex xl:justify-between mb-8 xl:mb-[112px] mt-8 xl:mt-[100px] max-md:text-[3.9vw] max-md:leading-[6vw] text-[20px] leading-[31px] text-white font-['Montserrat',Arial,sans-serif]") {
                div(className = "md:flex items-start") {
                    image(src = "images/rentality_coin.png", className = "float-left w-[50px]")
                    div(className = "max-md:text-base") {
                        + "Transforming car rental by linking real-world assets (RWAs) "
                        br(className = "max-md:hidden")
                        + "with decentralized physical infrastructure networks (DePINs)."
                    }
                }
                div(className = "max-xl:hidden items-center") {
                    buttonToPrototype()
                    marketplace()
                }
            }
            div(className = "justify-start my-11 xl:hidden") {
                buttonToPrototype()
                marketplace()
            }
        }
//        image(src = "images/car_for_hero_web3.png", className="absolute top-[27%] min-[0px]:right-[3%] min-[1066px]:right-[-5%] min-[1100px]:right-[-7%] min-[1150px]:right-[-13%] min-[1190px]:right-[-17%] min-[1280px]:right-[-21%] min-[1440px]:right-[-30%] max-w-[70%]")
    }
}

private fun Container.headWagmi2025Page() {
    div(className = "mt-2 xl:mt-8 text-white w-full font-['Montserrat',Arial,sans-serif]") {
        h1(className = "text-[5.8vw] xl:text-[64px] leading-[6.6vw] xl:leading-[77px] font-bold max-md:text-center") {
            + "Welcome to Rentality,"
            br()
            + "the proud gold partner"
            br()
            + "of Wagmi Miami 2025!"
        }

        p(className = "mt-7 text-xl font-medium leading-7 max-md:text-center") {
            + "We are pioneers in the Web3 car rental industry, and our mission is to"
            br()
            + "make your participation in the conference as comfortable as possible"
        }
        div(className = "bg-gradient-wagmi-exclusive-offer flex max-md:flex-col mt-[280px] md:mt-[354px] rounded-[20px] pl-[35px] pr-[45px] py-[30px] items-center justify-between") {
            div(className = "flex max-md:flex-col") {
                image(src = "images/wagmi_exclusive_offer.svg", className = "max-md:hidden")
                p(className = "md:ml-7 text-base md:text-xl font-medium md:leading-8 max-md:text-center") {
                    + "Exclusive offer for conference attendees: use promo code"
                    br(className = "max-md:hidden")
                    span(content = "WAGMI2025", className = "text-[#5DF4E8]")
                    + " to get  "
                    span(content = "20% off", className = "text-[#5DF4E8]")
                    + " your first ride. The code is"
                    br(className = "max-md:hidden")
                    + "active for 6 months from the start of the conference"
                }
            }
            link(label = "", url = "https://app.rentality.xyz/guest") {
                button(text = "Rent a Car", className = "flex max-md:mt-6 items-center justify-center bg-white rounded-full w-[352px] h-[60px] text-xl text-[#6600CC] font-['Montserrat',Arial,sans-serif] font-semibold") {
                    image(src = "images/maki_car.svg", className = "ml-4")
                }
            }
        }
    }
}

private fun Container.breadcrumbs(state: ConduitState) {
    val title = if (state.userStatus == UserStatus.GUEST) "GUEST" else "HOST"
    val selectedPage = when (state.page) {
        Pages.CAR_INFO -> "car info"
        Pages.HOST_CAR_LISTING -> "listing"
        Pages.HOST_ADD_CAR -> "add car"
        Pages.HOST_BOOKED -> "booked"
        Pages.HOST_HISTORY -> "history"
        Pages.GUEST_SEARCH -> "search"
        Pages.GUEST_BOOKED -> "booked"
        Pages.GUEST_HISTORY -> "history"
        Pages.HISTORY_DETAILS -> "history details"
        else -> ""
    }
    val marginLeftPerc = if (state.userStatus == UserStatus.GUEST) "25%" else "26.9%"
    if (!(state.page.url.contains(Pages.LEGAL_MATTERS.url) || state.page.url.contains(Pages.TRIP_RULES.url) || state.page.url.contains(Pages.WAGMI_2025.url))) {
        div(className = "flex bg-[url('images/bg_breadcrumbs.png')] bg-cover bg-center bg-no-repeat h-[200px] mt-[30px] mb-[50px] container mx-auto max-w-[$MAX_WITH_CONTENT] rounded-2xl border-transparent border-solid") {
            id = "breadcrumbs-content"
            div {
                h1(title, className = "bg-clip-text bg-gradient-title-breadcrumbs mt-5 ml-5 text-transparent bg-clip-text text-5xl font-bold")
                p("$title / $selectedPage", className = "mt-[90px] ml-5")
            }
        }
    }
}

private fun Container.buttonToPrototype() {
    div(className = "xl:flex xl:items-center") {
        link(label = "", url = "https://app.rentality.xyz/guest") {
            button(text = "", className= "border-gradient items-center px-2 w-[240px] h-[50px] rounded-[10px] text-xl font-['Montserrat',Arial,sans-serif] font-semibold mr-[10px]") {
                + "Rent a car"
                span(content = "→", className = "text-[#7F5EE7] ml-3 mb-0.5")
            }
        }
        link(label = "", url = "https://app.rentality.xyz/host") {
            button(text = "", className= "border-gradient items-center px-2 w-[240px] h-[50px] rounded-[10px] text-xl font-['Montserrat',Arial,sans-serif] font-semibold max-xl:mt-4") {
                + "Become a Host"
                span(content = "→", className = "text-[#7F5EE7] ml-3 mb-0.5")
            }
        }
    }
}

private fun Container.marketplace() {
    div(className = "xl:flex xl:items-center justify-around mt-12") {
        link(label = "", url = "https://apps.apple.com/ua/app/rentality/id6736899320") {
            image(src = "images/marketplace/ic_appstore.svg", className = "w-[200px]")
        }
        link(label = "", url = "https://play.google.com/store/apps/details?id=xyz.rentality.rentality") {
            image(src = "images/marketplace/ic_google_play.png", className = "max-xl:mt-4")
        }
    }
}