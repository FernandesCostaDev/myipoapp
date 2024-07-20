package com.example.myipoapp.main.view.database

import android.app.Application

class App {
    class App: Application() {
        lateinit var db: AppDatabase
        override fun onCreate() {
            super.onCreate()
            db = AppDatabase.getDatabase(this)
        }
    }
}