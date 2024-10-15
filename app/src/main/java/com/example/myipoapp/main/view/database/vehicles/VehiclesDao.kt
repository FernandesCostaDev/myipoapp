package com.example.myipoapp.main.view.database.vehicles

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VehiclesDao {
    @Insert
    fun insert(vehicles: Vehicles)

    @Query("SELECT * FROM Vehicles ORDER BY cod ASC")
    fun query(): List<Vehicles>

    @Query("SELECT * FROM Vehicles WHERE prefix = :prefix")
    fun queryTwo(prefix: String): List<Vehicles>

    @Delete
    fun delete(vehicles: Vehicles):Int
}