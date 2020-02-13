package com.trella.data.models


import com.squareup.moshi.Json


data class ShipmentRemote(
    @Json(name = "commodity")
    val commodity: String = "",
    @Json(name = "addresses")
    val addresses: List<Addresses> = listOf(),
    @Json(name = "price")
    val price: Int = 0,
    @Json(name = "numberOfBids")
    val numberOfBids: Int = 0,
    @Json(name = "key")
    val key: String = "",
    @Json(name = "vehicleType")
    val vehicleType: String = ""
) {

    data class Addresses(
        @Json(name = "latitude")
        val latitude: Double = 0.0,
        @Json(name = "name")
        val name: String = "",
        @Json(name = "key")
        val key: String = "",
        @Json(name = "order")
        val order: Int = 0,
        @Json(name = "longitude")
        val longitude: Double = 0.0
    )

}


