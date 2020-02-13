package com.trella.ui.home

import com.trella.data.local.ShipmentsDao
import com.trella.data.HomeApi
import com.trella.data.ShipmentEntityToShipmentMapper
import com.trella.data.ShipmentsMemorySource
import com.trella.data.models.ShipmentEntity
import io.reactivex.Observable
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeApi: HomeApi,
    private val shipmentsDao: ShipmentsDao,
    private val shipmentsMemorySource: ShipmentsMemorySource,
    private val shipmentEntityToShipmentMapper: ShipmentEntityToShipmentMapper
) {

    fun getShipments(latitude: Double? = null, longitude: Double? = null) =
        Observable.concat(
            getShipmentsRemote(latitude, longitude),
            getShipmentsMemory(),
            getShipmentsDisk()
        )
            .firstElement()
            .map(shipmentEntityToShipmentMapper::map)


    private fun getShipmentsRemote(
        latitude: Double? = null,
        longitude: Double? = null
    ): Observable<List<ShipmentEntity>> =
        homeApi.getShipments(latitude, longitude)
            .doOnNext { shipments ->
                shipmentsDao.clearAll()
                    .also { shipmentsDao.insertAll(*shipments.toTypedArray()) }
                    .also { shipmentsMemorySource.cache(shipments) }

            }

    private fun getShipmentsDisk(): Observable<List<ShipmentEntity>> = shipmentsDao.allShipments()
        .doOnNext { shipments ->
            shipmentsMemorySource.cache(shipments)
        }

    private fun getShipmentsMemory() = shipmentsMemorySource.getShipments()


}