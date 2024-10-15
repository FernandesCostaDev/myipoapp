package com.example.myipoapp.main.view.database.users

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM User ORDER BY cod ASC")
    fun query(): List<User>

    @Query("SELECT * FROM User WHERE fireman = :fireman")
    fun queryTwo(fireman: String): List<User>

    @Delete
    fun delete(user: User):Int
}