package com.example.myipoapp.main.view.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.Room

@Database(entities = [User::class], version = 3)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
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