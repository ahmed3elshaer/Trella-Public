package com.trella.common.local

import android.location.Address
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.trella.data.models.ShipmentLocal

class ShipmentTypeConverter {
    @TypeConverter
    fun addressToJSON(addresses: List<ShipmentLocal.Address>): String {
        return Gson().toJson(addresses)
    }

    @TypeConverter
    fun stringToAddresses(value: String): List<ShipmentLocal.Address> {
        return Gson().fromJson(value, Array<ShipmentLocal.Address>::class.java).toList()
    }
}