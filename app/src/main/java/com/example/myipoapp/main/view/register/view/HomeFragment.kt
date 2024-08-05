package com.example.myipoapp.main.view.register.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.myipoapp.R
import com.example.myipoapp.databinding.FragmentHomeBinding
import com.example.myipoapp.main.view.common.util.TxtWatcher

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val listName = resources.getStringArray(R.array.testeName)
        val adapterName = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listName)
        binding.selectNameComplete.setAdapter(adapterName)

        binding.selectNameComplete.addTextChangedListener (watcher)

        binding.selectStartButton.setOnClickListener {
            Toast.makeText(requireContext(), "Partiu criar formulário!", Toast.LENGTH_SHORT).show()
        }

    }

    private val watcher = TxtWatcher{
        binding.selectStartButton.isEnabled = binding.selectNameComplete.text.toString().isNotEmpty()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}