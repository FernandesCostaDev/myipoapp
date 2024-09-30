package com.example.myipoapp.main.view.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM User ORDER BY fireman ASC")
    fun query(): List<User>

    @Delete
    fun delete(user: User):Int
}