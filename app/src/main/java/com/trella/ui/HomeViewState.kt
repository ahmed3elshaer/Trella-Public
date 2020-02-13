package com.trella.ui

import com.trella.common.viewstate.BaseViewState
import com.trella.domain.model.Shipment

 data class HomeViewState(
     override val status: Status = Status.IDLE,
     override val payload: List<Shipment>? = null,
     override val error: Throwable? = null
) : BaseViewState<List<Shipment>>(status, payload, error)