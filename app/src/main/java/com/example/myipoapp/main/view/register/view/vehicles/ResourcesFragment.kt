package com.example.myipoapp.main.view.register.view.vehicles

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myipoapp.R
import com.example.myipoapp.databinding.FragmentResourcesBinding
import com.example.myipoapp.main.view.common.util.ResourcesViewModel
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

    private lateinit var adapterCbActivated: ArrayAdapter<String>
    private lateinit var viewModel: ResourcesViewModel

    @SuppressLint("NotifyDataSetChanged")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResourcesBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity()).get(ResourcesViewModel::class.java)

      /*  val returnFireman = arguments?.getString("fireman")
        val returnCrbm = arguments?.getString("crbm")
        val returnObm = arguments?.getString("obm")
        val returnDate = arguments?.getString("date")
        val returnTime = arguments?.getString("time")
        val returnNature = arguments?.getString("nature")
        val returnSubNature = arguments?.getString("subNature")
        val returnCity = arguments?.getString("city")
        val returnStreet = arguments?.getString("street")
        val returnNeighborhood = arguments?.getString("neighborhood")
        val returnComplement = arguments?.getString("complement")*/

        val sharedPreferences = requireActivity().getSharedPreferences("ResourcesData", Context.MODE_PRIVATE)
        val returnFireman = sharedPreferences.getString("fireman", "")
        val returnCrbm = sharedPreferences.getString("crbm", "")
        val returnObm = sharedPreferences.getString("obm", "")
        val returnDate = sharedPreferences.getString("date", "")
        val returnTime = sharedPreferences.getString("time", "")
        val returnNature = sharedPreferences.getString("nature", "")
        val returnSubNature = sharedPreferences.getString("subNature", "")
        val returnCity = sharedPreferences.getString("city", "")
        val returnStreet = sharedPreferences.getString("street", "")
        val returnNeighborhood = sharedPreferences.getString("neighborhood", "")
        val returnComplement = sharedPreferences.getString("complement", "")

        this.list = mutableListOf<Vehicles>()

        viewModel.cbActivatedData.observe(viewLifecycleOwner) { cbActivated ->
            adapterCbActivated =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, cbActivated)
            binding?.resourcesAutoCb?.setAdapter(adapterCbActivated)
        }

        binding?.let {
            with(it) {
                when (returnObm) {
                    "1º GB Curitiba" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.gbCuritiba)
                    "2º GB Ponta Grossa" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.gbPontaGrossa)
                    "3º GB Londrina" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.gbLondrina)
                    "4º GB Cascavel" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.gbCascavel)
                    "5º GB Maringá" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.gbMaringa)
                    "6º GB São José dos Pinhais" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.gbSaoJosePinhais)
                    "7º GB Colombo" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.gbColombo)
                    "8º GB Paranaguá" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.gbParanaguá)
                    "9º GB Foz do Iguaçu" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.gbFozIguaçu)
                    "10º GB Francisco Beltrão" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.gbFrancisco)
                    "11º GB Apucarana" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.gbApucarana)
                    "12º GB Guarapuava" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.gbGuarapuava)
                    "13º GB Pato Branco" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.gbPatoBranco)
                    "1º SGBI Ivaiporã" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.sgbiIvaipora)
                    "6º SGBI Umuarama" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.sgbiUmuarama)
                    "7º SGBI Santo Antonio da Platina" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.sgbiSap)
                    "8º SGBI Cianorte" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.sgbiCianorte)
                    "9º SGBI Paranavaí" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.sgbiParanavai)
                    "10º SGBI Irati" -> viewModel.cbActivatedData.value = resources.getStringArray(R.array.sgbiIrati)
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
                        list.clear()
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

                    val sharedPreferences = requireActivity().getSharedPreferences("ResourcesData", Context.MODE_PRIVATE)
                    sharedPreferences.edit().clear().apply()

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

                checkBox.setOnCheckedChangeListener(null)

                checkBox.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        if (selectedItems.size < 5) {
                            selectedItems.add(item)
                        } else {
                            checkBox.isChecked = false

                            Toast.makeText(
                                itemView.context,
                                "Você pode selecionar no máximo 5 viaturas.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {

                        selectedItems.remove(item)
                    }

                    updateTextView()
                }
            }
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