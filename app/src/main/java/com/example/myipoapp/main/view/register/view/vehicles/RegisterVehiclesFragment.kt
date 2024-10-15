package com.example.myipoapp.main.view.register.view.vehicles


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myipoapp.R
import com.example.myipoapp.databinding.FragmentRegisterVehiclesBinding
import com.example.myipoapp.main.view.common.util.TxtWatcher
import com.example.myipoapp.main.view.database.App
import com.example.myipoapp.main.view.database.users.User
import com.example.myipoapp.main.view.database.vehicles.Vehicles
import com.example.myipoapp.main.view.main.view.MainActivity
import com.example.myipoapp.main.view.main.view.ResourcesActivity


class RegisterVehiclesFragment : Fragment(R.layout.fragment_register_vehicles) {
    private var binding: FragmentRegisterVehiclesBinding? = null
    private lateinit var prefix: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterVehiclesBinding.bind(view)

        var cod = 0

        binding?.let {
            with(it) {
                registerVehiclesAutoType.addTextChangedListener(watcher)
                registerVehiclesEditNumber.addTextChangedListener(watcher)
                registerVehiclesTxtPrefix.addTextChangedListener(watcher)

                val itemsTypology = resources.getStringArray(R.array.typology)
                val adapterTypology =
                    ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        itemsTypology
                    )
                registerVehiclesAutoType.setAdapter(adapterTypology)

                registerVehiclesButtonAdd.setOnClickListener {
                    val service =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    service.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)

                    prefix =
                        registerVehiclesAutoType.text.toString() + " " + registerVehiclesEditNumber.text.toString()
                    binding?.registerVehiclesTxtPrefix?.text = prefix
                }


                registerVehiclesButtonSave.setOnClickListener {
                    val type = registerVehiclesAutoType.text.toString()

                    when (type) {
                        "ABTR:" -> {
                            cod = 1
                        }

                        "AA:" -> {
                            cod = 2
                        }

                        "ABS:" -> {
                            cod = 3
                        }

                        "ABT:" -> {
                            cod = 4
                        }

                        "AT:" -> {
                            cod = 5
                        }

                        "ATP:" -> {
                            cod = 6
                        }
                    }

                    Thread {
                        val app = requireActivity().application as App.App
                        val dao = app.db.vehiclesDao()
                        dao.insert(
                            Vehicles(
                                cod = cod,
                                prefix = prefix
                            )
                        )
                        requireActivity().runOnUiThread {
                            Toast.makeText(requireContext(), "Dados salvo com sucesso! \uD83E\uDDD1\u200D\uD83D\uDE92", Toast.LENGTH_SHORT).show()
                            val intent = Intent(requireContext(), ResourcesActivity::class.java)
                            startActivity(intent)
                        }
                    }.start()

                }
            }
        }
    }

    private val watcher = TxtWatcher {
        binding?.registerVehiclesButtonAdd?.isEnabled =
            binding?.registerVehiclesAutoType?.text.toString().isNotEmpty() &&
                    binding?.registerVehiclesEditNumber?.text.toString().isNotEmpty()

        binding?.registerVehiclesButtonSave?.isEnabled =
            binding?.registerVehiclesTxtPrefix?.text.toString().isNotEmpty()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}