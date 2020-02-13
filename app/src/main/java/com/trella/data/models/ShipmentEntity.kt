package com.trella.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "shipments")
data class ShipmentEntity(
    @ColumnInfo(name = "commodity")
    @Json(name = "commodity")
    val commodity: String = "",
    @ColumnInfo(name = "addresses")
    @Json(name = "addresses")
    val addresses: List<Address> = listOf(),
    @ColumnInfo(name = "price")
    @Json(name = "price")
    val price: Int = 0,
    @ColumnInfo(name = "numberOfBids")
    @Json(name = "numberOfBids")
    val numberOfBids: Int = 0,
    @ColumnInfo(name = "key")
    @Json(name = "key")
    @PrimaryKey
    val key: String = "",
    @ColumnInfo(name = "vehicleType")
    @Json(name = "vehicleType")
    val vehicleType: String = ""
) {

    data class Address(
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