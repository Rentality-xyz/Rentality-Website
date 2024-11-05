package com.eigenmethod.rentality.models.crypto_provider

interface ICryptoProvider {
    fun connectToWallet()
    fun getProvider(): dynamic
    fun getSigner(): dynamic
    fun getWalletAddress(): dynamic
    fun getContract(abis: String): dynamic
}