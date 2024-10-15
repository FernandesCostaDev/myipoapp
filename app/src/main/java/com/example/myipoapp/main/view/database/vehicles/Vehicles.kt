package com.example.myipoapp.main.view.database.vehicles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//toda vez que acrecentar uma coluna ou excluir atualizar o version no db
@Entity
data class Vehicles(
@PrimaryKey(autoGenerate = true) val id: Int = 0,
@ColumnInfo(name = "cod") val cod: Int,
@ColumnInfo(name = "prefix") val prefix: String,
)
