package com.trella.domain

import com.trella.common.base.BaseUseCase
import com.trella.common.schedulers.BaseSchedulerProvider
import com.trella.data.ShipmentEntityToShipmentMapper
import com.trella.domain.model.Shipment
import com.trella.ui.HomeRepository
import io.reactivex.Maybe
import javax.inject.Inject

class GetShipmentsUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val shipmentMapper: ShipmentEntityToShipmentMapper,
    baseSchedulerProvider: BaseSchedulerProvider
) : BaseUseCase(baseSchedulerProvider) {

    fun getAllShipments(latitude: Double? = null, longitude: Double? = null): Maybe<List<Shipment>> =
        repository.getShipments(latitude, longitude)
            .map(shipmentMapper::map)
            .compose(applySchedulersMaybe())


}