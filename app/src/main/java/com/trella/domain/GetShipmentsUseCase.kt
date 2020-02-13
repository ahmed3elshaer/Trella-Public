package com.trella.domain

import com.trella.common.base.BaseUseCase
import com.trella.common.schedulers.BaseSchedulerProvider
import com.trella.ui.home.HomeRepository
import javax.inject.Inject

class GetShipmentsUseCase @Inject constructor(
    private val repository: HomeRepository,
    baseSchedulerProvider: BaseSchedulerProvider
) : BaseUseCase(baseSchedulerProvider) {

    fun getAllShipments() = repository.getShipments(null, null)
        .compose(applySchedulers())

    fun getNearByShipments(latitude: Double, longitude: Double) =
        repository.getShipments(latitude, longitude)
            .compose(applySchedulers())

}