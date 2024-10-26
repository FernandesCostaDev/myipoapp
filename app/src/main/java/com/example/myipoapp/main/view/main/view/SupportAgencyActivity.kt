package com.example.myipoapp.main.view.main.view

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myipoapp.R
import com.example.myipoapp.databinding.ActivitySupportAgencyBinding
import com.example.myipoapp.main.view.common.util.TxtWatcher

class SupportAgencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySupportAgencyBinding
    private val maxSelection = 5
    private var selectionSupport = ""
    private val selectedValues = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySupportAgencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.supportToolbar
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
        val returnEnvironment = intent.getStringExtra("environment")
        val returnProperty = intent.getStringExtra("property")
        val returnScenery = intent.getStringExtra("scenery")
        val returnUnfolding = intent.getStringExtra("unfolding")

        with(binding){

            supportTxtListSupport.addTextChangedListener(watcher)

            val checkBoxes = listOf(
                ckBox1, ckBox2, ckBox3, ckBox4, ckBox5,
                ckBox6, ckBox7, ckBox8, ckBox10,
                ckBox11, ckBox12, ckBox13, ckBox14, ckBox15,
                ckBox16, ckBox17, ckBox18, ckBox19, ckBox20,
                ckBox21, ckBox22, ckBox23, ckBox24, ckBox25,
                ckBox26, ckBox27, ckBox28, ckBox29, ckBox30,
                ckBox31, ckBox32, ckBox33, ckBox34, ckBox35
            )

            for (checkBox in checkBoxes) {
                checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                    val value = buttonView.text.toString()
                    if (isChecked) {
                        if (selectedValues.size < maxSelection) {
                            selectedValues.add(value)
                        } else {
                            buttonView.isChecked = false
                            Toast.makeText(
                                this@SupportAgencyActivity,
                                "Só pode ser selecionado no máximo $maxSelection orgão de apoio",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        selectedValues.remove(value)
                    }
                    updateTextView()
                }
            }
        }
    }

    private fun updateTextView() {
        selectionSupport = selectedValues.joinToString(", ")
        binding.supportTxtListSupport.text = selectedValues.joinToString(", ")
    }

    private val watcher = TxtWatcher {
        binding.supportButtonNext.isEnabled = binding.supportTxtListSupport.text.isNotEmpty()
    }
}