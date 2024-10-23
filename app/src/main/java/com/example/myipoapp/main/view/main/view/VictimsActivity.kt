package com.example.myipoapp.main.view.main.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.myipoapp.R
import com.example.myipoapp.databinding.ActivityVictimsBinding

class VictimsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVictimsBinding
    private var selectedOption: String? = null

    private var unharmed: Int = 0
    private var code1: Int = 0
    private var code2: Int = 0
    private var code3: Int = 0
    private var code4: Int = 0
    private var total: Int = 0
    private var obsVictims: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVictimsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.victimsToolbar
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

        with(binding) {
            victimsRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                selectedOption = when (checkedId) {
                    R.id.radioButtonSim -> "Sim"
                    R.id.radioButtonNao -> "Não"
                    else -> null
                }

                when (selectedOption) {
                    "Não" -> {
                        victimsButtonNext.isEnabled = true
                        clearVictimFields()
                        enableVictimFields(false)

                        victimsButtonNext.setOnClickListener {
                            val intent = Intent(this@VictimsActivity, OtherInformationActivity::class.java)

                            intent.putExtra("unharmed", unharmed)
                            intent.putExtra("code1", code1)
                            intent.putExtra("code2", code2)
                            intent.putExtra("code3", code3)
                            intent.putExtra("code4", code4)
                            intent.putExtra("total", total)
                            intent.putExtra("obsVictims", obsVictims)

                            intent.putExtra("cb", returnCb)
                            intent.putExtra("number", returnNumber)
                            intent.putExtra("vehicles", returnVehicles)

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
                            startActivity(intent)
                        }
                    }

                    "Sim" -> {
                        enableVictimFields(true)
                        victimsButtonNext.isEnabled =
                            false
                        victimsButtonNext.setOnClickListener {
                            unharmed = binding.victimsEditUnharmed.text.toString().toIntOrNull() ?: 0
                            code1 = binding.victimsEditCode1.text.toString().toIntOrNull() ?: 0
                            code2 = binding.victimsEditCod2.text.toString().toIntOrNull() ?: 0
                            code3 = binding.victimsEditCod3.text.toString().toIntOrNull() ?: 0
                            code4 = binding.victimsEditCod4.text.toString().toIntOrNull() ?: 0
                            obsVictims = binding.victimsEditObs.text.toString()

                            val intent = Intent(this@VictimsActivity, OtherInformationActivity::class.java)
                            intent.putExtra("unharmed", unharmed)
                            intent.putExtra("code1", code1)
                            intent.putExtra("code2", code2)
                            intent.putExtra("code3", code3)
                            intent.putExtra("code4", code4)
                            intent.putExtra("total", total)
                            intent.putExtra("obsVictims", obsVictims)

                            intent.putExtra("cb", returnCb)
                            intent.putExtra("number", returnNumber)
                            intent.putExtra("vehicles", returnVehicles)

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
                            startActivity(intent)
                        }
                    }
                    else -> {
                        victimsButtonNext.isEnabled = false
                    }
                }
            }
            victimsEditUnharmed.addTextChangedListener(watcher)
            victimsEditCode1.addTextChangedListener(watcher)
            victimsEditCod2.addTextChangedListener(watcher)
            victimsEditCod3.addTextChangedListener(watcher)
            victimsEditCod4.addTextChangedListener(watcher)
        }
    }


    private val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (selectedOption == "Sim") {
                binding.apply {
                    unharmed = victimsEditUnharmed.text.toString().toIntOrNull() ?: 0
                    code1 = victimsEditCode1.text.toString().toIntOrNull() ?: 0
                    code2 = victimsEditCod2.text.toString().toIntOrNull() ?: 0
                    code3 = victimsEditCod3.text.toString().toIntOrNull() ?: 0
                    code4 = victimsEditCod4.text.toString().toIntOrNull() ?: 0

                    total = unharmed + code1 + code2 + code3 + code4
                    victimsTxtTotal.setText(total.toString())
                    victimsButtonNext.isEnabled = total > 0
                }
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private fun enableVictimFields(enable: Boolean) {
        with(binding) {
            textView1.isEnabled = enable
            textView2.isEnabled = enable
            textView3.isEnabled = enable
            textView4.isEnabled = enable
            textView5.isEnabled = enable
            textView6.isEnabled = enable
            textView7.isEnabled = enable
            textView8.isEnabled = enable
            victimsEditUnharmed.isEnabled = enable
            victimsEditCode1.isEnabled = enable
            victimsEditCod2.isEnabled = enable
            victimsEditCod3.isEnabled = enable
            victimsEditCod4.isEnabled = enable
            victimsTxtTotal.isEnabled = enable
            victimsEditObs.isEnabled = enable
        }
    }
    private fun clearVictimFields() {
        binding.apply {
            victimsEditUnharmed.setText("")
            victimsEditCode1.setText("")
            victimsEditCod2.setText("")
            victimsEditCod3.setText("")
            victimsEditCod4.setText("")
            victimsTxtTotal.setText("")
            victimsEditObs.setText("")
        }
        unharmed = 0
        code1 = 0
        code2 = 0
        code3 = 0
        code4 = 0
        total = 0
        obsVictims = "Não Informado."
    }
}

