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

private const val min_full_design = "min-[1860px]"

fun Container.lifetimeRewardsPage() {
    div(className = "w-full max-w-[1920px] bg-[url('/images/img_bg_main_lifetime_rewards_mob.png')] xl:bg-[url('/images/img_bg_main_lifetime_rewards.png')] bg-cover bg-no-repeat xl:bg-center font-['Montserrat',Arial,sans-serif]") {
        div(className = "mx-auto flex flex-wrap flex-col max-xl:px-4 xl:flex-row xl:max-w-[86%]") {
            id = "content-lifetime-rewards"
            div(className = "flex items-center w-full h-[$HEADER_LINE_H] xl:h-[140px]").bind(isActiveMenuMob) {
                id = "header-line-lifetime-rewards"
                link(label = "", url = "", className = "w-[200px] h-auto", image = "/images/Logo_rentality.svg") {
                    onClick {
                        //делаем через onClick, а не через url = "${Pages.HOME.url}", чтобы не перегружалась страница
                        it.preventDefault()
                        ConduitManager.redirectPage(Pages.HOME)
                        Pace.stop()
                    }
                }
                image(src = imgMenu, className = "xl:hidden ml-auto") {
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
                menuMobLifetimeRewards()
            }
            div(className ="w-full xl:w-[664px] $min_full_design:w-[830px] xl:ml-auto mt-72 xl:mt-6 $min_full_design:mt-11 mb-[90px]") {
                id = "narrative-lifetime-rewards"
                div(className = "w-full max-xl:text-center text-[32px] xl:text-[64px] $min_full_design:text-[80px] text-white font-bold leading-[120%] xl:leading-[110%] xl:tracking-[-3%]") {
                    p(content = "Join Our Rewards")
                    p(content = "Program for")
                    p(content = "Exclusive Benefits", className = "text-[#24D8D4]")
                }
                div(className = "max-xl:text-center mt-7 xl:mt-9 max-xl:px-4 w-full text-base xl:text-[19px] $min_full_design:text-2xl text-white font-medium") {
                    p {
                        span(content = "01", className = "text-[#24D8D4] font-semibold mr-[22px]")
                        + "Get 20% with promo code WAGMI2025"
                    }
                    p(className = "mt-5 xl:mt-3") {
                        span(content = "02", className = "text-[#24D8D4] font-semibold mr-[22px]")
                        + "Win a FREE 1-day Bentley Bentayga rental in Miami"
                    }
                    p(className = "mt-5 xl:mt-3") {
                        span(content = "03", className = "text-[#24D8D4] font-semibold mr-[22px]")
                        + "Enjoy Lifetime membership in our Premium Rewards Program"
                    }
                }
                div(className = "w-full mt-12 xl:mt-14 bg-[#110D1C66] rounded-3xl backdrop-blur-lg text-white px-[35px] py-[30px]") {
                    p(content = "Register and Unlock Exclusive Benefits!", className = "text-[24px] xl:text-[26px] $min_full_design:text-[32px] font-semibold text-center w-full")
                    div(className = "flex max-xl:flex-col mt-6 w-full gap-3 xl:gap-5 text-base $min_full_design:text-xl font-medium") {
                        link(label = "", url = "https://app.rentality.xyz/guest", className = "w-full h-[50px] $min_full_design:h-[62px]") {
                            button(text = "", className= "bg-[url('/images/menumainbutton20.png')] bg-cover bg-no-repeat bg-center flex items-center justify-center px-4 w-full h-full rounded-full active:opacity-75 active:scale-95 transition duration-150") {
                                + "Launch the App"
                                image(src = "/images/ic_round-get-app.svg", className = "ml-4")
                            }
                        }
                        link(label = "", url = "https://app.rentality.xyz/guest/profile", className = "w-full h-[50px] $min_full_design:h-[62px]") {
                            button(text = "", className= "bg-[url('/images/menumainbutton20.png')] bg-cover bg-no-repeat bg-center flex items-center justify-center px-4 w-full h-full rounded-full active:opacity-75 active:scale-95 transition duration-150") {
                                + "Create profile"
                                image(src = "/images/iconamoon_profile-fill.svg", className = "ml-4")
                            }
                        }
                    }
                    div(className = "mt-6 flex max-xl:flex-col max-xl:text-center items-center justify-between") {
                        div(className = "w-full text-[#FFFFFF59] text-sm $min_full_design:text-lg font-medium") {
                            span(content = "Registration is open until January 31st!", className = "text-[#24D8D4]")
                            + " Results will be posted on our social media pages. Follow us to stay updated!"
                        }
                        div(className = "flex min-w-[120px] max-xl:mt-7 xl:ml-6") {
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
                div(className = "mt-7 xl:mt-9 w-full flex max-xl:flex-col max-xl:w-[200px] max-xl:mx-auto max-xl:text-center items-center justify-between text-sm $min_full_design:text-lg font-medium") {
                    p(className = "text-[#FFFFFF59]") {
                        link("Learn more", url = "https://medium.com/@rentality/rentality-lifetime-rewards-program-16f61b0e27f9", className = "text-[#24D8D4] underline cursor-pointer")
                        + " about the Lifetime Rewards Program"
                    }
                    link("Terms of the Giveaway", url = "https://medium.com/@rentality/terms-of-the-giveaway-2ff6d17efa11", className = "text-[#24D8D4] underline cursor-pointer max-xl:mt-4")
                }
            }
        }
    }
}

private fun Container.menuLifetimeRewards() {
    nav(className = "hidden xl:block ml-auto w-[664px] $min_full_design:w-[830px] text-white text-base") {
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
                link(label = "Launch the App", url = "https://app.rentality.xyz/guest", className = "cursor-pointer block mr-6 hover:underline")
                link(label = "Create profile", url = "https://app.rentality.xyz/guest/profile", className = "cursor-pointer block hover:underline")
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
    nav(className = "xl:hidden duration-500 fixed top-[-100%] right-0 w-full bg-white z-[100] flex flex-col items-center text-black space-y-4 px-6 py-6 font-medium font-['Montserrat',Arial,sans-serif]") {
        id = "menu-mob-lifetime-rewards"
        div(className = "flex items-center justify-between w-full border-b border-[#EFEFEF] pb-6") {
            image(src = "/images/Logo_rentality_black.svg", className = "w-[160px]")
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
        link(label = "About program", url = "") {
            onEvent {
//                click = { e ->
//                    e.preventDefault()
//                    ConduitManager.redirectPage(Pages.HOME)
//                    hideMenuMobLifetimeRewards()
//                    ConduitManager.redirectUrl("${Pages.LIFETIME_REWARDS.url}/#reserve-shuttle")
//                    Pace.stop()
//                }
            }
        }
        link(label = "Launch the App", url = "https://app.rentality.xyz/guest") {
            onEvent {
                click = { hideMenuMobLifetimeRewards() }
            }
        }
        link(label = "Create profile", url = "https://app.rentality.xyz/guest/profile") {
            onEvent {
                click = { hideMenuMobLifetimeRewards() }
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