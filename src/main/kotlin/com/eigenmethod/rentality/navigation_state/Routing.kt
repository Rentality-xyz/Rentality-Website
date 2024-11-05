package com.eigenmethod.rentality.navigation_state

import io.kvision.navigo.Navigo
import kotlin.js.RegExp

fun Navigo.initialize(): Navigo {
    return on(
            Pages.HOME.url,
            { _ ->
//                console.log("HOME Page Routing")
                ConduitManager.homePage()
            })
            //для страницы CAR_INFO не надо, там переход делается по ConduitManager.routToCarInfoPage
            .on(
                    Pages.CAR_INFO.url,
                    { _ ->
//                console.log("HOST_CAR_INFO Page Routing")
//                ConduitManager.carInfoPage()
                    }
            )
            .on(
                    Pages.HOST_CAR_LISTING.url,
                    { _ ->
                        ConduitManager.hostCarListingPage()
                    }
            )
            .on(
                    Pages.HOST_ADD_CAR.url,
                    { _ ->
                        ConduitManager.hostAddCarPage()
                    }
            )
            .on(
                    Pages.HOST_BOOKED.url,
                    { _ ->
                        ConduitManager.hostBookedPage()
                    }
            )
            .on(
                    Pages.HOST_HISTORY.url,
                    { _ ->
                        ConduitManager.hostHistoryPage()
                    }
            )
            .on(
                    Pages.GUEST_SEARCH.url,
                    { _ ->
                        ConduitManager.guestSearchPage()
                    }
            )
            .on(
                    Pages.GUEST_BOOKED.url,
                    { _ ->
                        ConduitManager.guestBookedPage()
                    }
            )
            .on(
                    Pages.GUEST_HISTORY.url,
                    { _ ->
                        ConduitManager.guestHistoryPage()
                    }
            )
            .on(
                    Pages.HISTORY_DETAILS.url,
                    { _ ->
//                ConduitManager.historyDetailsPage()
                    }
            )
            .on(
                    Pages.LEGAL_MATTERS.url,
                    { _ ->
                        ConduitManager.legalMattersPage()
                    }
            )
            .on(
                    RegExp("legalmatters/(.*)"),
                    { match ->
                        ConduitManager.legalMattersHook(match.data[0] as String)
                    }
            )
            .on(
                    Pages.TRIP_RULES.url,
                    { _ ->
                        ConduitManager.tripRulesPage()
                    }
            )


}