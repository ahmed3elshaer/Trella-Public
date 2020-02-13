/*
 * *
 *  * Created by Ahmed Elshaer on 10/26/19 4:17 AM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 10/26/19 3:34 AM
 *
 */

package com.trella.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.trella.data.models.ShipmentEntity

@TypeConverters(ShipmentTypeConverter::class)
@Database(
    entities = [ShipmentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ShipmentsDatabase : RoomDatabase() {
    abstract fun shipmentsDao(): ShipmentsDao

    companion object {
        fun getInstance(context: Context): ShipmentsDatabase =
            Room.databaseBuilder(context, ShipmentsDatabase::class.java, "ShipmentsDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }
}
