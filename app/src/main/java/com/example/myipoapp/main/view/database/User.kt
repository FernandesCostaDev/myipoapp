package com.example.myipoapp.main.view.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//toda vez que acrecentar uma coluna ou excluir atualizar o version no db
@Entity
data class User(
@PrimaryKey(autoGenerate = true) val id: Int = 0,
@ColumnInfo(name = "fireman") val fireman: String,
@ColumnInfo(name = "crbm") val crbm: String,
@ColumnInfo(name = "obm") val obm: String
)
