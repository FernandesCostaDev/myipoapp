package com.example.myipoapp.main.view.register.view.vehicles

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myipoapp.R
import com.example.myipoapp.databinding.FragmentResourcesBinding
import com.example.myipoapp.main.view.common.util.TxtWatcher
import com.example.myipoapp.main.view.database.App
import com.example.myipoapp.main.view.database.vehicles.Vehicles
import com.example.myipoapp.main.view.main.view.VictimsActivity


class ResourcesFragment : Fragment(R.layout.fragment_resources) {
    private var binding: FragmentResourcesBinding? = null
    private lateinit var list: MutableList<Vehicles>
    private lateinit var adapter: AdapterVehicles
    private lateinit var rv: RecyclerView
    private lateinit var txtVehicles: TextView
    private lateinit var cbActivated: Array<String>
    private lateinit var adapterCbActivated: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResourcesBinding.bind(view)

        val returnFireman = arguments?.getString("fireman")
        val returnCrbm = arguments?.getString("crbm")
        val returnObm = arguments?.getString("obm")
        val returnDate = arguments?.getString("date")
        val returnTime = arguments?.getString("time")
        val returnNature = arguments?.getString("nature")
        val returnSubNature = arguments?.getString("subNature")
        val returnCity = arguments?.getString("city")
        val returnStreet = arguments?.getString("street")
        val returnNeighborhood = arguments?.getString("neighborhood")
        val returnComplement = arguments?.getString("complement")

        this.list = mutableListOf<Vehicles>()

