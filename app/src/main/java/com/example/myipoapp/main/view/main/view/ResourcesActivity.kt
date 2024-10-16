package com.example.myipoapp.main.view.main.view

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

        replaceFragment(ResourcesFragment())

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


