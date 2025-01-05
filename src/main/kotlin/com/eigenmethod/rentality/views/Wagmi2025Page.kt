package com.eigenmethod.rentality.views

import com.eigenmethod.rentality.AppScope
import com.eigenmethod.rentality.constants.CONTAINER_PX
import com.eigenmethod.rentality.constants.MAIN_COLOR_BG
import com.eigenmethod.rentality.constants.MAX_WITH_CONTENT
import com.eigenmethod.rentality.controllers.sendWagmi2025DataToGoogleTable
import com.eigenmethod.rentality.models.Wagmi2025Data
import com.eigenmethod.rentality.utilites.isValidEmail
import com.eigenmethod.rentality.utilites.isValidShuttleDate
import com.eigenmethod.rentality.utilites.orZero
import com.eigenmethod.rentality.views.components.baseDialog
import com.eigenmethod.rentality.views.components.withProgress
import io.kvision.core.Container
import io.kvision.core.onClick
import io.kvision.core.onInput
import io.kvision.form.form
import io.kvision.form.text.textInput
import io.kvision.html.*
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLInputElement

private val isVisibleUpBtn = ObservableValue(false)
private var isEmailError = ObservableValue(false)
private var isDateError = ObservableValue(false)

private const val cssInput = "pl-5 pr-5 border-b border-[#EFEFEF] bg-white h-[50px] text-base w-full"

private var formInputFirstName: String = ""
private var formInputLastName: String = ""
private var formInputCompanyName: String = ""
private var formInputHotelName: String = ""
private var formInputEMail: String = ""
private var formInputPhone: String = ""
private var formInputDateShuttle: String = ""

private var isDialogReserveShuttleVisible = ObservableValue(false)


fun Container.wagmi2025Page() {
    window.onscroll = {
        if (document.body?.scrollTop.orZero() > 600 || document.documentElement?.scrollTop.orZero() > 600) {
            isVisibleUpBtn.value = true
        } else {
            isVisibleUpBtn.value = false
        }
    }

    div {
        div(className = "fixed flex justify-center items-center bottom-20 right-10 md:right-20 z-[99] w-[60px] h-[60px] cursor-pointer bg-[url('/images/ellipseUpBtn.png')] bg-cover bg-no-repeat bg-center").bind(isVisibleUpBtn) {
            id = "up-btn"
            visible = isVisibleUpBtn.value
            image(src = "/images/arrUpBtn.png", className = "w-[40px] h-[28px]")
        }
        onClick { ev ->
            ev.preventDefault()
            val scrollStep = window.scrollY / 10
            scrollToTop(scrollStep)
        }
    }

    image(src = "/images/img_phone_wagmi_mob.png", className = "md:hidden absolute min-[360px]:top-[330%] min-[380px]:top-[320%] min-[410px]:top-[315%] left-0 w-full")

    div(className="mx-auto mt-4 $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] text-white font-['Montserrat',Arial,sans-serif]") {
        id = "main-wagmi2025-page"
        image(src = "/images/bg_logo_zoya.png", className = "max-xl:hidden absolute top-[1072px] right-0 max-h-[593px]")
        image(src = "/images/bg_arrows_from_logo_zoya.png", className = "max-xl:hidden absolute top-[1532px] left-0 max-w-[365px]")
        image(src = "/images/bg_logo_zoya.png", className = "max-xl:hidden absolute top-[2232px] right-0 max-h-[593px]")
        image(src = "/images/bg_arrows_from_logo_zoya.png", className = "max-xl:hidden absolute top-[2542px] left-0 max-w-[365px]")

        sectionReserveShuttle()
        sectionRentalityApp()
        sectionBecomeHost()
        div(className = "mt-20 md:mt-44 text-[28px] md:text-[64px] md:leading-[76px] w-full text-center font-bold") {
            + "Why List Your "
            br(className = "max-md:hidden")
            + "Caron Rentality?"
        }
    }

    div(className = "w-full md:bg-[url('/images/bg_avto_103_wagmi.png')] bg-cover bg-no-repeat bg-center font-['Montserrat',Arial,sans-serif]") {
        div(className="mt-8 mx-auto $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] text-white") {
            sectionListCaronRentality()
        }
    }

    div(className="mx-auto $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] bg-[$MAIN_COLOR_BG] text-white font-['Montserrat',Arial,sans-serif]") {
        sectionHowToGetStarted()
    }

    div(className = "relative w-full font-['Montserrat',Arial,sans-serif]") {
        div(className="mt-8 mx-auto $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] text-white") {
            sectionRegisterAsHost()
        }
        image(src = "/images/img_car_register_host_wagmi_mob.png", className = "md:hidden w-full")
        image(src = "/images/img_car_register_host_wagmi.png", className = "max-md:hidden absolute top-[-65%] right-0 w-[1078px] min-[1537px]:w-[1278px]")
    }

    div(className="mx-auto $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] bg-[$MAIN_COLOR_BG] text-white font-['Montserrat',Arial,sans-serif]") {
        footerWagmi()
    }

    div().bind(isDialogReserveShuttleVisible) {
        if (isDialogReserveShuttleVisible.value) {
            showDialogReserveShuttle()
        }
    }
}

