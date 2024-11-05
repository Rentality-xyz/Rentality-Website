package com.eigenmethod.rentality.navigation_state

import com.eigenmethod.rentality.models.*
import io.kvision.redux.RAction


data class ConduitState(
    val page: Pages = Pages.HOME,
    val userStatus: UserStatus = UserStatus.GUEST,
    val fromPage: Pages? = null,
    val car: Car? = null,
    val rentCar: RentCar? = null,
    val bookedCar: BookedCar? = null,
    val legalMatters: ELegalMatters? = null
)

sealed class ConduitAction : RAction {
    object HomePage : ConduitAction()
    data class SetConduitState(
        val conduitState: ConduitState
    ) : ConduitAction()
}

fun conduitReducer(state: ConduitState, action: ConduitAction): ConduitState = when (action) {
    is ConduitAction.HomePage -> {
        state.copy(page = Pages.HOME)
    }
    is ConduitAction.SetConduitState -> {
        state.copy(
            page = action.conduitState.page,
            userStatus = action.conduitState.userStatus,
            fromPage = action.conduitState.fromPage,
            car = action.conduitState.car,
            rentCar = action.conduitState.rentCar,
            bookedCar = action.conduitState.bookedCar,
            legalMatters = action.conduitState.legalMatters
        )
    }
}
