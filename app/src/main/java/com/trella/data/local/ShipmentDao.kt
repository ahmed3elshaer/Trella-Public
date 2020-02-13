package com.trella.data.local

import androidx.room.*
import com.trella.data.models.ShipmentEntity
import io.reactivex.Observable

@Dao
@TypeConverters(ShipmentTypeConverter::class)
interface ShipmentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shipment: ShipmentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg shipment: ShipmentEntity)

    @Query("SELECT * FROM shipments")
    fun allShipments(): Observable<List<ShipmentEntity>>

    @Query("DELETE  FROM shipments")
    fun clearAll()
}