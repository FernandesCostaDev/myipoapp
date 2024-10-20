package com.example.myipoapp.main.view.main.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import androidx.core.widget.addTextChangedListener
import com.example.myipoapp.R
import com.example.myipoapp.databinding.ActivityInitialBinding
import com.example.myipoapp.main.view.common.util.TxtWatcher
import java.util.Calendar

class InitialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInitialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.initialToolbar
        setSupportActionBar(toolbar)
        toolbar.overflowIcon?.setTint(Color.WHITE)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.brasao_bombeiro_p)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding) {
            initialTxtDate.addTextChangedListener(watcher)
            initialTxtTime.addTextChangedListener(watcher)
            initialAutoNature.addTextChangedListener(watcher)
            initialAutoSubNature.addTextChangedListener(watcher)

            initialBtnCalendar.setOnClickListener {
                showDatePickerDialog()
            }

            initialBtnTimer.setOnClickListener {
                timePicker()
            }

            val itemNature = resources.getStringArray(R.array.natureEvent)
            val adapterNature = ArrayAdapter(this@InitialActivity, android.R.layout.simple_list_item_1, itemNature)
            initialAutoNature.setAdapter(adapterNature)

            initialAutoNature.addTextChangedListener {
                val selectedNature = initialAutoNature.text.toString()

                when (selectedNature) {
                    "Acidente de Trânsito" -> {
                        val itemAcidenteTransito =resources.getStringArray(R.array.subNaturezaAcidenteTransito)
                        val adapterAcidenteTransito = ArrayAdapter(this@InitialActivity,android.R.layout.simple_list_item_1, itemAcidenteTransito)
                       initialAutoSubNature.setAdapter(adapterAcidenteTransito)
                    }

                    "APH" -> {
                        val itemAph = resources.getStringArray(R.array.subNaturezaAPH)
                        val adapterAph =
                            ArrayAdapter(this@InitialActivity, android.R.layout.simple_list_item_1, itemAph)
                        initialAutoSubNature.setAdapter(adapterAph)
                    }

                    "Atendimento Comunitário" -> {
                        val itemComunitario =
                            resources.getStringArray(R.array.subNaturezaAtendimentoComunitario)
                        val adapterComunitario =
                            ArrayAdapter(this@InitialActivity, android.R.layout.simple_list_item_1, itemComunitario)
                        initialAutoSubNature.setAdapter(adapterComunitario)
                    }

                    "Busca e Salvamento" -> {
                        val itemBuscaSalvamento =
                            resources.getStringArray(R.array.subNaturezaBuscaSalvamento)
                        val adapterBuscaSalvamento =
                            ArrayAdapter(this@InitialActivity, android.R.layout.simple_list_item_1, itemBuscaSalvamento)
                        initialAutoSubNature.setAdapter(adapterBuscaSalvamento)
                    }

                    "Desastre" -> {
                        val itemDesastre = resources.getStringArray(R.array.subNaturezaDesastre)
                        val adapterDesatre =
                            ArrayAdapter(this@InitialActivity, android.R.layout.simple_list_item_1, itemDesastre)
                        initialAutoSubNature.setAdapter(adapterDesatre)
                    }

                    "Incêndio" -> {
                        val itemIncendio = resources.getStringArray(R.array.subNaturezaIncendio)
                        val adapterIncendio =
                            ArrayAdapter(this@InitialActivity, android.R.layout.simple_list_item_1, itemIncendio)
                        initialAutoSubNature.setAdapter(adapterIncendio)
                    }
                }

            }

            initialButtonNext.setOnClickListener {
                val date = initialTxtDate.text.toString()
                val time = initialTxtTime.text.toString()
                val nature = initialAutoNature.text.toString()
                val subNature = initialAutoSubNature.text.toString()
                val returnFireman = intent.getStringExtra("fireman")
                val returnCrbm = intent.getStringExtra("crbm")
                val returnObm = intent.getStringExtra("obm")

                val intent = Intent(this@InitialActivity, AddressActivity::class.java)
                intent.putExtra("date", date)
                intent.putExtra("time", time)
                intent.putExtra("nature", nature)
                intent.putExtra("subNature", subNature)
                intent.putExtra("fireman", returnFireman)
                intent.putExtra("crbm", returnCrbm)
                intent.putExtra("obm", returnObm)
                startActivity(intent)
            }
        }
    }

    private fun timePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                binding.initialTxtTime.text = selectedTime
            },
            hour, minute, true
        )
        timePickerDialog.show()
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = String.format("%02d/%02d/%04d",selectedDay,selectedMonth + 1,selectedYear)
                binding.initialTxtDate.text = selectedDate
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private val watcher = TxtWatcher {
        binding.initialButtonNext.isEnabled = binding.initialTxtDate.text.isNotEmpty() &&
                binding.initialTxtTime.text.isNotEmpty() &&
                binding.initialAutoNature.text.isNotEmpty() &&
                binding.initialAutoSubNature.text.isNotEmpty()


    }
}