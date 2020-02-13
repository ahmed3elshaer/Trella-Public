package com.trella.utils

import com.trella.domain.model.Shipment

object DataGenerator {

    fun getShipments() = listOf(getShipment(), getShipment(), getShipment())

    fun getShipment() = Shipment()

    const val lat = 30.0456862

    const val lng = 31.2195405

    val throwable = Throwable("test error")
}