        binding?.let {
            with(it) {
                when (returnObm) {
                    "1º GB Curitiba" -> {
                        cbActivated = resources.getStringArray(R.array.gbCuritiba)
                        adapterCbActivated =
                            ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_list_item_1,
                                cbActivated
                            )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "2º GB Ponta Grossa" -> {
                        cbActivated = resources.getStringArray(R.array.gbPontaGrossa)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "3º GB Londrina" -> {
                        cbActivated = resources.getStringArray(R.array.gbLondrina)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "4º GB Cascavel" -> {
                        cbActivated = resources.getStringArray(R.array.gbCascavel)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "5º GB Maringá" -> {
                        cbActivated = resources.getStringArray(R.array.gbMaringa)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "6º GB São José dos Pinhais" -> {
                        cbActivated = resources.getStringArray(R.array.gbSaoJosePinhais)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "7º GB Colombo" -> {
                        cbActivated = resources.getStringArray(R.array.gbColombo)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "8º GB Paranaguá" -> {
                        cbActivated = resources.getStringArray(R.array.gbParanaguá)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "9º GB Foz do Iguaçu" -> {
                        cbActivated = resources.getStringArray(R.array.gbFozIguaçu)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "10º GB Francisco Beltrão" -> {
                        cbActivated = resources.getStringArray(R.array.gbFrancisco)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "11º GB Apucarana" -> {
                        cbActivated = resources.getStringArray(R.array.gbApucarana)
                        adapterCbActivated =
                            ArrayAdapter(
                                requireContext(),
                                android.R.layout.simple_list_item_1,
                                cbActivated
                            )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "12º GB Guarapuava" -> {
                        cbActivated = resources.getStringArray(R.array.gbGuarapuava)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "13º GB Pato Branco" -> {
                        cbActivated = resources.getStringArray(R.array.gbPatoBranco)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "1º SGBI Ivaiporã" -> {
                        cbActivated = resources.getStringArray(R.array.sgbiIvaipora)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "6º SGBI Umuarama" -> {
                        cbActivated = resources.getStringArray(R.array.sgbiUmuarama)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "7º SGBI Santo Antonio da Platina" -> {
                        cbActivated = resources.getStringArray(R.array.sgbiSap)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "8º SGBI Cianorte" -> {
                        cbActivated = resources.getStringArray(R.array.sgbiCianorte)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "9º SGBI Paranavaí" -> {
                        cbActivated = resources.getStringArray(R.array.sgbiParanavai)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }

                    "10º SGBI Irati" -> {
                        cbActivated = resources.getStringArray(R.array.sgbiIrati)
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            cbActivated
                        )
                        resourcesAutoCb.setAdapter(adapterCbActivated)
                    }
                }

                txtVehicles = resourcesTxtVehicles

                adapter = AdapterVehicles(list, txtVehicles)
                rv = resourcesRvVehicles
                rv.layoutManager = LinearLayoutManager(requireContext())
                rv.adapter = adapter

                Thread {
                    val app = requireActivity().application as App.App
                    val dao = app.db.vehiclesDao()
                    val result = dao.query()

                    requireActivity().runOnUiThread {
                        list.addAll(result)
                        adapter.notifyDataSetChanged()
                    }
                }.start()

                resourcesAutoCb.addTextChangedListener(watcher)
                resourcesEditTextNumber.addTextChangedListener(watcher)
                resourcesTxtVehicles.addTextChangedListener(watcher)

                resourcesButtonNext.setOnClickListener {
                    val cb = resourcesAutoCb.text.toString()
                    val number = resourcesEditTextNumber.text.toString()
                    val vehicles = resourcesTxtVehicles.text.toString()

                    val intent = Intent(requireContext(), VictimsActivity::class.java)
                    intent.putExtra("cb", cb)
                    intent.putExtra("number", number)
                    intent.putExtra("vehicles", vehicles)
                    intent.putExtra("city", returnCity)
                    intent.putExtra("street", returnStreet)
                    intent.putExtra("neighborhood", returnNeighborhood)
                    intent.putExtra("complement", returnComplement)
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
    }

    inner class AdapterVehicles(
        private val listVehicles: List<Vehicles>,
        private val txtEmpenhoVtr: TextView
    ) : RecyclerView.Adapter<AdapterVehicles.VehiclesViewHolder>() {

        private val selectedItems = mutableSetOf<Vehicles>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclesViewHolder {
            // Corrigido layoutInflater
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.checkbox_vehicles, parent, false)
            return VehiclesViewHolder(view)
        }

        override fun getItemCount(): Int {
            return listVehicles.size
        }

        override fun onBindViewHolder(holder: VehiclesViewHolder, position: Int) {
            val itemCurrent = listVehicles[position]
            holder.bind(itemCurrent)
        }

        override fun onViewRecycled(holder: VehiclesViewHolder) {
            holder.checkBox.setOnCheckedChangeListener(null)
            super.onViewRecycled(holder)
        }

        inner class VehiclesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val checkBox: CheckBox = itemView.findViewById(R.id.checkbox_)

            fun bind(item: Vehicles) {
                checkBox.text = item.prefix
                checkBox.isChecked = selectedItems.contains(item)

                // Limpa qualquer listener anterior
                checkBox.setOnCheckedChangeListener(null)

                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        if (selectedItems.size < 5) {
                            // Adiciona o item se o número de selecionados for menor que 5
                            selectedItems.add(item)
                        } else {
                            // Não permite marcar o CheckBox se já houver 5 selecionados
                            checkBox.isChecked = false

                            Toast.makeText(
                                itemView.context,
                                "Você pode selecionar no máximo 5 viaturas.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        // Remove o item da seleção se desmarcado
                        selectedItems.remove(item)
                    }

                    // Atualiza o TextView com os veículos selecionados
                    updateTextView()
                }
            }
        }

        fun getSelectedItems(): List<Vehicles> {
            return selectedItems.toList()
        }

        private fun updateTextView() {
            val selectedText = selectedItems.joinToString(", ") { it.prefix }
            txtEmpenhoVtr.text = selectedText
        }
    }

    private val watcher = TxtWatcher {
        binding?.resourcesButtonNext?.isEnabled = binding?.resourcesAutoCb?.text.toString().isNotEmpty() &&
                binding?.resourcesEditTextNumber?.text.toString().isNotEmpty() &&
                binding?.resourcesTxtVehicles?.text.toString().isNotEmpty()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}