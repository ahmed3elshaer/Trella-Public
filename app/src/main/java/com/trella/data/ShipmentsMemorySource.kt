package com.trella.data

import com.trella.data.models.ShipmentEntity
import io.reactivex.Observable
import java.util.*

class ShipmentsMemorySource {
    private var shipments: MutableList<ShipmentEntity> = mutableListOf()

    fun getShipments() = Observable.create<List<ShipmentEntity>> {
        if (shipments.isNotEmpty())
            it.onNext(shipments)
        it.onComplete()
    }

    fun cache(shipments: List<ShipmentEntity>) {
        this.shipments.apply {
            clear()
            addAll(shipments)
        }
    }
}