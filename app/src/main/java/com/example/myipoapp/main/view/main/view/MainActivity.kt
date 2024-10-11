package com.example.myipoapp.main.view.main.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.example.myipoapp.R
import com.example.myipoapp.databinding.ActivityMainBinding
import com.example.myipoapp.main.view.register.view.user.DeleteUserFragment
import com.example.myipoapp.main.view.register.view.user.HomeFragment
import com.example.myipoapp.main.view.register.view.user.RegisterUserFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        window.statusBarColor = getColor(R.color.blue2)

        val toolbar = binding.mainToolbar
        setSupportActionBar(toolbar)
        toolbar.overflowIcon?.setTint(Color.WHITE)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.brasao_bombeiro_p)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.mainBottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.menu_bottom_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_bottom_userAdd-> {
                    replaceFragment(RegisterUserFragment())
                    true
                }

                R.id.menu_bottom_userDel -> {
                    replaceFragment(DeleteUserFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame, fragment)
        fragmentTransaction.commit()
    }

}