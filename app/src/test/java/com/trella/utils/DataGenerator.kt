package com.trella.utils

import com.trella.data.models.ShipmentEntity
import com.trella.domain.model.Shipment

object DataGenerator {

    fun getShipments() = listOf(getShipment(), getShipment(), getShipment())

    private fun getShipment() = Shipment()

    fun threeEntityShipments() = listOf(ShipmentEntity(), ShipmentEntity(), ShipmentEntity())

    fun fourEntityShipments() =
        listOf(ShipmentEntity(), ShipmentEntity(), ShipmentEntity(), ShipmentEntity())

    fun twoEntityShipments() = listOf(ShipmentEntity(), ShipmentEntity())

    const val lat = 30.0456862

    const val lng = 31.2195405

    val throwable = Throwable("test error")
}