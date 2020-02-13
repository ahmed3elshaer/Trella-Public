package com.trella.ui

import androidx.lifecycle.MutableLiveData
import com.trella.common.base.BaseViewModel
import com.trella.common.viewstate.BaseViewState
import com.trella.domain.GetShipmentsUseCase
import com.trella.domain.model.Shipment
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getShipmentsUseCase: GetShipmentsUseCase
) : BaseViewModel<HomeViewState>() {
    override val viewStateImpl: MutableLiveData<HomeViewState> = MutableLiveData()

    init {
        post(HomeViewState())
    }

    fun getShipments(latitude: Double? = null, longitude: Double? = null) {
        getShipmentsUseCase.getAllShipments(latitude, longitude)
            .doOnSubscribe { showLoading() }
            .map { HomePayload(it, latitude != null) }
            .subscribe(this::postPayload,this::postError)
            .addDisposable()
    }


    private fun showLoading() {
        post(previousValue().copy(status = BaseViewState.Status.LOADING))
    }

    private fun postPayload(payload: HomePayload) {
        post(
            previousValue().copy(
                payload = payload,
                status = BaseViewState.Status.SUCCESS,
                error = null
            )
        )
    }

    private fun postError(error: Throwable) {
        post(
            previousValue().copy(
                status = BaseViewState.Status.FAILURE,
                error = error
            )
        )
    }

}