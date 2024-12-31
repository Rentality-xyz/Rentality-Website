package com.eigenmethod.rentality.navigation_state

import com.eigenmethod.rentality.models.BookedCar
import com.eigenmethod.rentality.models.Car
import com.eigenmethod.rentality.models.ELegalMatters.Companion.convertToELegalMatters
import com.eigenmethod.rentality.models.RentCar
import com.eigenmethod.rentality.models.UserStatus
import com.eigenmethod.rentality.models.crypto_provider.ICryptoProvider
import io.kvision.redux.createTypedReduxStore
import io.kvision.routing.Routing
import io.kvision.routing.Strategy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

object ConduitManager : CoroutineScope by CoroutineScope(Dispatchers.Default + SupervisorJob()) {

    val conduitStore = createTypedReduxStore<ConduitState, ConduitAction>(::conduitReducer, ConduitState())

    private lateinit var routing: Routing
    private lateinit var provider: ICryptoProvider

    fun initialize(_provider: ICryptoProvider) {
        routing = Routing.init("/", useHash = false, strategy = Strategy.ONE)
        routing.initialize().resolve()

        try {
            provider = _provider
            provider.connectToWallet()
//        val addressWallet = provider.getWalletAddress()
//        console.log("MetaMask Wallet address")
//        console.log(addressWallet)
        } catch (error: Throwable) {
            console.log("No connection to wallet")
        }
    }

    fun redirectPage(page: Pages) {
        routing.navigate(page.url)
    }

    fun redirectUrl(url: String) {
        routing.navigate(url)
    }

    fun getProvider(): ICryptoProvider {
        return provider
    }

    fun homePage() {
        conduitStore.dispatch(ConduitAction.HomePage)
    }

    fun routToCarInfoPage(userStatus: UserStatus, fromPage: Pages, car: Car, rentCar: RentCar? = null, bookedCar: BookedCar? = null) {
        val state = conduitStore.getState().copy(
            page = Pages.CAR_INFO,
            userStatus = userStatus,
            fromPage = fromPage,
            car = car,
            rentCar = rentCar,
            bookedCar = bookedCar
        )
        conduitStore.dispatch(ConduitAction.SetConduitState(state))
        routing.navigate(Pages.CAR_INFO.url)
    }

    fun routToHistoryDetailsPage(userStatus: UserStatus, bookedCar: BookedCar) {
        val state = conduitStore.getState().copy(
            page = Pages.HISTORY_DETAILS,
            userStatus = userStatus,
            bookedCar = bookedCar
        )
        conduitStore.dispatch(ConduitAction.SetConduitState(state))
        routing.navigate(Pages.HISTORY_DETAILS.url)
    }

    fun hostCarListingPage() {
        val state = conduitStore.getState().copy(page = Pages.HOST_CAR_LISTING, userStatus = UserStatus.HOST)
        conduitStore.dispatch(ConduitAction.SetConduitState(state))
    }

    fun hostAddCarPage() {
        val state = conduitStore.getState().copy(page = Pages.HOST_ADD_CAR, userStatus = UserStatus.HOST)
        conduitStore.dispatch(ConduitAction.SetConduitState(state))
    }

    fun hostBookedPage() {
        val state = conduitStore.getState().copy(page = Pages.HOST_BOOKED, userStatus = UserStatus.HOST)
        conduitStore.dispatch(ConduitAction.SetConduitState(state))
    }

    fun hostHistoryPage() {
        val state = conduitStore.getState().copy(page = Pages.HOST_HISTORY, userStatus = UserStatus.HOST)
        conduitStore.dispatch(ConduitAction.SetConduitState(state))
    }

    fun guestSearchPage() {
        val state = conduitStore.getState().copy(page = Pages.GUEST_SEARCH, userStatus = UserStatus.GUEST)
        conduitStore.dispatch(ConduitAction.SetConduitState(state))
    }

    fun guestBookedPage() {
        val state = conduitStore.getState().copy(page = Pages.GUEST_BOOKED, userStatus = UserStatus.GUEST)
        conduitStore.dispatch(ConduitAction.SetConduitState(state))
    }

    fun guestHistoryPage() {
        val state = conduitStore.getState().copy(page = Pages.GUEST_HISTORY, userStatus = UserStatus.GUEST)
        conduitStore.dispatch(ConduitAction.SetConduitState(state))
    }

    fun legalMattersPage() {
        val state = conduitStore.getState().copy(page = Pages.LEGAL_MATTERS)
        conduitStore.dispatch(ConduitAction.SetConduitState(state))
    }

    fun legalMattersHook(value: String) {
        val typeLegalMatter = convertToELegalMatters(value)
        typeLegalMatter?.also {
            val state = conduitStore.getState().copy(page = Pages.LEGAL_MATTERS, legalMatters = it)
            conduitStore.dispatch(ConduitAction.SetConduitState(state))
            redirectPage(Pages.LEGAL_MATTERS)
        }
    }

    fun tripRulesPage() {
        val state = conduitStore.getState().copy(page = Pages.TRIP_RULES)
        conduitStore.dispatch(ConduitAction.SetConduitState(state))
    }

    fun wagmi2025Page() {
        val state = conduitStore.getState().copy(page = Pages.WAGMI_2025)
        conduitStore.dispatch(ConduitAction.SetConduitState(state))
    }
}
