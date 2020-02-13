package com.trella.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trella.R
import com.trella.domain.model.Shipment
import kotlinx.android.synthetic.main.shipment_item.view.*

class ShipmentsAdapter :
    ListAdapter<Shipment, ShipmentsAdapter.ViewHolder>(ShipmentsDiffUtils()) {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(shipment: Shipment) = with(itemView) {
            text_from.text = shipment.fromAddress.name
            text_to.text = shipment.toAddress.name
            text_num_of_bids.text = "Number of bids (${shipment.numberOfBids})"
            button_vehicle.text = shipment.vehicleType
            text_commodity.text = shipment.commodity
            text_price.text = shipment.price.toString() + " EGP"
        }
    }

    class ShipmentsDiffUtils : DiffUtil.ItemCallback<Shipment>() {
        override fun areItemsTheSame(oldItem: Shipment, newItem: Shipment): Boolean =
            oldItem.key == newItem.key


        override fun areContentsTheSame(oldItem: Shipment, newItem: Shipment): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.shipment_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}