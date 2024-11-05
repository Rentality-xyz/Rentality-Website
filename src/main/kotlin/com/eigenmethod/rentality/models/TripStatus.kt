package com.eigenmethod.rentality.models

enum class TripStatus(val value: String) {
    Pending("pending"),
    Confirmed("confirmed"),
    CheckedInByHost("checkedInByHost"),
    Started("started"),
    CheckedOutByGuest("checkedOutByGuest"),
    Finished("finished"),
    Closed("closed"),
    Rejected("rejected")
}