package com.eigenmethod.rentality.models.crypto_provider

import com.eigenmethod.rentality.models.abis.Rentality
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object EthProvider : ICryptoProvider {

    private var provider: dynamic = null
    private var signer: dynamic = null
    override fun connectToWallet() {
        getProvider()
    }

    override fun getProvider(): dynamic {
        val ethers = kotlinext.js.require("ethers") //эта переменная хоть и не используется в Kotlin, но эта строка нужна, т.к. эта переменная используется в ф-ции js(), а иначе получаем ошибку Uncaught ReferenceError: ethers is not defined
        provider = js("new ethers.providers.Web3Provider(window.ethereum)")
        signer = provider.getSigner()
        return provider
    }

    override fun getSigner(): dynamic {
        return signer
    }

    override fun getWalletAddress(): dynamic {
        return signer.getAddress()
    }

    override fun getContract(abis: String): dynamic {
        val ethers = kotlinext.js.require("ethers")
        val rentCar = Json.decodeFromString<Rentality>(abis)
        val address = rentCar.address.orEmpty()
        val abi = Json.encodeToString(rentCar.abi)
        val _signer = signer
        return js("new ethers.Contract(address, abi, _signer)")
    }
}