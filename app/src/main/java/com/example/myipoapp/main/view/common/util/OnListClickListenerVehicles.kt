package com.example.myipoapp.main.view.common.util

import com.example.myipoapp.main.view.database.vehicles.Vehicles


interface OnListClickListenerVehicles {
    fun onClick(id: Int, type: String)
    fun onLongClick(position: Int, vehicles: Vehicles)
}