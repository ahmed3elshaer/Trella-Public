package com.trella.ui

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.trella.common.location.RxLocationExt
import com.trella.R
import com.trella.common.base.BaseActivity
import com.trella.common.extensions.hide
import com.trella.common.extensions.isNetworkAvailable
import com.trella.common.extensions.show
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity<HomePayload, HomeViewState, HomeViewModel>() {
    private lateinit var shipmentsAdapter: ShipmentsAdapter
    private val rxLocation: RxLocationExt = RxLocationExt()
    private val compositeDisposable = CompositeDisposable()


    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun getLoadingView(): View? = lottie_loading

    override fun setupViews() {
        shipmentsAdapter = ShipmentsAdapter()
        recycler_shipments.itemAnimator = DefaultItemAnimator()
        recycler_shipments.adapter = shipmentsAdapter
    }

    override fun triggerEvents() {
        viewModel.getShipments()
        listenForModeSwitch()
    }

    private fun listenForModeSwitch() {
        text_nearby.setOnClickListener {
            triggerNearby()
        }
        layout_bottom.setOnClickListener {
            triggerNearby()
        }
    }

    private fun triggerNearby() {
        if (text_nearby.text == getString(R.string.show_nearby))
            getLocation()
        else
            viewModel.getShipments()
    }

    private fun getLocation() {
        if (isNetworkAvailable()) {
            compositeDisposable.add(initRxLocation())
        } else {
            viewModel.getShipments()
        }
    }

    private fun initRxLocation() = rxLocation.locations(this, true)
        .subscribe({ location ->
            viewModel.getShipments(location.latitude, location.longitude)
        },
            { error: Throwable ->
                error.message?.let {
                    renderError(it)
                }

            })


    override fun renderPayload(payload: HomePayload) {
        shipmentsAdapter.submitList(payload.shipments)
        if (payload.isNearby)
            renderNearbyState()
        else
            renderIdleState()
    }

    private fun renderIdleState() {
        text_nearby.text = getString(R.string.show_nearby)
        lottie_switch.pauseAnimation()
        lottie_switch.progress = 0.0f
        lottie_switch.hide()
    }

    private fun renderNearbyState() {
        text_nearby.text = getString(R.string.showing_nearby)
        lottie_switch.show()
        lottie_switch.playAnimation()
    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        rxLocation.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        rxLocation.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}
