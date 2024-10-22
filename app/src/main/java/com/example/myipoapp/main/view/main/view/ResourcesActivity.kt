package com.example.myipoapp.main.view.main.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myipoapp.R
import com.example.myipoapp.databinding.ActivityResourcesBinding
import com.example.myipoapp.main.view.register.view.vehicles.DeleteVehiclesFragment
import com.example.myipoapp.main.view.register.view.vehicles.RegisterVehiclesFragment
import com.example.myipoapp.main.view.register.view.vehicles.ResourcesFragment

class ResourcesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResourcesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.resourcesToolbar
        setSupportActionBar(toolbar)
        toolbar.overflowIcon?.setTint(Color.WHITE)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.brasao_bombeiro_p)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val returnCity = intent.getStringExtra("city")
        val returnStreet = intent.getStringExtra("street")
        val returnNeighborhood = intent.getStringExtra("neighborhood")
        val returnComplement = intent.getStringExtra("complement")
        val returnDate = intent.getStringExtra("date")
        val returnTime = intent.getStringExtra("time")
        val returnNature = intent.getStringExtra("nature")
        val returnSubNature = intent.getStringExtra("subNature")
        val returnCrbm = intent.getStringExtra("crbm")
        val returnObm = intent.getStringExtra("obm")
        val returnFireman = intent.getStringExtra("fireman")

        val fragment = ResourcesFragment()
        val bundle = Bundle()
        bundle.putString("city", returnCity)
        bundle.putString("street", returnStreet)
        bundle.putString("neighborhood", returnNeighborhood)
        bundle.putString("complement", returnComplement)
        bundle.putString("date", returnDate)
        bundle.putString("time", returnTime)
        bundle.putString("nature", returnNature)
        bundle.putString("subNature", returnSubNature)
        bundle.putString("crbm", returnCrbm)
        bundle.putString("obm", returnObm)
        bundle.putString("fireman", returnFireman)
        fragment.arguments = bundle
        replaceFragment(fragment)





        with(binding) {
            resourcesBottomNav.setOnItemSelectedListener { item ->
                when (item.itemId) {

                    R.id.menu_bottom_resources -> {
                        replaceFragment(ResourcesFragment())
                        true
                    }

                    R.id.menu_bottom_VehicleAdd -> {
                        replaceFragment(RegisterVehiclesFragment())
                        true
                    }

                    R.id.menu_bottom_VehiclesDel -> {
                        replaceFragment(DeleteVehiclesFragment())
                        true
                    }

                    else -> false
                }
            }

        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.resources_frame, fragment)
        fragmentTransaction.commit()
    }
}


