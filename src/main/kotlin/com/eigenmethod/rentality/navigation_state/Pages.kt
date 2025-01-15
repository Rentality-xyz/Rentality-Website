package com.eigenmethod.rentality.navigation_state

enum class Pages(val url: String) {
    HOME("/"),
    CAR_INFO("/carinfo"),
    HOST_CAR_LISTING("/hostcarlisting"),
    HOST_ADD_CAR("/hostaddcar"),
    HOST_HISTORY("/hosthistory"),
    HOST_BOOKED("/hostbooked"),
    GUEST_SEARCH("/guestsearch"),
    GUEST_HISTORY("/guesthistory"),
    GUEST_BOOKED("/guestbooked"),
    HISTORY_DETAILS("/historydetails"),
    LEGAL_MATTERS("/legalmatters"),
    TRIP_RULES("/triprules"),
    WAGMI_2025("/wagmi-2025"),
    LIFETIME_REWARDS("/lifetime-rewards"),
}