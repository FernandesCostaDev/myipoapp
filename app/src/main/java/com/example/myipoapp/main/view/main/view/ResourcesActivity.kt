package com.example.myipoapp.main.view.main.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myipoapp.R
import com.example.myipoapp.databinding.ActivityResourcesBinding
import com.example.myipoapp.main.view.register.view.vehicles.DeleteVehiclesFragment
import com.example.myipoapp.main.view.register.view.vehicles.RegisterVehiclesFragment
import com.example.myipoapp.main.view.register.view.vehicles.ResourcesFragment

class ResourcesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResourcesBinding
    private lateinit var resourcesFragment: ResourcesFragment
    private lateinit var registerVehiclesFragment: RegisterVehiclesFragment
    private lateinit var deleteVehiclesFragment: DeleteVehiclesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.resourcesToolbar
        setSupportActionBar(toolbar)
        toolbar.overflowIcon?.setTint(Color.WHITE)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.brasao_bombeiro_p)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle = Bundle().apply {
            putString("city", intent.getStringExtra("city"))
            putString("street", intent.getStringExtra("street"))
            putString("neighborhood", intent.getStringExtra("neighborhood"))
            putString("complement", intent.getStringExtra("complement"))
            putString("date", intent.getStringExtra("date"))
            putString("time", intent.getStringExtra("time"))
            putString("nature", intent.getStringExtra("nature"))
            putString("subNature", intent.getStringExtra("subNature"))
            putString("crbm", intent.getStringExtra("crbm"))
            putString("obm", intent.getStringExtra("obm"))
            putString("fireman", intent.getStringExtra("fireman"))
        }

        resourcesFragment = ResourcesFragment().apply { arguments = bundle }
        registerVehiclesFragment = RegisterVehiclesFragment().apply { arguments = bundle }
        deleteVehiclesFragment = DeleteVehiclesFragment().apply { arguments = bundle }

        supportFragmentManager.beginTransaction().apply {
            add(R.id.resources_frame, resourcesFragment, "ResourcesFragment")
            add(R.id.resources_frame, registerVehiclesFragment, "RegisterVehiclesFragment")
            add(R.id.resources_frame, deleteVehiclesFragment, "DeleteVehiclesFragment")
            hide(registerVehiclesFragment)
            hide(deleteVehiclesFragment)
        }.commit()

        with(binding) {
            resourcesBottomNav.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_bottom_resources -> showFragment(resourcesFragment)
                    R.id.menu_bottom_VehicleAdd -> showFragment(registerVehiclesFragment)
                    R.id.menu_bottom_VehiclesDel -> showFragment(deleteVehiclesFragment)
                    else -> false
                }
                true
            }
        }
    }

    private fun showFragment(fragmentToShow: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            hide(resourcesFragment)
            hide(registerVehiclesFragment)
            hide(deleteVehiclesFragment)
            show(fragmentToShow)
        }.commit()
    }
}