private fun Container.sectionReserveShuttle() {
    div(className="relative z-20 flex flex-col mt-[74px] pl-[35px] pr-[45px] py-[30px] rounded-[20px] bg-white text-[#1E1E32]") {
        id = "reserve-shuttle"
        div(className = "flex max-md:flex-col pb-12 border-b-2 border-[#EFEFEF] md:items-center justify-between") {
            div(className = "flex max-md:flex-col") {
                image(src = "/images/ic_reserve_shuttle.svg", className = "max-md:w-[70px]")
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
                        label("*Address", forId = "wagmi_hotel")
                        textInput(type = InputType.TEXT, className = cssInput) {
                            id = "wagmi_hotel"
                            placeholder = "Enter your address"
                            onInput {
                                it.preventDefault()
                                formInputHotelName = this.value.orEmpty()
                            }
                        }
                    }
                }

                div(className = "flex flex-col md:grid grid-cols-2 gap-12 pb-5") {
                    div {
                        div().bind(isEmailError) {
                            label("*E-mail", forId = "wagmi_email", className = if (isEmailError.value) "text-red-600" else "")
                        }
                        textInput(type = InputType.EMAIL, className = cssInput) {
                            id = "wagmi_email"
                            placeholder = "hello@gmail.com"
                            value = formInputEMail
                            onInput {
                                it.preventDefault()
                                isEmailError.value = false
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
                        div().bind(isDateError) {
                            label("*Date of Shuttle service", forId = "wagmi_date_shuttle", className = if (isDateError.value) "text-red-600" else "")
                        }
                        textInput(type = InputType.DATE, className = cssInput) {
                            id = "wagmi_date_shuttle"
                            placeholder = "00/00/0000"
                            onInput {
                                it.preventDefault()
                                isDateError.value = false
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
                        if (!isValidEmail(formInputEMail)) {
//                            window.alert("Invalid EMail")
                            val inputElement = document.getElementById("wagmi_email") as? HTMLInputElement
                            inputElement?.focus()
                            isEmailError.value = true
                            return@withProgress
                        }

                        if (!isValidShuttleDate(formInputDateShuttle)) {
//                            window.alert("Invalid Date")
                            val inputElement = document.getElementById("wagmi_date_shuttle") as? HTMLInputElement
                            inputElement?.focus()
                            isDateError.value = true
                            return@withProgress
                        }

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

                        // Очистка данных формы
                        formInputFirstName = ""
                        formInputLastName = ""
                        formInputCompanyName = ""
                        formInputHotelName = ""
                        formInputEMail = ""
                        formInputPhone = ""
                        formInputDateShuttle = ""

                        // Очистка содержимого текстовых полей
                        val inputs = listOf(
                                "wagmi_first_name",
                                "wagmi_last_name",
                                "wagmi_company_name",
                                "wagmi_hotel",
                                "wagmi_email",
                                "wagmi_phone",
                                "wagmi_date_shuttle"
                        )

                        inputs.forEach { id ->
                            val inputElement = document.getElementById(id) as? HTMLInputElement
                            inputElement?.value = ""
                        }

                        // Показать диалог
                        isDialogReserveShuttleVisible.value = true
                    }
                }
            }
        }
    }
}

private fun Container.sectionRentalityApp() {
    div(className="relative z-10 flex max-md:flex-col mt-14 md:mt-56 h-[700px] md:h-[560px]") {
        id = "rentality-app"
        div(className = "relative z-10 flex flex-col max-md:items-center") {
            p(className = "text-[28px] md:text-[64px] font-bold md:leading-[76px]") {
                + "Get the"
                br(className = "max-md:hidden")
                + "Rentality App"
            }
            p(className = "text-base md:text-xl font-medium md:leading-7 mt-2 md:mt-6 max-md:text-center") {
                + "User-friendly interface and access to all features in one"
                br(className = "max-md:hidden")
                + "place. Download our app to simplify your interactions"
            }
            link(label = "", url = "https://apps.apple.com/ua/app/rentality/id6736899320", className = "max-md:w-full w-fit") {
                button(text = "Download for iOS", className = "flex mt-8 md:mt-14 items-center justify-center bg-white rounded-full max-md:w-full md:w-[405px] h-[60px] text-xl text-[#6600CC] font-['Montserrat',Arial,sans-serif] font-semibold") {
                    image(src = "/images/ic_apple_wagmi.svg", className = "ml-4")
                }
            }
            link(label = "", url = "https://play.google.com/store/apps/details?id=xyz.rentality.rentality", className = "max-md:w-full w-fit") {
                button(text = "Download for Android", className = "flex mt-4 md:mt-6 items-center justify-center bg-white rounded-full max-md:w-full md:w-[405px] h-[60px] text-xl text-[#6600CC] font-['Montserrat',Arial,sans-serif] font-semibold") {
                    image(src = "/images/ic_android_wagmi.svg", className = "ml-4")
                }
            }
        }
        image(src = "/images/img_phone_wagmi.png", className = "max-md:hidden absolute top-[-310px] right-[-200px]")
    }
}

private fun Container.sectionBecomeHost() {
    div(className="relative z-10 flex max-md:text-center md:items-center md:justify-end p-6 md:p-14 mt-56 h-[537px] md:h-[456px] bg-[url('/images/bg_wagmi_become_host_mob.png')] md:bg-[url('/images/bg_wagmi_become_host.png')] bg-cover bg-center bg-no-repeat") {
        id = "become-a-host"
        div(className = "flex flex-col") {
            p(className = "text-[28px] md:text-[60px] font-bold md:leading-[76px]") {
                + "Host Your Car "
                br(className = "max-md:hidden")
                + "with Rentality"
            }
            p(className = "text-base md:text-xl font-medium md:leading-7 mt-6") {
                + "Becoming a car host has never been easier "
                br(className = "max-md:hidden")
                + "or more profitable"
            }
            p(className = "text-base md:text-xl font-medium md:leading-7 mt-4") {
                + "Rentality allows you to turn your car into a reliable "
                br(className = "max-md:hidden")
                + "source of income by offering the ultimate "
                br(className = "max-md:hidden")
                + "convenience of Web3 technology"
            }
        }
    }
}

private fun Container.sectionListCaronRentality() {
    div(className="relative z-10 flex-col md:grid grid-cols-2 gap-4") {
        id = "list-caron-rentality"

        div(className = "relative w-full overflow-hidden") {
            image(src = "/images/rectangle_midnight_purple_without_shadow_not_transparent.png", className = "max-md:hidden absolute w-full h-full object-fill")
            image(src = "/images/rectangle_midnight_purple_without_shadow_not_transparent_mob.png", className = "md:hidden absolute w-full h-full object-fill")
            div(className = "w-full h-full px-[25px] py-[35px] md:p-[50px]") {
                div(className = "flex justify-between") {
                    image(src = "/images/img_wagmi_low_commission_rates.svg", className = "relative z-10 max-md:w-[70px]")
                    p(className = "relative z-10 bg-gradient-wagmi-exclusive-offer rounded-xl py-[10px] md:py-[20px] px-[20px] md:px-[40px] w-[70%] md:w-[80%] text-xs md:text-base") {
                        + "Traditional platforms charge as much as 40% in hidden fees—Rentality has none"
                    }
                }
                p(className = "text-[24px] md:text-[28px] font-semibold relative z-10 mt-4 md:mt-10") {
                    + "Low Commission Rates"
                }
                p(className = "text-base md:text-xl font-medium relative z-10 mt-2 md:mt-6") {
                    + "Rentality offers a competitive 20% commission rate with plans to lower it to 0%, so you keep more of your earnings"
                }
            }
        }

        div(className = "relative w-full overflow-hidden max-md:mt-4") {
            image(src = "/images/rectangle_midnight_purple_without_shadow_not_transparent.png", className = "max-md:hidden absolute w-full h-full object-fill")
            image(src = "/images/rectangle_midnight_purple_without_shadow_not_transparent_mob.png", className = "md:hidden absolute w-full h-full object-fill")
            div(className = "w-full h-full px-[25px] py-[35px] md:p-[50px]") {
                image(src = "/images/img_wagmi_instant_payouts.svg", className = "relative z-10 max-md:w-[70px]")
                p(className = "text-[24px] md:text-[28px] font-semibold relative z-10 mt-4 md:mt-10") {
                    + "Instant Payouts"
                }
                p(className = "text-base md:text-xl font-medium relative z-10 mt-2 md:mt-6") {
                    + "No need to wait for days or weeks. Thanks to blockchain technology, you'll receive instant payouts after every transaction"
                }
            }
        }

        div(className = "relative w-full overflow-hidden max-md:mt-4") {
            image(src = "/images/rectangle_midnight_purple_without_shadow_not_transparent.png", className = "max-md:hidden absolute w-full h-full object-fill")
            image(src = "/images/rectangle_midnight_purple_without_shadow_not_transparent_mob.png", className = "md:hidden absolute w-full h-full object-fill")
            div(className = "w-full h-full px-[25px] py-[35px] md:p-[50px]") {
                image(src = "/images/img_wagmi_no_middlemen.svg", className = "relative z-10 max-md:w-[70px]")
                p(className = "text-[24px] md:text-[28px] font-semibold relative z-10 mt-4 md:mt-10") {
                    + "No Middlemen"
                }
                p(className = "text-base md:text-xl font-medium relative z-10 mt-2 md:mt-6") {
                    + "Rentality eliminates intermediaries. Every deal is a direct agreement between you and the guest"
                }
            }
        }

        div(className = "relative w-full overflow-hidden max-md:mt-4") {
            image(src = "/images/rectangle_midnight_purple_without_shadow_not_transparent.png", className = "max-md:hidden absolute w-full h-full object-fill")
            image(src = "/images/rectangle_midnight_purple_without_shadow_not_transparent_mob.png", className = "md:hidden absolute w-full h-full object-fill")
            div(className = "w-full h-full px-[25px] py-[35px] md:p-[50px]") {
                image(src = "/images/img_wagmi_secure_transactions.svg", className = "relative z-10 max-md:w-[70px]")
                p(className = "text-[24px] md:text-[28px] font-semibold relative z-10 mt-4 md:mt-10") {
                    + "Transparent, Secure Transactions"
                }
                p(className = "text-base md:text-xl font-medium relative z-10 mt-2 md:mt-6") {
                    + "All transactions are carried out using smart contracts, which provides 100% transparent and eliminates the risk of fraud"
                }
            }
        }

        div(className = "relative w-full overflow-hidden max-md:mt-4") {
            image(src = "/images/rectangle_midnight_purple_without_shadow_not_transparent.png", className = "max-md:hidden absolute w-full h-full object-fill")
            image(src = "/images/rectangle_midnight_purple_without_shadow_not_transparent_mob.png", className = "md:hidden absolute w-full h-full object-fill")
            div(className = "w-full h-full px-[25px] py-[35px] md:p-[50px]") {
                image(src = "/images/img_wagmi_earn_more.svg", className = "relative z-10 max-md:w-[70px]")
                p(className = "text-[24px] md:text-[28px] font-semibold relative z-10 mt-4 md:mt-10") {
                    + "Earn More"
                }
                p(className = "text-base md:text-xl font-medium relative z-10 mt-2 md:mt-6") {
                    + "With direct bookings and blockchain efficiency, your profits are maximized"
                }
            }
        }

        div(className = "relative w-full overflow-hidden max-md:mt-4") {
            image(src = "/images/rectangle_midnight_purple_without_shadow_not_transparent.png", className = "max-md:hidden absolute w-full h-full object-fill")
            image(src = "/images/rectangle_midnight_purple_without_shadow_not_transparent_mob.png", className = "md:hidden absolute w-full h-full object-fill")
            div(className = "w-full h-full px-[25px] py-[35px] md:p-[50px]") {
                image(src = "/images/img_wagmi_premium_audience.svg", className = "relative z-10 max-md:w-[70px]")
                p(className = "text-[24px] md:text-[28px] font-semibold relative z-10 mt-4 md:mt-10") {
                    + "Reach a Premium Audience"
                }
                p(className = "text-base md:text-xl font-medium relative z-10 mt-2 md:mt-6") {
                    + "Rentality connects you with tech-savvy, crypto-powered renters who value quality and convenience"
                }
            }
        }
    }
}

private fun Container.sectionHowToGetStarted() {
    div(className="relative z-10 mt-20 md:mt-36") {
        id = "how-to-get-started"
        h2(content = "How to Get Started", className = "text-[28px] md:text-[64px] font-bold text-center")

        div(className = "md:grid w-full grid-cols-3 gap-2 mt-11 md:mt-24") {
            div(className = "relative flex flex-col items-center") {
                image(src = "/images/ic_plus_wagmi.png")
                h3(content = "Create an Account", className = "text-[24px] md:text-[28px] font-semibold mt-7 md:mt-11")
                p(className = "text-base md:text-xl font-medium text-center mt-3") {
                    + "Signing up is fast, "
                    br(className = "max-,d:hidden")
                    + "free, and easy"
                }
                div(className="max-md:mt-8 md:absolute right-[-5px] top-1/2 w-[80%] md:h-[80%] max-md:h-px md:w-px translate-y-[-50%] bg-[#64616B]")
            }
            div(className = "relative flex flex-col items-center max-md:mt-8") {
                image(src = "/images/ic_car_wagmi.png")
                h3(content = "List Your Car", className = "text-[24px] md:text-[28px] font-semibold mt-[30px] md:mt-[55px]")
                p(className = "text-base md:text-xl font-medium text-center mt-3") {
                    + "Upload photos, set your price, "
                    br(className = "max-,d:hidden")
                    + "and customize your availability"
                }
                div(className="max-md:mt-8 md:absolute right-[-5px] top-1/2 w-[80%] md:h-[80%] max-md:h-px md:w-px translate-y-[-50%] bg-[#64616B]")
            }
            div(className = "flex flex-col items-center max-md:mt-8") {
                image(src = "/images/ic_dollar_wagmi.png")
                h3(content = "Start Earning", className = "text-[24px] md:text-[28px] font-semibold mt-8 md:mt-9")
                p(className = "text-base md:text-xl font-medium text-center mt-3") {
                    + "Watch your car work for you "
                    br(className = "max-,d:hidden")
                    + "while Rentality handles the rest"
                }
            }
        }

        p(className = "w-full text-base md:text-xl text-[#64616B] text-center font-medium mt-[54px] md:mt-[60px]") {
            + "More detailed instructions on how to list your car can be found "
            span(className = "cursor-pointer underline text-[#805FE8]") {
                link(label = "here", url = "https://medium.com/@rentality/a-step-by-step-guide-to-becoming-a-host-on-rentality-0d30e6b673ae")
            }
        }
    }
}

private fun Container.sectionRegisterAsHost() {
    div(className="relative z-10 mt-20 md:mt-36") {
        id = "register-as-a-host"
        h2(className = "text-[28px] md:text-[64px] font-bold leading-[120%] max-md:text-center") {
            + "Ready to Earn "
            br(className = "max-md:hidden")
            + "More with "
            br(className = "max-md:hidden")
            + "Less Effort?"
        }
        link(label = "", url = "https://demo.rentality.xyz/host/profile", className = "max-md:w-full") {
            button(text = "Register as a Host", className = "flex max-md:mt-6 mt-14 items-center justify-center bg-white rounded-full max-md:w-full md:w-[405px] h-[60px] text-xl text-[#6600CC] font-semibold") {
                image(src = "/images/tabler_arrow_right.svg", className = "ml-4")
            }
        }
    }
}


// Функция для отображения диалогового окна
private fun Container.showDialogReserveShuttle() {
    baseDialog {
        div(className = "flex flex-col items-center bg-white rounded-[20px] p-8 md:p-14 w-[90%] md:w-[750px] text-center text-[#1E1E32]") {
            div(className = "relative flex w-full h-[70px] md:h-[110px] mb-4 md:mb-8") {
                image(src = "/images/img_car_dialog_reserve_shuttle.svg", className = "absolute left-[40%] md:left-[266px] max-md:w-[70px] cursor-pointer")
                image(src = "/images/ic_close.svg", className = "absolute right-0 w-[20px] md:w-[25px] cursor-pointer") {
                    onClick {
                        isDialogReserveShuttleVisible.value = false
                    }
                }
            }
            p("Thank you! Your shuttle has been reserved", className = "text-[28px] md:text-5xl leading-[33px] md:leading-[58px] font-bold mb-4 md:mb-8")
            p("You will receive an email confirmation shortly.", className = "text-base md:text-xl font-medium mb-6 md:mb-10 leading-[22px] md:leading-7")
            div(className = "flex flex-col md:grid md:grid-cols-2 md:gap-12") {
                button(text = "", className = "max-md:mb-4 py-2 flex items-center justify-center rounded-full text-base md:text-xl font-semibold") {
                    onClick {
                        it.preventDefault()
//                        ConduitManager.redirectPage(Pages.HOME)
                        isDialogReserveShuttleVisible.value = false
                    }
                    image(src = "/images/tabler_arrow_left.svg", className = "mr-4")
                    span("Return to home page")
                }
                link(label = "", url = "https://app.rentality.xyz/guest", className = "") {
                    button("Explore more", className = "p-2 bg-[#6600CC] w-full text-white text-base md:text-xl font-semibold rounded-full") {
                        onClick {
                            isDialogReserveShuttleVisible.value = false
                        }
                    }
                }
            }
        }
    }
}

private fun Container.footerWagmi() {
    div(className="flex max-md:flex-col items-center justify-between mb-8 md:mt-36 min-[1537px]:mt-72 pt-8 border-t border-[#3A2E47] text-white") {
        id = "footer-wagmi"
        image(src = "/images/logo_rentality_wagmi.svg")
        div("©2025 by Rentality LLC", className = "leading-6 max-md:mt-8")
    }
}