package com.trella.ui

import com.trella.common.viewstate.BaseViewState
import com.trella.domain.model.Shipment

data class HomeViewState(
    override val status: Status = Status.IDLE,
    override val payload: HomePayload = HomePayload(),
    override val error: Throwable? = null
) : BaseViewState<HomePayload>(status, payload, error)

data class HomePayload(
    val shipments: List<Shipment> = listOf(),
    val isNearby: Boolean = false
)