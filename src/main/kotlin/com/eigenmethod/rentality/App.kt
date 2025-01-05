package com.eigenmethod.rentality

import com.eigenmethod.rentality.constants.MAIN_COLOR_BG
import com.eigenmethod.rentality.models.crypto_provider.EthProvider
import com.eigenmethod.rentality.navigation_state.ConduitManager
import com.eigenmethod.rentality.navigation_state.Pages
import com.eigenmethod.rentality.views.*
import com.eigenmethod.rentality.views.components.footer
import com.eigenmethod.rentality.views.components.header
import com.eigenmethod.rentality.views.guest.guestBookedPage
import com.eigenmethod.rentality.views.guest.guestHistoryPage
import com.eigenmethod.rentality.views.guest.guestSearchPage
import com.eigenmethod.rentality.views.host.hostAddCarPage
import com.eigenmethod.rentality.views.host.hostBookedPage
import com.eigenmethod.rentality.views.host.hostCarListingPage
import com.eigenmethod.rentality.views.host.hostHistoryPage
import io.kvision.Application
import io.kvision.CoreModule
import io.kvision.html.main
import io.kvision.module
import io.kvision.pace.Pace
import io.kvision.pace.PaceOptions
import io.kvision.panel.root
import io.kvision.startApplication
import io.kvision.state.bind
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher

val AppScope = CoroutineScope(window.asCoroutineDispatcher()) //TODO создается один раз на всю программу, а можно и в каждом классе создавать свой


class App : Application(), CoroutineScope by CoroutineScope(Dispatchers.Default + SupervisorJob()) {

    init {
        kotlinext.js.require("css/styles.css")
    }

    override fun start() {
        Pace.init(io.kvision.require("css/pace-bounce.css"))
        Pace.setOptions(PaceOptions(manual = true))
        ConduitManager.initialize(EthProvider)

        root("kvapp") {
            main(className = "bg-[$MAIN_COLOR_BG] overflow-hidden").bind(ConduitManager.conduitStore) { state ->
                header()

                when (state.page) {
                    Pages.HOME -> {
//                        console.log("App View Home")
                        homePage()
                        Pace.stop()
                    }
                    Pages.CAR_INFO -> {
//                        console.log("App View HOST_ADD_CAR")
                        carInfoPage(state)
                        Pace.stop()
                    }
                    Pages.HOST_CAR_LISTING -> {
//                        console.log("App View HOST_CAR_LISTING")
                        hostCarListingPage(state)
                        Pace.stop()
                    }
                    Pages.HOST_ADD_CAR -> {
//                        console.log("App View HOST_ADD_CAR")
                        hostAddCarPage(state)
                        Pace.stop()
                    }
                    Pages.HOST_HISTORY -> {
                        hostHistoryPage(state)
                        Pace.stop()
                    }
                    Pages.HOST_BOOKED -> {
                        hostBookedPage(state)
                        Pace.stop()
                    }
                    Pages.GUEST_SEARCH -> {
//                        console.log("App View GUEST_SEARCH")
                        guestSearchPage(state)
                        Pace.stop()
                    }
                    Pages.GUEST_HISTORY -> {
                        guestHistoryPage(state)
                        Pace.stop()
                    }
                    Pages.GUEST_BOOKED -> {
                        guestBookedPage(state)
                        Pace.stop()
                    }
                    Pages.HISTORY_DETAILS -> {
                        historyDetailsPage(state)
                        Pace.stop()
                    }
                    Pages.LEGAL_MATTERS -> {
                        legalMattersPage(state)
                        Pace.stop()
                    }
                    Pages.TRIP_RULES -> {
                        tripRulesPage(state)
                        Pace.stop()
                    }
                    Pages.WAGMI_2025 -> {
                        wagmi2025Page()
                        Pace.stop()
                    }
                }
                if (state.page != Pages.WAGMI_2025) {
                    footer()
                }
            }
        }
    }
}

fun main()  {
    startApplication(
        ::App,
        module.hot,
        CoreModule
    )

    //для ETHERS версии 5.7.0
//    val provider = getProvider()
//    val signer = getSigner()
//    provider = EthProvider
//    provider?.connectToWallet()
//    val addressWallet = provider?.getWalletAddress()
//    console.log("MetaMask Wallet address")
//    console.log(addressWallet)

    //для ETHERS версии 6.4.0, но пока эта библиотека не работает, вылетает ошибка Uncaught ReferenceError: Buffer is not defined
//    val ethers = kotlinext.js.require("ethers")
//    val winEthereum: dynamic = js("window.ethereum")
//    val provider =  if (winEthereum == null) {
//        console.log("winEthereum NULL")
//        js("ethers.getDefaultProvider()")
//    } else {
//        console.log("winEthereum NOT NULL")
//        js("new ethers.BrowserProvider(window.ethereum)")
//    }
//    val signer = provider.getSigner()
//    val address = signer.getAddress()
//    console.log("MetaMask Wallet address")
//    console.log(address)
}