package com.eigenmethod.rentality.views.components

import com.eigenmethod.rentality.constants.CONTAINER_PX
import com.eigenmethod.rentality.constants.MAX_WITH_CONTENT
import com.eigenmethod.rentality.controllers.sendCommunityDataToGoogleTable
import com.eigenmethod.rentality.css.cssInputFooter
import com.eigenmethod.rentality.models.CommunityData
import com.eigenmethod.rentality.models.ELegalMatters
import com.eigenmethod.rentality.navigation_state.ConduitManager
import com.eigenmethod.rentality.navigation_state.Pages
import io.kvision.core.Container
import io.kvision.core.onInput
import io.kvision.form.form
import io.kvision.form.text.textInput
import io.kvision.html.*
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch

private val coroutine = CoroutineScope(window.asCoroutineDispatcher())
private var formInputName: String = ""
private var formInputEMail: String = ""
private var formInputPhone: String = ""

fun Container.footer() {
    footer(className="bg-[url('/images/bg-gradient-flip.jpg')] bg-cover bg-no-repeat bg-center bg-scroll relative h-[600px] min-[560px]:h-[450px] lg:h-[290px]") {
        image(src = "/images/red-generic-sport-ca.png", className="absolute bottom-0 left-0")
//        div("©2024 by Rentality LLC", className = "absolute bottom-0 left-4 sm:hidden text-white font-['Montserrat',Arial,sans-serif]")
        div(className = "text-white max-w-[$MAX_WITH_CONTENT] mx-auto $CONTAINER_PX flex max-[560px]:flex-col flex-row h-full") {
            id = "footer-content"
            footerLegalMatters()
            footerInfoBlock()
//            footerCommunicBlock()
        }
    }
}

fun Container.footerInfoBlock() {
    div(className = "z-0 flex flex-col lg:pt-[40px] max-lg:mx-auto lg:ml-auto w-max h-full") {
        image(src = "/images/Logo_rentality.svg", className = "max-w-[180px] min-w-[180px] h-auto mb-2")
        div {
            link("", url = "mailto:info@rentality.xyz", className = "max-[560px]:text-center pt-6 lg:pt-9 font-['Montserrat',Arial,sans-serif] text-base font-normal") {
                div("info@rentality.xyz")
            }
            div(className = "flex flex-col mt-1.5 max-[560px]:items-center") {
                div(className = "flex") {
                    link("", url = "https://www.linkedin.com/company/rentalitycorp/?viewAsMember=true") {
                        image(src = "/images/social/linkedin-logo.svg", className = "w-[30px]")
                    }
                    link("", url = "https://twitter.com/Rentality_Info") {
                        image(src = "/images/social/x-logo.svg", className = "ml-1.5 w-[30px]")
                    }
                    link("", url = "https://discord.gg/rentality") {
                        image(src = "/images/social/discord-logo.svg", className = "ml-1.5 w-[30px]")
                    }
//                    link("", url = "mailto:info@rentality.xyz", className = "lg:hidden") {
//                        image(src = "/images/ic-email-50.png", className = "ml-1.5 w-[30px] mt-0.5")
//                    }
                    link("", url = "https://t.me/rentality_xyz") {
                        image(src = "/images/social/telegram-logo.svg", className = "ml-1.5 w-[30px]")
                    }
                }
                div(className = "flex") {
                    link("", url = "https://mirror.xyz/0x263660F0ab0014e956d42f85DccD918bBa2Df587") {
                        image(src = "/images/social/mirror-logo.svg", className = "w-[30px]")
                    }
                    link("", url = "https://warpcast.com/rentality") {
                        image(src = "/images/social/warpcast-logo.svg", className = "ml-1.5 w-[30px]")
                    }
                    link("", url = "https://www.instagram.com/rentality_/") {
                        image(src = "/images/social/instagram-logo.svg", className = "ml-1.5 w-[30px]")
                    }
                    link("", url = "https://medium.com/@rentality") {
                        image(src = "/images/social/medium-logo.svg", className = "ml-1.5 w-[30px]")
                    }
                }
            }
        }
        div("©2024 by Rentality LLC", className = "z-0 mt-auto font-['Montserrat',Arial,sans-serif]")

    }
}

