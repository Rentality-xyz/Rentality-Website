package com.eigenmethod.rentality.views

import com.eigenmethod.rentality.AppScope
import com.eigenmethod.rentality.constants.CONTAINER_PX
import com.eigenmethod.rentality.constants.MAIN_COLOR_BG
import com.eigenmethod.rentality.constants.MAX_WITH_CONTENT
import com.eigenmethod.rentality.controllers.sendWagmi2025DataToGoogleTable
import com.eigenmethod.rentality.models.ELegalMatters
import com.eigenmethod.rentality.models.Wagmi2025Data
import com.eigenmethod.rentality.navigation_state.Pages
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
private var isInputMainError = ObservableValue(false)
private var isInputFirstNameError = ObservableValue(false)
private var isInputLastNameError = ObservableValue(false)
private var isInputCompanyNameError = ObservableValue(false)
private var isInputPickUpLocationError = ObservableValue(false)
private var isInputEmailError = ObservableValue(false)
private var isInputPhoneError = ObservableValue(false)
private var isInputDateError = ObservableValue(false)
private var isInputDestinationError = ObservableValue(false)
private var isBtnSocialTwitterClicked = ObservableValue(false)
private var isBtnSocialTelegramClicked = ObservableValue(false)
private var isBtnSocialDiscoedClicked = ObservableValue(false)
private var isDialogReserveShuttleVisible = ObservableValue(false)
private var countClickedBtn = ObservableValue(0)

private const val cssInput = "pl-5 pr-5 border-b border-[#EFEFEF] bg-white h-[50px] text-base w-full"
private const val ccsSocialBntEnabled = "flex w-full justify-center p-4 rounded-full bg-[#F4F4F4]"
private const val ccsSocialBntDisabled = "$ccsSocialBntEnabled text-[#1E1E3240]"
private const val countSocialBtn = 3
private const val cssBtnSubmitMain = "text-white pt-0 pb-0 pl-4 pr-4 w-full h-[50px] rounded-full text-xl font-['Montserrat',Arial,sans-serif] font-semibold"
private const val cssBtnSubmitEnabled = "$cssBtnSubmitMain bg-[#6600CC] active:opacity-75 active:scale-95 transition duration-150"
private const val cssBtnSubmitDisabled = "$cssBtnSubmitMain bg-[#CCCCCC]"

