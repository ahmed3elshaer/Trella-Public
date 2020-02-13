package com.trella.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shipments")
data class ShipmentLocal(
    @ColumnInfo(name = "commodity")
    val commodity: String = "",
    @ColumnInfo(name = "addresses")
    val address: List<Address> = listOf(),
    @ColumnInfo(name = "price")
    val price: Int = 0,
    @ColumnInfo(name = "numberOfBids")
    val numberOfBids: Int = 0,
    @ColumnInfo(name = "key")
    @PrimaryKey
    val key: String = "",
    @ColumnInfo(name = "vehicleType")
    val vehicleType: String = ""
) {

    data class Address(
        val latitude: Double = 0.0,
        val name: String = "",
        val key: String = "",
        val order: Int = 0,
        val longitude: Double = 0.0
    )
}