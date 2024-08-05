package com.example.myipoapp.main.view.main.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myipoapp.R
import com.example.myipoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainBottomNav.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.menu_bottom_home -> {

                    true
                }
                R.id.menu_bottom_userAdd-> {

                    true
                }
                R.id.menu_bottom_userDel -> {

                    true
                }
                else -> false
            }
        }
    }
}