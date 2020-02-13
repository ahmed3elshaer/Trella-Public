package com.trella.data

import com.trella.common.base.Mapper
import com.trella.data.models.ShipmentEntity
import com.trella.domain.model.Shipment

class ShipmentEntityToShipmentMapper : Mapper<ShipmentEntity, Shipment>() {
    override fun map(value: ShipmentEntity): Shipment = value.let {
        val addressMapper = AddressEntityToAddress()
        Shipment(
            it.commodity,
            it.price,
            it.numberOfBids,
            it.key,
            it.vehicleType,
            addressMapper.map(it.addresses.find { it.order == 1 }!!),
            addressMapper.map(it.addresses.find { it.order == 2 }!!)
        )
    }


    inner class AddressEntityToAddress : Mapper<ShipmentEntity.Address, Shipment.Address>() {
        override fun map(value: ShipmentEntity.Address): Shipment.Address = value.let {
            Shipment.Address(it.name, it.key)
        }
    }

}