private var formInputFirstName: String = ""
private var formInputLastName: String = ""
private var formInputCompanyName: String = ""
private var formInputPickUpLocation: String = ""
private var formInputEMail: String = ""
private var formInputPhone: String = ""
private var formInputDateShuttle: String = ""
private var formInputDestinationShuttle: String = ""




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

    div(className="mx-auto mt-4 $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] text-white font-['Montserrat',Arial,sans-serif]") {
        id = "main-wagmi2025-page"
        image(src = "/images/bg_logo_zoya.png", className = "max-xl:hidden absolute top-[1072px] right-0 max-h-[593px]")
        image(src = "/images/bg_arrows_from_logo_zoya.png", className = "max-xl:hidden absolute top-[1532px] left-0 max-w-[365px]")
        image(src = "/images/bg_logo_zoya.png", className = "max-xl:hidden absolute top-[2232px] right-0 max-h-[593px]")
        image(src = "/images/bg_arrows_from_logo_zoya.png", className = "max-xl:hidden absolute top-[2542px] left-0 max-w-[365px]")

        sectionReserveShuttle()
    }

    div(className = "relative w-full font-['Montserrat',Arial,sans-serif]") {
        div(className="mt-8 mx-auto $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] text-white") {
            sectionRentalityApp()
            sectionBecomeHost()
        }
        image(src = "/images/img_phone_wagmi.png", className = "max-md:hidden absolute top-[-310px] right-[-2%] min-[1920px]:right-[5%]")
        image(src = "/images/img_phone_wagmi_mob.png", className = "md:hidden absolute top-[70px] left-0 w-full")
    }

    div(className = "w-full md:bg-[url('/images/bg_avto_103_wagmi.png')] bg-cover bg-no-repeat bg-center font-['Montserrat',Arial,sans-serif]") {
        div(className="mt-[76px] md:mt-[176px] mx-auto $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] text-white") {
            div(className = "text-[28px] md:text-[64px] md:leading-[76px] w-full text-center font-bold") {
                + "Why List Your "
                br(className = "max-md:hidden")
                + "Car on Rentality?"
            }
            sectionListCarOnRentality()
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
    div(className="relative z-20 flex flex-col mt-[34px] pl-[35px] pr-[45px] py-[30px] rounded-[20px] bg-white text-[#1E1E32]") {
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
        form(className = "mt-8 text-base md:text-xl font-medium") {
            div {
                div(className = "flex flex-col md:grid grid-cols-2 gap-12 pb-5") {
                    div {
                        div().bind(isInputFirstNameError) {
                            label("*First Name", forId = "wagmi_first_name", className = if (isInputFirstNameError.value) "text-red-600" else "")
                        }
                        textInput(type = InputType.TEXT, className = cssInput) {
                            id = "wagmi_first_name"
                            placeholder = "First Name"
                            onInput {
                                it.preventDefault()
                                isInputFirstNameError.value = false
                                formInputFirstName = this.value.orEmpty()
                            }
                        }
                    }
                    div {
                        div().bind(isInputLastNameError) {
                            label("*Last Name", forId = "wagmi_last_name", className = if (isInputLastNameError.value) "text-red-600" else "")
                        }
                        textInput(type = InputType.TEXT, className = cssInput) {
                            id = "wagmi_last_name"
                            placeholder = "Last Name"
                            onInput {
                                it.preventDefault()
                                isInputLastNameError.value = false
                                formInputLastName = this.value.orEmpty()
                            }
                        }
                    }
                }

                div(className = "flex flex-col md:grid grid-cols-2 gap-12 pb-5") {
                    div {
                        div().bind(isInputCompanyNameError) {
                            label("*Company Name", forId = "wagmi_company_name", className = if (isInputCompanyNameError.value) "text-red-600" else "")
                        }
                        textInput(type = InputType.TEXT, className = cssInput) {
                            id = "wagmi_company_name"
                            placeholder = "Enter your Company"
                            onInput {
                                it.preventDefault()
                                isInputCompanyNameError.value = false
                                formInputCompanyName = this.value.orEmpty()
                            }
                        }
                    }
                    div {
                        div().bind(isInputPickUpLocationError) {
                            label("*Pick-up Location", forId = "wagmi_pick_up_location", className = if (isInputPickUpLocationError.value) "text-red-600" else "")
                        }
                        textInput(type = InputType.TEXT, className = cssInput) {
                            id = "wagmi_pick_up_location"
                            placeholder = "Enter Pick-up Location"
                            onInput {
                                it.preventDefault()
                                isInputPickUpLocationError.value = false
                                formInputPickUpLocation = this.value.orEmpty()
                            }
                        }
                    }
                }

                div(className = "flex flex-col md:grid grid-cols-2 gap-12 pb-5") {
                    div {
                        div().bind(isInputEmailError) {
                            label("*E-mail", forId = "wagmi_email", className = if (isInputEmailError.value) "text-red-600" else "")
                        }
                        textInput(type = InputType.EMAIL, className = cssInput) {
                            id = "wagmi_email"
                            placeholder = "hello@gmail.com"
                            value = formInputEMail
                            onInput {
                                it.preventDefault()
                                isInputEmailError.value = false
                                formInputEMail = this.value.orEmpty()
                            }
                        }
                    }
                    div {
                        div().bind(isInputPhoneError) {
                            label("*Phone Number", forId = "wagmi_phone", className = if (isInputPhoneError.value) "text-red-600" else "")
                        }
                        textInput(type = InputType.TEL, className = cssInput) {
                            id = "wagmi_phone"
                            placeholder = "+19999999999"
                            onInput {
                                it.preventDefault()
                                isInputPhoneError.value = false
                                val inputValue = this.value.orEmpty()
                                val allowedChars = setOf('+', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
                                val filteredValue = inputValue.filter { char -> allowedChars.contains(char) }
                                val limitedValue = filteredValue.take(12)
                                this.value = limitedValue
                                formInputPhone = limitedValue
                            }
                        }
                    }
                }

                div(className = "flex flex-col md:grid grid-cols-2 gap-12 pb-5") {
                    div {
                        div().bind(isInputDateError) {
                            label("*Date and Time of Shuttle service", forId = "wagmi_date_time_shuttle", className = if (isInputDateError.value) "text-red-600" else "")
                        }
                        textInput(type = InputType.DATETIME_LOCAL, className = cssInput) {
                            id = "wagmi_date_time_shuttle"
                            onInput {
                                it.preventDefault()
                                isInputDateError.value = false
                                formInputDateShuttle = this.value.orEmpty()
                            }
                        }
                    }

                    div {
                        div().bind(isInputDestinationError) {
                            label("*Destination", forId = "wagmi_destination", className = if (isInputDestinationError.value) "text-red-600" else "")
                        }
                        textInput(type = InputType.TEXT, className = cssInput) {
                            id = "wagmi_destination"
                            placeholder = "Enter Destination"
                            onInput {
                                it.preventDefault()
                                isInputDestinationError.value = false
                                formInputDestinationShuttle = this.value.orEmpty()
                            }
                        }
                    }
                }
            }
            div(className = "w-full flex items-center justify-between").bind(countClickedBtn) {
                id = "subscribe_social_network"
                div {
                    + "*Subscribe to our "
                    br(className = "md:hidden")
                    + "social networks"
                }
                div(className = "flex max-md:flex-col") {
                    div {
                        + "Subscribed"
                        span(content = ":", className = "md:hidden")
                    }
                    span(content = "${countClickedBtn.value}/3", className = "text-[#6600CC] max-md:ml-auto md:pl-1")
                }
            }
            div(className = "w-full mt-3 mb-9 flex flex-col md:grid grid-cols-3 gap-4 items-center justify-between font-semibold") {
                id = "btn_social_network"
                div(className = "w-full").bind(isBtnSocialTwitterClicked) {
                    link("", url = "https://x.com/Rentality_Info", target = "_blank", className = if (isBtnSocialTwitterClicked.value) ccsSocialBntEnabled else ccsSocialBntDisabled) {
                        div(className = "flex items-center justify-center flex-grow") {
                            image(src = "/images/social/logos_twitter_wagmi.svg", className = if (isBtnSocialTwitterClicked.value) "mr-4" else "hidden")
                            image(src = "/images/social/logos_twitter_disabled_wagmi.svg", className = if (isBtnSocialTwitterClicked.value) "hidden" else "mr-4")
                            + "Twitter"
                        }
                        image(src = "/images/padlock_wagmir.svg", className = if (isBtnSocialTwitterClicked.value) "hidden" else "ml-auto")
                        onClick {
                            isBtnSocialTwitterClicked.value = true
                            countClickedBtn.value = ++countClickedBtn.value
                        }
                    }
                }
                div(className = "w-full").bind(isBtnSocialTelegramClicked) {
                    link("", url = "https://t.me/rentality_xyz", target = "_blank", className = if (isBtnSocialTelegramClicked.value) ccsSocialBntEnabled else ccsSocialBntDisabled) {
                        div(className = "flex items-center justify-center flex-grow") {
                            image(src = "/images/social/logos_telegram_wagmi.svg", className = if (isBtnSocialTelegramClicked.value) "mr-4" else "hidden")
                            image(src = "/images/social/logos_telegram_disabled_wagmi.svg", className = if (isBtnSocialTelegramClicked.value) "hidden" else "mr-4")
                            + "Telegram"
                        }
                        image(src = "/images/padlock_wagmir.svg", className = if (isBtnSocialTelegramClicked.value) "hidden" else "ml-auto")
                        onClick {
                            isBtnSocialTelegramClicked.value = true
                            countClickedBtn.value = ++countClickedBtn.value
                        }
                    }
                }
                div(className = "w-full").bind(isBtnSocialDiscoedClicked) {
                    link("", url = "https://discord.com/invite/rentality ", target = "_blank", className = if (isBtnSocialDiscoedClicked.value) ccsSocialBntEnabled else ccsSocialBntDisabled) {
                        div(className = "flex items-center justify-center flex-grow") {
                            image(src = "/images/social/logos_discord_wagmi.svg", className = if (isBtnSocialDiscoedClicked.value) "mr-4" else "hidden")
                            image(src = "/images/social/logos_discord_disabled_wagmi.svg", className = if (isBtnSocialDiscoedClicked.value) "hidden" else "mr-4")
                            + "Discord"
                        }
                        image(src = "/images/padlock_wagmir.svg", className = if (isBtnSocialDiscoedClicked.value) "hidden" else "ml-auto")
                        onClick {
                            isBtnSocialDiscoedClicked.value = true
                            countClickedBtn.value = ++countClickedBtn.value
                        }
                    }
                }
            }
            div().bind(countClickedBtn) {
                button("Submit Request", type = ButtonType.SUBMIT, className = if (countClickedBtn.value < countSocialBtn) cssBtnSubmitDisabled else cssBtnSubmitEnabled) {
                    disabled = countClickedBtn.value < countSocialBtn
                    onClick {
                        it.preventDefault()
                        AppScope.withProgress {
                            isInputMainError.value = false

                            if (formInputFirstName.isEmpty()) {
                                val inputElement = document.getElementById("wagmi_first_name") as? HTMLInputElement
                                inputElement?.focus()
                                isInputFirstNameError.value = true
                                isInputMainError.value = true
                            }

                            if (formInputLastName.isEmpty()) {
                                val inputElement = document.getElementById("wagmi_last_name") as? HTMLInputElement
                                inputElement?.focus()
                                isInputLastNameError.value = true
                                isInputMainError.value = true
                            }

                            if (formInputCompanyName.isEmpty()) {
                                val inputElement = document.getElementById("wagmi_company_name") as? HTMLInputElement
                                inputElement?.focus()
                                isInputCompanyNameError.value = true
                                isInputMainError.value = true
                            }

                            if (formInputPickUpLocation.isEmpty()) {
                                val inputElement = document.getElementById("wagmi_pick_up_location") as? HTMLInputElement
                                inputElement?.focus()
                                isInputPickUpLocationError.value = true
                                isInputMainError.value = true
                            }

                            if (!isValidEmail(formInputEMail)) {
//                            window.alert("Invalid EMail")
                                val inputElement = document.getElementById("wagmi_email") as? HTMLInputElement
                                inputElement?.focus()
                                isInputEmailError.value = true
                                isInputMainError.value = true
                            }

                            val phoneRegex = Regex("""^\+\d{11}${'$'}""")
                            if (!phoneRegex.matches(formInputPhone)) {
                                val inputElement = document.getElementById("wagmi_phone") as? HTMLInputElement
                                inputElement?.focus()
                                isInputPhoneError.value = true
                                isInputMainError.value = true
                            }

                            if (!isValidShuttleDate(formInputDateShuttle)) {
                                val inputElement = document.getElementById("wagmi_date_time_shuttle") as? HTMLInputElement
                                inputElement?.focus()
                                isInputDateError.value = true
                                isInputMainError.value = true
                            }

                            if (formInputPickUpLocation.isEmpty()) {
                                val inputElement = document.getElementById("wagmi_destination") as? HTMLInputElement
                                inputElement?.focus()
                                isInputDestinationError.value = true
                                isInputMainError.value = true
                            }

                            if (isInputMainError.value) {
                                return@withProgress
                            }

                            sendWagmi2025DataToGoogleTable(
                                    Wagmi2025Data(
                                            firstName = formInputFirstName,
                                            lastName = formInputLastName,
                                            company = formInputCompanyName,
                                            pickUpLocation = formInputPickUpLocation,
                                            email = formInputEMail,
                                            phone = formInputPhone,
                                            date = formInputDateShuttle,
                                            destination = formInputDestinationShuttle)
                            )

                            // Очистка данных формы
                            formInputFirstName = ""
                            formInputLastName = ""
                            formInputCompanyName = ""
                            formInputPickUpLocation = ""
                            formInputEMail = ""
                            formInputPhone = ""
                            formInputDateShuttle = ""
                            formInputDestinationShuttle = ""

                            // Очистка содержимого текстовых полей
                            val inputs = listOf(
                                    "wagmi_first_name",
                                    "wagmi_last_name",
                                    "wagmi_company_name",
                                    "wagmi_pick_up_location",
                                    "wagmi_email",
                                    "wagmi_phone",
                                    "wagmi_date_time_shuttle",
                                    "wagmi_destination"
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
}

private fun Container.sectionRentalityApp() {
    div(className="relative z-10 flex max-md:flex-col mt-[70px] md:mt-56 h-[700px] md:h-[560px]") {
        id = "rentality-app"
        div(className = "relative z-10 flex flex-col max-md:items-center") {
            p(className = "text-[28px] md:text-[64px] font-bold md:leading-[76px]") {
                + "Get the "
                br(className = "max-md:hidden")
                + "Rentality App"
            }
            p(className = "text-base md:text-xl font-medium md:leading-7 mt-2 md:mt-6 max-md:text-center") {
                + "User-friendly interface and access to all features in one"
                br(className = "max-md:hidden")
                + "place. Download our app to simplify your interactions"
            }
            link(label = "", url = "https://apps.apple.com/ua/app/rentality/id6736899320", className = "max-md:w-full w-fit") {
                button(text = "Download for iOS", className = "flex mt-8 md:mt-14 items-center justify-center bg-white rounded-full max-md:w-full md:w-[405px] h-[60px] text-xl text-[#6600CC] font-['Montserrat',Arial,sans-serif] font-semibold active:opacity-75 active:scale-95 transition duration-150") {
                    image(src = "/images/ic_apple_wagmi.svg", className = "ml-4")
                }
            }
            link(label = "", url = "https://play.google.com/store/apps/details?id=xyz.rentality.rentality", className = "max-md:w-full w-fit") {
                button(text = "Download for Android", className = "flex mt-4 md:mt-6 items-center justify-center bg-white rounded-full max-md:w-full md:w-[405px] h-[60px] text-xl text-[#6600CC] font-['Montserrat',Arial,sans-serif] font-semibold active:opacity-75 active:scale-95 transition duration-150") {
                    image(src = "/images/ic_android_wagmi.svg", className = "ml-4")
                }
            }
        }
    }
}

private fun Container.sectionBecomeHost() {
    div(className="relative z-10 flex max-md:text-center md:items-center md:justify-end p-6 md:p-14 mt-56 h-[537px] md:h-[456px] bg-[url('/images/bg_wagmi_become_host_mob.png')] md:bg-[url('/images/bg_wagmi_become_host.png')] bg-cover bg-center bg-no-repeat") {
        id = "become-a-host"
        div(className = "flex flex-col") {
            p(className = "text-[28px] md:text-[60px] font-bold md:leading-[76px]") {
                + "List Your Car "
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

private fun Container.sectionListCarOnRentality() {
    div(className="relative z-10 flex-col md:grid grid-cols-2 gap-4 mt-7") {
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
                    + "No Intermediaries"
                }
                p(className = "text-base md:text-xl font-medium relative z-10 mt-2 md:mt-6") {
                    + "Every deal is a direct agreement between you and the guest"
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
    div(className="relative z-10 mt-[108px] md:mt-36") {
        id = "register-as-a-host"
        h2(className = "text-[28px] md:text-[64px] font-bold leading-[120%] max-md:text-center") {
            + "Ready to Earn "
            br(className = "max-md:hidden")
            + "More with "
            br(className = "max-md:hidden")
            + "Less Effort?"
        }
        link(label = "", url = "https://demo.rentality.xyz/host/profile", className = "max-md:w-full") {
            button(text = "Register as a Host", className = "flex max-md:mt-6 mt-14 items-center justify-center bg-white rounded-full max-md:w-full md:w-[405px] h-[60px] text-xl text-[#6600CC] font-semibold active:opacity-75 active:scale-95 transition duration-150") {
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
            p("Thank you! We received your shuttle request!", className = "text-[28px] md:text-5xl leading-[33px] md:leading-[58px] font-bold mb-4 md:mb-8")
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
    div(className = "flex max-md:flex-col justify-between mb-8 md:mt-36 min-[1537px]:mt-72 pt-8 border-t border-[#3A2E47] text-white") {
        id = "footer-wagmi"
        div(className = "flex flex-col justify-between") {
            image(src = "/images/logo_rentality_wagmi.svg")
            div("©2025 by Rentality LLC", className = "max-md:hidden")
        }
        footerLegalMattersWagmi()
        footerStoresBlockWagmi()
        footerInfoBlockWagmi()
    }
}

private fun Container.footerLegalMattersWagmi() {
    div(className = "relative z-0 flex flex-col max-md:items-center max-md:mt-6") {
        link(
            label = "Legal matters",
            url = Pages.LEGAL_MATTERS.url,
            target = "_blank",
            className = "pb-1 cursor-pointer text-xl font-semibold font-['Montserrat',Arial,sans-serif] hover:underline"
        )
        link(
            label = "Terms of service",
            url = "${Pages.LEGAL_MATTERS.url}/${ELegalMatters.TERMS.value}",
            target = "_blank",
            className = "mt-3 pb-1.5 cursor-pointer text-base font-['Montserrat',Arial,sans-serif] hover:underline"
        )
        link(
            label = "Cancellation policy",
            url = "${Pages.LEGAL_MATTERS.url}/${ELegalMatters.CANCELLATION.value}",
            target = "_blank",
            className = "pb-1.5 cursor-pointer text-base font-['Montserrat',Arial,sans-serif] hover:underline"
        )
        link(
            label = "Prohibited uses",
            url = "${Pages.LEGAL_MATTERS.url}/${ELegalMatters.PROHIBITEDUSES.value}",
            target = "_blank",
            className = "pb-1.5 cursor-pointer text-base font-['Montserrat',Arial,sans-serif] hover:underline"
        )
        link(
            label = "Privacy policy",
            url = "${Pages.LEGAL_MATTERS.url}/${ELegalMatters.PRIVACY.value}",
            target = "_blank",
            className = "pb-1.5 cursor-pointer text-base font-['Montserrat',Arial,sans-serif] hover:underline"
        )
    }
}

private fun Container.footerStoresBlockWagmi() {
    div(className = "relative z-0 flex flex-col items-center mt-4") {
        link("", url = "https://apps.apple.com/ua/app/rentality/id6736899320") {
            image(src = "/images/marketplace/ic_appstore.svg", className = "w-[200px]")
        }
        link("", url = "https://play.google.com/store/apps/details?id=xyz.rentality.rentality") {
            image(src = "/images/marketplace/ic_google_play.png", className = "w-[200px] mt-4")
        }
    }
}

private fun Container.footerInfoBlockWagmi() {
    div(className = "relative z-0 flex flex-col max-md:mt-8") {
        div(className = "md:ml-auto") {
            link(
                "",
                url = "mailto:info@rentality.xyz",
                className = "max-md:text-center pt-6 lg:pt-9 font-['Montserrat',Arial,sans-serif] text-base font-normal text-end"
            ) {
                div("info@rentality.xyz")
            }
            div(className = "flex flex-col mt-1.5 max-md:items-center") {
                div(className = "flex gap-5") {
                    link("", url = "https://www.linkedin.com/company/rentalitycorp/?viewAsMember=true") {
                        image(src = "/images/social/linkedin-logo.svg", className = "w-[30px]")
                    }
                    link("", url = "https://twitter.com/Rentality_Info") {
                        image(src = "/images/social/x-logo.svg", className = "ml-1.5 w-[30px]")
                    }
                    link("", url = "https://discord.gg/rentality") {
                        image(src = "/images/social/discord-logo.svg", className = "ml-1.5 w-[30px]")
                    }

                }
                div(className = "flex gap-5") {
                    link("", url = "https://t.me/rentality_xyz") {
                        image(src = "/images/social/telegram-logo.svg", className = "w-[30px]")
                    }
                    link("", url = "https://mirror.xyz/0x263660F0ab0014e956d42f85DccD918bBa2Df587") {
                        image(src = "/images/social/mirror-logo.svg", className = "ml-1.5 w-[30px]")
                    }
                    link("", url = "https://warpcast.com/rentality") {
                        image(src = "/images/social/warpcast-logo.svg", className = "ml-1.5 w-[30px]")
                    }
                }
                div(className = "flex gap-5") {
                    link("", url = "https://www.instagram.com/rentality_/") {
                        image(src = "/images/social/instagram-logo.svg", className = "w-[30px]")
                    }
                    link("", url = "https://medium.com/@rentality") {
                        image(src = "/images/social/medium-logo.svg", className = "ml-1.5 w-[30px]")
                    }
                    link("", url = "https://www.youtube.com/@Rentality_xyz") {
                        image(src = "/images/social/youtube-logo.png", className = "ml-1.5 w-[30px]")
                    }
                }
            }
        }
        div(className = "md:absolute w-[250px] bottom-[-16px] right-[-34px] max-md:m-auto max-md:mt-8") {
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
        div("©2025 by Rentality LLC", className = "md:hidden m-auto mt-7")
    }
}