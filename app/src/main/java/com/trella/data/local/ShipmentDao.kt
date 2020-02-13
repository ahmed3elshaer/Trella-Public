package com.trella.common.local

import androidx.room.*
import com.trella.data.models.ShipmentLocal
import io.reactivex.Observable

@Dao
@TypeConverters(ShipmentTypeConverter::class)
interface ShipmentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shipment: ShipmentLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg shipment: ShipmentLocal)

    @Query("SELECT * FROM shipments")
    fun allShipments(): Observable<List<ShipmentLocal>>

    @Query("DELETE  FROM shipments")
    fun clearAll()
}