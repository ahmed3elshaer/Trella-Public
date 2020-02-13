package com.trella.ui.home

import com.trella.common.local.ShipmentsDao
import com.trella.data.remote.HomeApi
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeApi: HomeApi,
    private val shipmentsDao: ShipmentsDao
) {

    fun getShipments(latitude: Double? = null, longitude: Double? = null) =
        homeApi.getShipments(latitude, longitude)

}