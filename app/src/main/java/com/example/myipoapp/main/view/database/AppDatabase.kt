package com.example.myipoapp.main.view.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myipoapp.main.view.database.users.User
import com.example.myipoapp.main.view.database.users.UserDao
import com.example.myipoapp.main.view.database.vehicles.Vehicles
import com.example.myipoapp.main.view.database.vehicles.VehiclesDao

@Database(entities = [User::class, Vehicles::class], version = 4)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun vehiclesDao(): VehiclesDao

    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "my_database"
                    ).fallbackToDestructiveMigration()
                     .build()

                }
                return INSTANCE as AppDatabase
            }else{
               return INSTANCE as AppDatabase
            }
        }
    }
}