fun Container.footerCommunicBlock() {
    div(className = "pt-4 sm:pt-[70px] w-full") {
        div("Let Us Contact You", className = "text-xl font-semibold pb-[30px]")
        form {
            div {
                div(className = "pb-5") {
                    textInput(type = InputType.TEXT, className = cssInputFooter) {
                        placeholder = "Name"
                        onInput {
                            it.preventDefault()
                            formInputName = this.value.orEmpty()
                        }
                    }
                }
                div(className = "pb-5") {
                    textInput(type = InputType.TEXT, className = cssInputFooter) {
                        placeholder = "Email"
                        onInput {
                            it.preventDefault()
                            formInputEMail = this.value.orEmpty()
                        }
                    }
                }
                div(className = "pb-5") {
                    textInput(type = InputType.TEXT, className = cssInputFooter) {
                        placeholder = "+1(999) 999-9999"
                        onInput {
                            it.preventDefault()
                            formInputPhone = this.value.orEmpty()
                        }
                    }
                }
            }
            button("Submit →", type = ButtonType.SUBMIT, className = "bg-white text-[#7833fb] hover:text-[#0b1019] pt-0 pb-0 pl-4 pr-4 w-[160px] h-[50px] rounded-[10px] text-xl font-['Montserrat',Arial,sans-serif] font-semibold") {
                onClick {
                    it.preventDefault()
                    coroutine.launch {
                        withProgress {
                            sendCommunityDataToGoogleTable(
                                CommunityData(
                                    name = formInputName,
                                    email = formInputEMail,
                                    phoneNumber = formInputPhone)
                            )
                        }
                    }
                }
            }
        }
    }
}

fun Container.footerLegalMatters() {
    div(className = "relative z-0 flex flex-col pt-[30px] lg:pt-[40px] max-lg:mx-auto lg:ml-[540px] min-[1536px]:ml-[540px] min-[1720px]:ml-auto w-max h-2/3 sm:h-full") {
        link(label = "Legal matters", url = "", className = "pb-1 cursor-pointer text-xl font-semibold font-['Montserrat',Arial,sans-serif] hover:underline") {
            onClick {
                //делаем через onClick, а не через url = "", чтобы не перегружалась страница
                it.preventDefault()
                ConduitManager.redirectPage(Pages.LEGAL_MATTERS)
            }
        }
        link(label = "Terms of service", url = "", className = "pb-1.5 cursor-pointer text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
            onClick {
                it.preventDefault()
                ConduitManager.redirectUrl("${Pages.LEGAL_MATTERS.url}/${ELegalMatters.TERMS.value}")
            }
        }
        link(label = "Cancellation policy", url = "", className = "pb-1.5 cursor-pointer text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
            onClick {
                it.preventDefault()
                ConduitManager.redirectUrl("${Pages.LEGAL_MATTERS.url}/${ELegalMatters.CANCELLATION.value}")
            }
        }
        link(label = "Prohibited uses", url = "", className = "pb-1.5 cursor-pointer text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
            onClick {
                it.preventDefault()
                ConduitManager.redirectUrl("${Pages.LEGAL_MATTERS.url}/${ELegalMatters.PROHIBITEDUSES.value}")
            }
        }
        link(label = "Privacy policy", url = "", className = "pb-1.5 cursor-pointer text-base font-['Montserrat',Arial,sans-serif] hover:underline") {
            onClick {
                it.preventDefault()
                ConduitManager.redirectUrl("${Pages.LEGAL_MATTERS.url}/${ELegalMatters.PRIVACY.value}")
            }
        }

        //TrustBox widget - Review Collector
//        div {
//            link(label = "", url = "https://www.trustpilot.com/review/rentality.xyz", target = "_blank") {
//                button(text = "", className= "inline-flex items-center border-gradient px-6 h-10 mt-6 rounded-[10px] text-sm font-['Montserrat',Arial,sans-serif] font-semibold") {
//                    + "Review us"
//                    span(content = "★", className = "text-2xl text-[#5CF4E8] ml-3 mr-1")
//                    + "Trustpilot"
//                    span(content = "●", className = "text-[#7F5EE7] ml-3 mb-0.5")
//                }
//            }
//        }
        div(className = "absolute w-[250px] left-[-50px] bottom-4") {
            div(className = "trustpilot-widget") {
                setAttribute("data-locale", "en-US")
                setAttribute("data-template-id", "56278e9abfbbba0bdcd568bc")
                setAttribute("data-businessunit-id", "67459f54bfd7b10d2666003e")
                setAttribute("data-style-height", "52px")
                setAttribute("data-style-width", "100%")

                link(
                        label = "Trustpilot",
                        url = "https://www.trustpilot.com/review/rentality.xyz",
                        target = "_blank"
                )
            }
        }
    }
}