package com.trella.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.trella.data.models.ShipmentEntity

class ShipmentTypeConverter {
    @TypeConverter
    fun addressToJSON(addresses: List<ShipmentEntity.Address>): String {
        return Gson().toJson(addresses)
    }

    @TypeConverter
    fun stringToAddresses(value: String): List<ShipmentEntity.Address> {
        return Gson().fromJson(value, Array<ShipmentEntity.Address>::class.java).toList()
    }
}