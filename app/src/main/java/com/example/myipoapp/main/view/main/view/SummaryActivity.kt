package com.example.myipoapp.main.view.main.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myipoapp.R
import com.example.myipoapp.databinding.ActivitySummaryBinding

class SummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.summaryToolbar
        setSupportActionBar(toolbar)
        toolbar.overflowIcon?.setTint(Color.WHITE)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.brasao_bombeiro_p)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}