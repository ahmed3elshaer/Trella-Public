package com.trella.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    fun getShipments() {
        getShipmentsUseCase.getAllShipments()
            .doOnSubscribe { showLoading() }
            .doOnError(this::postError)
            .subscribe(this::postPayload)
            .addDisposable()
    }

    fun getShipmentsNearBy(latitude: Double, longitude: Double) {
        getShipmentsUseCase.getNearByShipments(latitude, longitude)
            .doOnSubscribe { showLoading() }
            .doOnError(this::postError)
            .subscribe(this::postPayload)
            .addDisposable()
    }

    private fun showLoading() {
        post(previousValue().copy(status = BaseViewState.Status.LOADING))
    }

    private fun postPayload(payload: List<Shipment>) {
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
                payload = null,
                status = BaseViewState.Status.FAILURE,
                error = error
            )
        )
    }

}