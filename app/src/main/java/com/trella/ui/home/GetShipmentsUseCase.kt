package com.trella.ui.home

import com.trella.common.base.BaseUseCase
import com.trella.common.schedulers.BaseSchedulerProvider
import javax.inject.Inject

class GetShipmentsUseCase @Inject constructor(
    private val repository: HomeRepository,
    baseSchedulerProvider: BaseSchedulerProvider
) : BaseUseCase(baseSchedulerProvider) {

    fun getAllShipments() = repository.getShipments(null, null)
        .compose(applySchedulers())

    fun getNearByShipments() = repository.getShipments(null, null)
        .compose(applySchedulers())
}