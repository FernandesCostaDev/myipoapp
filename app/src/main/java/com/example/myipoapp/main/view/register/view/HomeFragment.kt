package com.example.myipoapp.main.view.register.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myipoapp.R
import com.example.myipoapp.databinding.FragmentHomeBinding
import com.example.myipoapp.main.view.common.util.TxtWatcher
import com.example.myipoapp.main.view.database.App
import com.example.myipoapp.main.view.main.view.InitialActivity

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private lateinit var list: MutableList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        list = mutableListOf<String>()

        binding?.let { it ->
            with(it) {

                homeAutoFireman.addTextChangedListener(watcher)

                Thread {
                    val app = requireActivity().application as App.App
                    val dao = app.db.userDao()
                    val result = dao.query()
                    val name = result.map {
                        it.fireman
                    }
                    requireActivity().runOnUiThread {
                        list.addAll(name)
                        val adapter =
                            ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_list_item_1,
                                list
                            )
                        homeAutoFireman.setAdapter(adapter)
                    }
                }.start()


                homeButtonStart.setOnClickListener {
                    val firemanId = homeAutoFireman.text.toString()
                    var fireman = ""
                    var crbm = ""
                    var obm = ""

                    Thread {
                        val app = requireActivity().application as App.App
                        val dao = app.db.userDao()
                        val resultado = dao.queryTwo(firemanId)

                        for (perfilUser in resultado) {
                            fireman = perfilUser.fireman.toString()
                            crbm = perfilUser.crbm.toString()
                            obm = perfilUser.obm.toString()
                        }
                        requireActivity().runOnUiThread {
                            val intent = Intent(requireContext(), InitialActivity::class.java)
                            intent.putExtra("fireman", fireman)
                            intent.putExtra("crbm", crbm)
                            intent.putExtra("obm", obm)
                            startActivity(intent)
                        }
                    }.start()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_options, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private val watcher = TxtWatcher{
        binding?.homeButtonStart?.isEnabled = binding?.homeAutoFireman?.text.toString().isNotEmpty()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}