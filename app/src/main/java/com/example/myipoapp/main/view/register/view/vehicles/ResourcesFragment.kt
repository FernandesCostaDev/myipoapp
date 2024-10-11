package com.example.myipoapp.main.view.register.view.vehicles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.myipoapp.R
import com.example.myipoapp.databinding.FragmentResourcesBinding


class ResourcesFragment : Fragment(R.layout.fragment_resources) {

    private var binding: FragmentResourcesBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResourcesBinding.bind(view)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}