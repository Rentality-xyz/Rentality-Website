package com.eigenmethod.rentality.views

import com.eigenmethod.rentality.constants.HEADER_LINE_H
import com.eigenmethod.rentality.navigation_state.ConduitManager
import com.eigenmethod.rentality.navigation_state.Pages
import com.eigenmethod.rentality.views.components.imgMenu
import com.eigenmethod.rentality.views.components.isActiveMenuMob
import io.kvision.core.Container
import io.kvision.core.onClick
import io.kvision.core.onEvent
import io.kvision.html.*
import io.kvision.pace.Pace
import io.kvision.state.bind
import kotlinx.browser.document
import kotlinx.dom.addClass

fun Container.lifetimeRewardsPage() {
    div(className = "w-full max-w-[1920px] relative bg-[url('/images/img_main_bg_lifetime_rewards.png')] bg-cover bg-no-repeat bg-center font-['Montserrat',Arial,sans-serif]") {
        image(src = "/images/img_car_lifetime_rewards.png", className = "absolute bottom-10 left-0 h-auto")
        div(className = "mx-auto flex flex-wrap flex-col md:flex-row max-w-[86%]") {
            id = "content-lifetime-rewards"
            div(className = "flex items-center w-screen h-[$HEADER_LINE_H] xl:h-[140px]").bind(isActiveMenuMob) {
                id = "header-line-lifetime-rewards"
                link(label = "", url = "", className = "w-[200px] h-auto", image = "/images/Logo_rentality.svg") {
                    onClick {
                        //делаем через onClick, а не через url = "${Pages.HOME.url}", чтобы не перегружалась страница
                        it.preventDefault()
                        ConduitManager.redirectPage(Pages.HOME)
                        Pace.stop()
                    }
                }
                image(src = imgMenu, className = "xl:hidden ml-auto pr-8") {
                    onClick {
                        it.preventDefault()
                        val menuMob = document.getElementById("menu-mob-lifetime-rewards")
                        val body = document.getElementById("body")
                        menuMob?.addClass("top-[0px]")
                        body?.addClass("overflow-hidden")
                        imgMenu = "/images/ic-menu-burge-close-white-20.svg"
                        isActiveMenuMob.value = true
                    }
                }
                menuLifetimeRewards()
            }
            div(className ="max-w-[830px] ml-auto mt-11 mb-[90px]") {
                id = "narrative-lifetime-rewards"
                div(className = "w-full text-[80px] text-white font-bold leading-[110%] tracking-[-3%]") {
                    p(content = "Join Our Rewards")
                    p(content = "Program for")
                    p(content = "Exclusive Benefits", className = "text-[#24D8D4]")
                }
                div(className = "mt-9 w-full text-[24px] text-white font-medium") {
                    p {
                        span(content = "01", className = "text-[#24D8D4] font-semibold mr-[22px]")
                        + "Get 20% with promo code WAGMI2025"
                    }
                    p(className = "mt-3") {
                        span(content = "02", className = "text-[#24D8D4] font-semibold mr-[22px]")
                        + "Win a FREE 1-day Bentley Bentayga rental in Miami"
                    }
                    p(className = "mt-3") {
                        span(content = "03", className = "text-[#24D8D4] font-semibold mr-[22px]")
                        + "Enjoy Lifetime membership in our Premium Rewards Program"
                    }
                }
                div(className = "w-full mt-14 bg-[#110D1C66] rounded-3xl backdrop-blur-lg text-white px-[35px] py-[30px]") {
                    p(content = "Register and Unlock Exclusive Benefits!", className = "text-[32px] font-semibold text-center w-full")
                    div(className = "flex mt-6 w-full h-[62px] gap-5 text-xl font-medium") {
                        button(text = "", className= "bg-[url('/images/menumainbutton20.png')] bg-cover bg-no-repeat bg-center flex items-center justify-center px-4 w-full h-full rounded-full active:opacity-75 active:scale-95 transition duration-150") {
                            + "Launch the App"
                            image(src = "/images/ic_round-get-app.svg", className = "ml-4")
                            onClick {
                                it.preventDefault()
                                ConduitManager.redirectPage(Pages.WAGMI_2025)
                                ConduitManager.redirectUrl("${Pages.WAGMI_2025.url}/#rentality-app")
                                Pace.stop()
                            }
                        }
                        button(text = "", className= "bg-[url('/images/menumainbutton20.png')] bg-cover bg-no-repeat bg-center flex items-center justify-center px-4 w-full h-full rounded-full active:opacity-75 active:scale-95 transition duration-150") {
                            + "Create profile"
                            image(src = "/images/iconamoon_profile-fill.svg", className = "ml-4")
                            onClick {
                                it.preventDefault()
                                ConduitManager.redirectPage(Pages.WAGMI_2025)
                                ConduitManager.redirectUrl("${Pages.WAGMI_2025.url}/#register-as-a-host")
                                Pace.stop()
                            }
                        }
                    }
                    div(className = "mt-6 flex items-center justify-between") {
                        div(className = "w-full text-lg font-medium") {
                            span(content = "Registration is open until January 31st!", className = "text-[#24D8D4]")
                            + " Results will be posted on our social media pages. Follow us to stay updated!"
                        }
                        div(className = "flex min-w-[120px] ml-6") {
                            link("", url = "https://discord.gg/rentality") {
                                image(src = "/images/social/discord-logo.svg", className = "w-[30px]")
                            }
                            link("", url = "https://t.me/rentality_xyz", className = "ml-4") {
                                image(src = "/images/social/telegram-logo.svg", className = "w-[30px]")
                            }
                            link("", url = "https://www.instagram.com/rentality_/", className = "ml-4") {
                                image(src = "/images/social/instagram-logo.svg", className = "w-[30px]")
                            }
                        }
                    }

                }
                div(className = "mt-9 w-full flex items-center justify-between text-lg font-medium") {
                    p(className = "text-[#FFFFFF59]") {
                        span(content = "Learn more", className = "text-[#24D8D4] underline cursor-pointer")
                        + " about the Lifetime Rewards Program"
                    }
                    p(content = "Terms of the Giveaway", className = "text-[#24D8D4] underline cursor-pointer")
                }
            }
        }
    }
}

