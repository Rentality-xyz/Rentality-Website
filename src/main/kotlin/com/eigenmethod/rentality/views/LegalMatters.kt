package com.eigenmethod.rentality.views

import com.eigenmethod.rentality.constants.CONTAINER_PX
import com.eigenmethod.rentality.constants.MAX_WITH_CONTENT
import com.eigenmethod.rentality.models.ELegalMatters
import com.eigenmethod.rentality.navigation_state.ConduitState
import io.kvision.core.Container
import io.kvision.core.Display
import io.kvision.core.style
import io.kvision.html.*
import io.kvision.state.ObservableValue
import io.kvision.state.bind

private val isActivateTabTermsService = ObservableValue(true)
private var isActivateTabPrivacyPolicy = false
private var isActivateTabProhibitedUse = false
private var isActivateTabCancellationPolicy = false

fun Container.legalMattersPage(state: ConduitState) {
    val classTabs = "m-1 max-md:mt-4 md:m-7"
    val classTabsItems = "flex lg:mb-4"
    val classTabsItem = "flex text-sm lg:text-base text-center justify-center items-center font-['Montserrat',Arial,sans-serif] sm:mr-6"
    val classTabsBlock = "pl-1 pr-1 pb-1 pt-5 md:p-5"
    val classSelectedTab = "text-[#5CF4E8]"

    val styleDispBlock = style {
        display = Display.BLOCK
    }
    val styleDispNone = style {
        display = Display.NONE
    }

    state.legalMatters?.also { onTabClick(it) }

    div(className="mx-auto $CONTAINER_PX max-w-[$MAX_WITH_CONTENT] text-white") {
        id = "main-legal-matters-page"
        h1(content = "Legal matters", className = "text-2xl font-semibold font-['Montserrat',Arial,sans-serif]")
        div(className = classTabs).bind(isActivateTabTermsService) {
            nav(className = classTabsItems) {
                link(label = "Terms of service", url = "", className = classTabsItem) {
                    if (isActivateTabTermsService.value) {
                        classSelectedTab
                                .split(" ")
                                .forEach {
                                    addCssClass(it)
                                }
                    }
                    onClick {
                        it.preventDefault()
                        onTabClick(ELegalMatters.TERMS)
                    }
                }
                link(label = "Cancellation policy", url = "", className = classTabsItem) {
                    if (isActivateTabCancellationPolicy) {
                        classSelectedTab
                                .split(" ")
                                .forEach {
                                    addCssClass(it)
                                }
                    }
                    onClick {
                        it.preventDefault()
                        onTabClick(ELegalMatters.CANCELLATION)
                    }
                }
                link(label = "Prohibited uses", url = "", className = classTabsItem) {
                    if (isActivateTabProhibitedUse) {
                        classSelectedTab
                                .split(" ")
                                .forEach {
                                    addCssClass(it)
                                }
                    }
                    onClick {
                        it.preventDefault()
                        onTabClick(ELegalMatters.PROHIBITEDUSES)
                    }
                }
                link(label = "Privacy policy", url = "", className = classTabsItem) {
                    if (isActivateTabPrivacyPolicy) {
                        classSelectedTab
                                .split(" ")
                                .forEach {
                                    addCssClass(it)
                                }
                    }
                    onClick {
                        it.preventDefault()
                        onTabClick(ELegalMatters.PRIVACY)
                    }
                }
            }
            div().bind(isActivateTabTermsService) {
                div(className = classTabsBlock) {
                    id = "lm_terms_of_service"
                    if (isActivateTabTermsService.value) {
                        addCssStyle(styleDispBlock)
                    } else {
                        addCssStyle(styleDispNone)
                    }
                    iframe(src = "docs/Terms.pdf", className = "w-full h-[600px] bg-white")
                }
                div(className = classTabsBlock) {
                    id = "lm_cancellation_policy"
                    if (isActivateTabCancellationPolicy) {
                        addCssStyle(styleDispBlock)
                    } else {
                        addCssStyle(styleDispNone)
                    }
                    iframe(src = "docs/Cancellation.pdf", className = "w-full h-[600px] bg-white")
                }
                div(className = classTabsBlock) {
                    id = "lm_prohibited_uses"
                    if (isActivateTabProhibitedUse) {
                        addCssStyle(styleDispBlock)
                    } else {
                        addCssStyle(styleDispNone)
                    }
                    iframe(src = "docs/Prohibited.pdf", className = "w-full h-[600px] bg-white")
                }
                div(className = classTabsBlock) {
                    id = "lm_privacy_policy"
                    if (isActivateTabPrivacyPolicy) {
                        addCssStyle(styleDispBlock)
                    } else {
                        addCssStyle(styleDispNone)
                    }
                    iframe(src = "docs/Privacy.pdf", className = "w-full h-[600px] bg-white")
                }
            }
        }
    }
}

private fun onTabClick(legalMatter: ELegalMatters) {
    when (legalMatter) {
        ELegalMatters.TERMS -> {
            isActivateTabTermsService.value = true
            isActivateTabPrivacyPolicy = false
            isActivateTabProhibitedUse = false
            isActivateTabCancellationPolicy = false
        }
        ELegalMatters.CANCELLATION -> {
            isActivateTabTermsService.value = false
            isActivateTabPrivacyPolicy = false
            isActivateTabProhibitedUse = false
            isActivateTabCancellationPolicy = true
        }
        ELegalMatters.PROHIBITEDUSES -> {
            isActivateTabTermsService.value = false
            isActivateTabPrivacyPolicy = false
            isActivateTabProhibitedUse = true
            isActivateTabCancellationPolicy = false
        }
        ELegalMatters.PRIVACY -> {
            isActivateTabTermsService.value = false
            isActivateTabPrivacyPolicy = true
            isActivateTabProhibitedUse = false
            isActivateTabCancellationPolicy = false
        }
    }
}