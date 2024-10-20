package com.example.myipoapp.main.view.database.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
@PrimaryKey(autoGenerate = true) val id: Int = 0,
@ColumnInfo(name = "cod") val cod: Int,
@ColumnInfo(name = "fireman") val fireman: String,
@ColumnInfo(name = "crbm") val crbm: String,
@ColumnInfo(name = "obm") val obm: String
)
