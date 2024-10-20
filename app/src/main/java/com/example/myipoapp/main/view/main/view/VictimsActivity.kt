package com.example.myipoapp.main.view.main.view

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVictimsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.victimsToolbar
        setSupportActionBar(toolbar)
        toolbar.overflowIcon?.setTint(Color.WHITE)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.brasao_bombeiro_p)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding) {
            with(binding) {
               victimsEditUnharmed.addTextChangedListener(watcher)
                victimsEditCode1.addTextChangedListener(watcher)
                victimsEditCod2.addTextChangedListener(watcher)
                victimsEditCod3.addTextChangedListener(watcher)
                victimsEditCod4.addTextChangedListener(watcher)

                victimsRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                    selectedOption = when (checkedId) {
                        R.id.radioButtonSim -> "Sim"
                        R.id.radioButtonNao -> "Não"
                        else -> null
                    }

                    when (selectedOption) {
                        "Não" -> {
                            victimsButtonNext.isEnabled = true
                            textView1.isEnabled = false
                            textView2.isEnabled = false
                            textView3.isEnabled = false
                            textView4.isEnabled = false
                            textView5.isEnabled = false
                            textView6.isEnabled = false
                            textView7.isEnabled = false
                            textView8.isEnabled = false
                            victimsEditUnharmed.isEnabled = false
                            victimsEditCode1.isEnabled = false
                            victimsEditCod2.isEnabled = false
                            victimsEditCod3.isEnabled = false
                            victimsEditCod4.isEnabled = false
                            victimsTxtTotal.isEnabled = false
                            victimsEditObs.isEnabled = false
                        }

                        "Sim" -> {
                            textView1.isEnabled = true
                            textView2.isEnabled = true
                            textView3.isEnabled = true
                            textView4.isEnabled = true
                            textView5.isEnabled = true
                            textView6.isEnabled = true
                            textView7.isEnabled = true
                            textView8.isEnabled = true
                            victimsEditUnharmed.isEnabled = true
                            victimsEditCode1.isEnabled = true
                            victimsEditCod2.isEnabled = true
                            victimsEditCod3.isEnabled = true
                            victimsEditCod4.isEnabled = true
                            victimsTxtTotal.isEnabled = true
                            victimsEditObs.isEnabled = true
                            victimsButtonNext.isEnabled = victimsEditUnharmed.text.isNotEmpty() ||
                                    victimsEditCode1.text.isNotEmpty() ||
                                    victimsEditCod2.text.isNotEmpty() ||
                                    victimsEditCod3.text.isNotEmpty() ||
                                    victimsEditCod4.text.isNotEmpty()

                        }
                        else -> {
                            victimsButtonNext.isEnabled = false
                        }
                    }
                }
            }
        }
    }

    private val watcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (selectedOption == "Sim") {
                binding.victimsButtonNext.isEnabled = s?.isNotEmpty() == true
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}



