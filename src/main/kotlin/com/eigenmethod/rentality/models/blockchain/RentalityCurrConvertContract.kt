package com.eigenmethod.rentality.models.blockchain

import com.eigenmethod.rentality.constants.ABIS_RENT_CURR_CONVERT
import com.eigenmethod.rentality.navigation_state.ConduitManager
import kotlin.js.Promise

private val provider = ConduitManager.getProvider()
private val contract = provider.getContract(ABIS_RENT_CURR_CONVERT)

object RentalityCurrConvertContract : IRentalityCurrConvertContract {
    override fun getEthFromUsdLatest(rentPriceInUsdCents: dynamic): Promise<dynamic> {
        return Promise { resolve, _ ->
            resolve(contract.getEthFromUsdLatest(rentPriceInUsdCents))
        }
    }
}

interface IRentalityCurrConvertContract {
    fun getEthFromUsdLatest(rentPriceInUsdCents: dynamic): Promise<dynamic>
}