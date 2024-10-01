package com.example.myipoapp.main.view.register.view

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

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private lateinit var list: MutableList<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        list = mutableListOf<String>()

        binding?.let { it ->
            with(it) {

                selectNameComplete.addTextChangedListener(watcher)

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
                        selectNameComplete.setAdapter(adapter)
                    }
                }.start()
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
        binding?.selectStartButton?.isEnabled = binding?.selectNameComplete?.text.toString().isNotEmpty()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}