package com.example.myipoapp.main.view.main.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myipoapp.R
import com.example.myipoapp.databinding.ActivityAddressBinding
import com.example.myipoapp.main.view.common.util.TxtWatcher

class AddressActivity : AppCompatActivity() {
   private lateinit var binding: ActivityAddressBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.addressToolbar
        setSupportActionBar(toolbar)
        toolbar.overflowIcon?.setTint(Color.WHITE)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.brasao_bombeiro_p)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        with(binding) {
            val returnFireman = intent.getStringExtra("fireman")
            val returnCrbm = intent.getStringExtra("crbm")
            val returnObm = intent.getStringExtra("obm")
            val returnDate = intent.getStringExtra("date")
            val returnTime = intent.getStringExtra("time")
            val returnNature = intent.getStringExtra("nature")
            val returnSubNature = intent.getStringExtra("subNature")

            addressAutoCompleteCity.addTextChangedListener(watcher)
            addressAutoCompleteStreet.addTextChangedListener(watcher)
            addressAutoCompleteNeighborhood.addTextChangedListener(watcher)

            val itemCity = resources.getStringArray(R.array.cities)
            val adapterCity = ArrayAdapter(this@AddressActivity, android.R.layout.simple_list_item_1, itemCity)
            addressAutoCompleteCity.setAdapter(adapterCity)

            val itemStreet = resources.getStringArray(R.array.streets)
            val adapterStreet = ArrayAdapter(this@AddressActivity, android.R.layout.simple_list_item_1, itemStreet)
            addressAutoCompleteStreet.setAdapter(adapterStreet)

            addressButtonNext.setOnClickListener {
                val city = addressAutoCompleteCity.text.toString()
                val street = addressAutoCompleteStreet.text.toString()
                val neighborhood = addressAutoCompleteNeighborhood.text.toString()
                val complement = addressEditComplement.text.toString()

                val intent = Intent(this@AddressActivity, ResourcesActivity::class.java)
                intent.putExtra("city", city)
                intent.putExtra("street", street)
                intent.putExtra("neighborhood", neighborhood)
                intent.putExtra("complement", complement)
                intent.putExtra("date", returnDate)
                intent.putExtra("time", returnTime)
                intent.putExtra("nature", returnNature)
                intent.putExtra("subNature", returnSubNature)
                intent.putExtra("fireman", returnFireman)
                intent.putExtra("crbm", returnCrbm)
                intent.putExtra("obm", returnObm)
                startActivity(intent)
            }
        }
    }
    private val watcher = TxtWatcher {
        binding.addressButtonNext.isEnabled = binding.addressAutoCompleteCity.text.isNotEmpty() &&
                binding.addressAutoCompleteStreet.text.isNotEmpty() &&
                binding.addressAutoCompleteNeighborhood.text.isNotEmpty()
    }
}