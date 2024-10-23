package com.example.myipoapp.main.view.main.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myipoapp.R
import com.example.myipoapp.databinding.ActivityOtherInformationBinding
import com.example.myipoapp.main.view.common.util.TxtWatcher
import com.google.android.material.snackbar.Snackbar

class OtherInformationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtherInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtherInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.otherInformationToolbar
        setSupportActionBar(toolbar)
        toolbar.overflowIcon?.setTint(Color.WHITE)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.brasao_bombeiro_p)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

        with(binding) {
            otherInformationIconHelperEnvironment.setOnClickListener {
                Snackbar.make(
                    it,
                    getString(R.string.otherInformation_Snackbar_helpEnvironment),
                    Snackbar.LENGTH_LONG
                ).setDuration(5000).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show()
            }

            otherInformationIconHelperProperty.setOnClickListener {
                Snackbar.make(
                    it,
                    getString(R.string.otherInformation_Snackbar_helpProperty),
                    Snackbar.LENGTH_LONG
                ).setDuration(5000).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show()
            }

            otherInformationIconHelperScenery.setOnClickListener {
                Snackbar.make(
                    it,
                    getString(R.string.otherInformation_Snackbar_helpScenery),
                    Snackbar.LENGTH_LONG
                ).setDuration(5000).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show()
            }

            otherInformationTextFiledUnfolding.addTextChangedListener(watcher)

            otherInformationIconHelperUnfolding.setOnClickListener {
                Snackbar.make(
                    it,
                    getString(R.string.otherInformation_Snackbar_helpUnfolding),
                    Snackbar.LENGTH_LONG
                ).setDuration(5000).setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show()
            }

            otherInformationButtonNext.setOnClickListener {
                val environment = otherInformationTextFiledEnvironment.text.toString().ifEmpty { "Não Informado." }
                val property = otherInformationTextFiledProperty.text.toString().ifEmpty { "Não Informado." }
                val scenery = otherInformationTextFiledScenery.text.toString().ifEmpty { "Não Informado." }
                val unfolding = otherInformationTextFiledUnfolding.text.toString()

                val intent = Intent(this@OtherInformationActivity, SupportAgencyActivity::class.java)
                intent.putExtra("environment", environment)
                intent.putExtra("property", property)
                intent.putExtra("scenery", scenery)
                intent.putExtra("unfolding", unfolding)
                intent.putExtra("fireman", returnFireman)
                intent.putExtra("crbm", returnCrbm)
                intent.putExtra("obm", returnObm)
                intent.putExtra("date", returnDate)
                intent.putExtra("time", returnTime)
                intent.putExtra("nature", returnNature)
                intent.putExtra("subNature", returnSubNature)
                intent.putExtra("city", returnCity)
                intent.putExtra("street", returnStreet)
                intent.putExtra("neighborhood", returnNeighborhood)
                intent.putExtra("complement", returnComplement)
                intent.putExtra("cb", returnCb)
                intent.putExtra("number", returnNumber)
                intent.putExtra("vehicles", returnVehicles)
                intent.putExtra("unharmed", returnUnharmed)
                intent.putExtra("code1", returnCode1)
                intent.putExtra("code2", returnCode2)
                intent.putExtra("code3", returnCode3)
                intent.putExtra("code4", returnCode4)
                intent.putExtra("total", returnTotal)
                intent.putExtra("obsVictims", returnObsVictims)
                startActivity(intent)

            }
        }
    }

    private val watcher = TxtWatcher {
        binding.otherInformationButtonNext.isEnabled = binding.otherInformationTextFiledUnfolding.text.toString().isNotEmpty()
    }
}