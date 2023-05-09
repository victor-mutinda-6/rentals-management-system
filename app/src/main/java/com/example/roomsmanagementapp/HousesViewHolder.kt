package com.example.roomsmanagementapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class HousesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNumber: TextView = itemView.findViewById(R.id.houseNumber)
    var tvType: TextView = itemView.findViewById(R.id.houseType)
    var tvRent: TextView = itemView.findViewById(R.id.houseRent)
    var tvTenant: TextView = itemView.findViewById(R.id.houseTenant)
    var tvMore: TextView = itemView.findViewById(R.id.moreInfor)
    var deleteHouse: ImageView = itemView.findViewById(R.id.deleteHouse)
    var editHouse: ImageView = itemView.findViewById(R.id.editHouse)
}