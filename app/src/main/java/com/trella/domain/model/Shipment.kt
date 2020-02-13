package com.trella.domain.model


data class Shipment(
    val commodity: String = "",
    val price: Int = 0,
    val numberOfBids: Int = 0,
    val key: String = "",
    val vehicleType: String = "",
    val fromAddress: Address = Address(),
    val toAddress: Address = Address()
) {

    data class Address(
        val name: String = "",
        val key: String = ""
    )
}