private fun Container.menuLifetimeRewards() {
    nav(className = "hidden xl:block ml-auto w-[830px] text-white text-base") {
        id = "menu-lifetime-rewards"
        div(className = "flex w-full items-center justify-between") {
            div(className = "flex") {
                link(label = "About program", url = "", className = "cursor-pointer block mr-6 hover:underline") {
                    onClick {
                        it.preventDefault()
                        ConduitManager.redirectPage(Pages.LIFETIME_REWARDS)
                        ConduitManager.redirectUrl("${Pages.LIFETIME_REWARDS.url}/#about-program")
                        Pace.stop()
                    }
                }
                link(label = "Launch the App", url = "", className = "cursor-pointer block mr-6 hover:underline") {
                    onClick {
                        onClick {
                            it.preventDefault()
                            ConduitManager.redirectPage(Pages.LIFETIME_REWARDS)
                            ConduitManager.redirectUrl("${Pages.LIFETIME_REWARDS.url}/#launch-app")
                            Pace.stop()
                        }
                    }
                }
                link(label = "Create profile", url = "", className = "cursor-pointer block hover:underline") {
                    onClick {
                        onClick {
                            it.preventDefault()
                            ConduitManager.redirectPage(Pages.LIFETIME_REWARDS)
                            ConduitManager.redirectUrl("${Pages.LIFETIME_REWARDS.url}/#create-profile")
                            Pace.stop()
                        }
                    }
                }
            }
            div(className = "flex") {
                link("", url = "https://discord.gg/rentality") {
                    image(src = "/images/social/discord-logo.svg", className = "w-[30px]")
                }
                link("", url = "https://t.me/rentality_xyz", className = "ml-5") {
                    image(src = "/images/social/telegram-logo.svg", className = "w-[30px]")
                }
                link("", url = "https://www.instagram.com/rentality_/", className = "ml-5") {
                    image(src = "/images/social/instagram-logo.svg", className = "w-[30px]")
                }
            }
        }
    }
}

private fun Container.menuMobLifetimeRewards() {
    nav(className = "xl:hidden duration-500 fixed top-[-100%] right-0 w-full bg-white z-[100] flex flex-col items-center text-black space-y-4 px-6 py-6 font-medium") {
        id = "menu-mob-lifetime-rewards"
        div(className = "flex items-center justify-between w-full border-b border-[#EFEFEF] pb-6") {
            image(src = "/images/Logo_rentality.svg", className = "w-[300px]")
            image(src = "/images/ic_close.svg") {
                onClick {
                    it.preventDefault()
                    val menuMob = document.getElementById("menu-mob-lifetime-rewards")
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
                    hideMenuMobLifetimeRewards()
                    ConduitManager.redirectUrl("${Pages.LIFETIME_REWARDS.url}/#reserve-shuttle")
                    Pace.stop()
                }
            }
        }
        link(label = "Rentality App", url = "#rentality-app", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    ConduitManager.redirectPage(Pages.HOME)
                    hideMenuMobLifetimeRewards()
                    ConduitManager.redirectUrl("${Pages.LIFETIME_REWARDS.url}/#rentality-app")
                    Pace.stop()
                }
            }
        }
        link(label = "Become a host", url = "#register-as-a-host", className = "font-['Montserrat',Arial,sans-serif]") {
            onEvent {
                click = { e ->
                    e.preventDefault()
                    ConduitManager.redirectPage(Pages.HOME)
                    hideMenuMobLifetimeRewards()
                    ConduitManager.redirectUrl("${Pages.LIFETIME_REWARDS.url}/#register-as-a-host")
                    Pace.stop()
                }
            }
        }
    }
}

private fun hideMenuMobLifetimeRewards() {
    val menuMob = document.getElementById("menu-mob-lifetime-rewards")
    menuMob?.classList?.remove("top-[0px]")
    val body = document.getElementById("body")
    body?.classList?.remove("overflow-hidden")
    body?.classList?.remove("overflow-hidden")
    imgMenu = "/images/ic-menu-burge-white-20.svg"
    isActiveMenuMob.value = !isActiveMenuMob.value
//    val targetText = document.getElementById(section)
//    targetText?.scrollIntoView(section)
}