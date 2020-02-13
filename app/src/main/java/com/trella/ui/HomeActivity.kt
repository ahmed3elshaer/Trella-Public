package com.trella.ui

import android.view.View
import com.trella.R
import com.trella.common.base.BaseActivity
import com.trella.common.extensions.hide
import com.trella.common.extensions.show
import com.trella.domain.model.Shipment
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity<List<Shipment>, HomeViewState, HomeViewModel>() {
    private lateinit var shipmentsAdapter: ShipmentsAdapter
    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun getLoadingView(): View? = lottie_loading
    override fun setupViews() {
        shipmentsAdapter = ShipmentsAdapter()
        recycler_shipments.adapter = shipmentsAdapter
    }

    override fun triggerEvents() {
        viewModel.getShipments()
    }

    override fun renderPayload(payload: List<Shipment>) {
        shipmentsAdapter.submitList(payload)
    }

    override fun renderLoading(isLoading: Boolean) {
        super.renderLoading(isLoading)
        if (isLoading)
            recycler_shipments.hide().also { bottom_nearby.hide() }
        else
            recycler_shipments.show().also { bottom_nearby.show() }
    }


}
