package com.example.myipoapp.main.view.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myipoapp.R
import com.example.myipoapp.databinding.ActivitySupportAgencyBinding

class SupportAgencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySupportAgencyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySupportAgencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val returnFireman = intent.getStringExtra("fireman")
        val returnCrbm = intent.getStringExtra("crbm")
        val returnObm = intent.getStringExtra("obm")
        val returnDate = intent.getStringExtra("date")
        val returnTime = intent.getStringExtra("time")
        val returnNature = intent.getStringExtra("nature")
        val returnSubNature = intent.getStringExtra("subNature")
        val returnCity = intent.getStringExtra("city")
        val returnStreet = intent.getStringExtra("street")
        val returnNeighborhood = intent.getStringExtra("neighborhood")
        val returnComplement = intent.getStringExtra("complement")
        val returnCb = intent.getStringExtra("cb")
        val returnNumber = intent.getStringExtra("number")
        val returnVehicles = intent.getStringExtra("vehicles")
        val returnUnharmed = intent.getIntExtra("unharmed", 0)
        val returnCode1 = intent.getIntExtra("code1", 0)
        val returnCode2 = intent.getIntExtra("code2", 0)
        val returnCode3 = intent.getIntExtra("code3", 0)
        val returnCode4 = intent.getIntExtra("code4", 0)
        val returnTotal = intent.getIntExtra("total", 0)
        val returnObsVictims = intent.getStringExtra("obsVictims")
        val returnEnvironment = intent.getStringExtra("environment")
        val returnProperty = intent.getStringExtra("property")
        val returnScenery = intent.getStringExtra("scenery")
        val returnUnfolding = intent.getStringExtra("unfolding")

        with(binding){
            textview1.text = returnFireman
            textview2.text = returnCrbm
            textview3.text = returnObm
            textview4.text = returnDate
            textview5.text = returnTime
            textview6.text = returnNature
            textview8.text = returnSubNature
            textview9.text = returnCity
            textview10.text = returnStreet
            textview11.text = returnNeighborhood
            textview12.text = returnComplement
            textview13.text = returnCb
            textview14.text = returnNumber
            textview15.text = returnVehicles
            textview16.text = returnUnharmed.toString()
            textview17.text = returnCode1.toString()
            textview18.text = returnCode2.toString()
            textview19.text = returnCode3.toString()
            textview20.text = returnCode4.toString()
            textview21.text = returnTotal.toString()
            textview22.text = returnObsVictims
            textview23.text = returnEnvironment
            textview24.text = returnProperty
            textview25.text = returnScenery
            textview26.text = returnUnfolding
        }
    }
}