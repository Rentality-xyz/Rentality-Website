package com.eigenmethod.rentality.views

import com.eigenmethod.rentality.AppScope
import com.eigenmethod.rentality.constants.CONTAINER_PX
import com.eigenmethod.rentality.constants.MAX_WITH_CONTENT
import com.eigenmethod.rentality.controllers.sendWagmi2025DataToGoogleTable
import com.eigenmethod.rentality.models.Wagmi2025Data
import com.eigenmethod.rentality.views.components.withProgress
import io.kvision.core.Container
import io.kvision.core.onInput
import io.kvision.form.form
import io.kvision.form.text.textInput
import io.kvision.html.*

private const val cssInput = "pl-5 pr-5 border-b border-[#EFEFEF] bg-white h-[50px] text-base w-full"

private var formInputFirstName: String = ""
private var formInputLastName: String = ""
private var formInputCompanyName: String = ""
private var formInputHotelName: String = ""
private var formInputEMail: String = ""
private var formInputPhone: String = ""
private var formInputDateShuttle: String = ""

fun Container.wagmi2025Page() {
    div(className="mx-auto mt-4 mb-12 $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] text-white font-['Montserrat',Arial,sans-serif]") {
        id = "main-wagmi2025-page"
        image(src = "images/bg_logo_zoya.png", className = "max-xl:hidden absolute top-[1072px] right-0 max-h-[593px]")
//        image(src = "images/bg_arrows_from_logo_zoya.png", className = "max-xl:hidden absolute top-[993px] left-0 max-w-[365px]")

        reserveShuttle()
        footerWagmi()
    }
}

private fun Container.reserveShuttle() {
    div(className="relative z-10 flex flex-col mt-[74px] pl-[35px] pr-[45px] py-[30px] rounded-[20px] bg-white text-[#1E1E32]") {
        id = "reserve-shuttle"
        div(className = "flex max-md:flex-col pb-12 border-b-2 border-[#EFEFEF] md:items-center justify-between") {
            div(className = "flex max-md:flex-col") {
                image(src = "images/ic_reserve_shuttle.svg", className = "max-md:w-[70px]")
                p(className = "md:ml-7 text-[28px] md:text-5xl md:leading-[58px] font-bold") {
                    + "Reserve Your Free"
                    br()
                    + "Shuttle to Wagmi Miami"
                }
            }
            p(className = "text-base md:text-xl font-medium md:leading-7 max-md:mt-6") {
                + "Enjoy web3 car rental service"
                br()
                + "during the conference! Shuttle"
                br()
                + "service is available for January"
                br()
                + "22, 23, and 24, 2025."
            }
        }
        form(className = "mt-8") {
            div {
                div(className = "flex flex-col md:grid grid-cols-2 gap-12 pb-5") {
                    div {
                        label("*First Name", forId = "wagmi_first_name")
                        textInput(type = InputType.TEXT, className = cssInput) {
                            id = "wagmi_first_name"
                            placeholder = "First Name"
                            onInput {
                                it.preventDefault()
                                formInputFirstName = this.value.orEmpty()
                            }
                        }
                    }
                    div {
                        label("*Last name", forId = "wagmi_last_name")
                        textInput(type = InputType.TEXT, className = cssInput) {
                            id = "wagmi_last_name"
                            placeholder = "Last name"
                            onInput {
                                it.preventDefault()
                                formInputLastName = this.value.orEmpty()
                            }
                        }
                    }
                }

                div(className = "flex flex-col md:grid grid-cols-2 gap-12 pb-5") {
                    div {
                        label("*Company Name", forId = "wagmi_company_name")
                        textInput(type = InputType.TEXT, className = cssInput) {
                            id = "wagmi_company_name"
                            placeholder = "Enter your Company"
                            onInput {
                                it.preventDefault()
                                formInputCompanyName = this.value.orEmpty()
                            }
                        }
                    }
                    div {
                        label("*Hotel", forId = "wagmi_hotel")
                        textInput(type = InputType.TEXT, className = cssInput) {
                            id = "wagmi_hotel"
                            placeholder = "Enter the name of your hotel"
                            onInput {
                                it.preventDefault()
                                formInputHotelName = this.value.orEmpty()
                            }
                        }
                    }
                }

                div(className = "flex flex-col md:grid grid-cols-2 gap-12 pb-5") {
                    div {
                        label("*E-mail", forId = "wagmi_email")
                        textInput(type = InputType.TEXT, className = cssInput) {
                            id = "wagmi_email"
                            placeholder = "hello@gmail.com"
                            onInput {
                                it.preventDefault()
                                formInputEMail = this.value.orEmpty()
                            }
                        }
                    }
                    div {
                        label("*Phone Number", forId = "wagmi_phone")
                        textInput(type = InputType.TEXT, className = cssInput) {
                            id = "wagmi_phone"
                            placeholder = "+1(999) 999-9999"
                            onInput {
                                it.preventDefault()
                                formInputPhone = this.value.orEmpty()
                            }
                        }
                    }
                }

                div(className = "flex flex-col md:grid grid-cols-2 gap-12 pb-5") {
                    div {
                        label("*Date of Shuttle service", forId = "wagmi_date_shuttle")
                        textInput(type = InputType.DATE, className = cssInput) {
                            id = "wagmi_date_shuttle"
                            placeholder = "00/00/0000"
                            onInput {
                                it.preventDefault()
                                formInputDateShuttle = this.value.orEmpty()
                            }
                        }
                    }
                }
            }
            button("Submit Request", type = ButtonType.SUBMIT, className = "bg-[#6600CC] text-white pt-0 pb-0 pl-4 pr-4 w-full h-[50px] rounded-full text-xl font-['Montserrat',Arial,sans-serif] font-semibold") {
                onClick {
                    it.preventDefault()
                    AppScope.withProgress {
                        sendWagmi2025DataToGoogleTable(
                                Wagmi2025Data(
                                        firstName = formInputFirstName,
                                        lastName = formInputLastName,
                                        company = formInputCompanyName,
                                        hotel = formInputHotelName,
                                        email = formInputEMail,
                                        phone = formInputPhone,
                                        date = formInputDateShuttle)
                        )
                    }
                }
            }
        }
    }
}

private fun Container.footerWagmi() {
    div(className="flex max-md:flex-col items-center justify-between mt-24 md:mt-48 pt-8 border-t border-[#3A2E47] text-white font-['Montserrat',Arial,sans-serif]") {
        id = "footer-wagmi"
        image(src = "images/logo_rentality_wagmi.svg")
        div("©2024 by Rentality LLC", className = "leading-6 max-md:mt-8")
    }
}