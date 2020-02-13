package com.trella.ui

import android.util.Log
import android.view.View
import com.trella.R
import com.trella.common.base.BaseActivity
import com.trella.domain.model.Shipment

class HomeActivity : BaseActivity<List<Shipment>, HomeViewState, HomeViewModel>() {


    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun getLoadingView(): View? = null

    override fun triggerEvents() {
        viewModel.getShipments()
    }

    override fun renderPayload(viewState: List<Shipment>) {
        Log.d("TrellaTask", viewState.size.toString())
    }
}
