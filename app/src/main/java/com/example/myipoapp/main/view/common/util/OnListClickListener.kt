package com.example.myipoapp.main.view.common.util

import com.example.myipoapp.main.view.database.User


interface OnListClickListener {
    fun onClick(id: Int, type: String)
    fun onLongClick(position: Int, usuario: User)
}