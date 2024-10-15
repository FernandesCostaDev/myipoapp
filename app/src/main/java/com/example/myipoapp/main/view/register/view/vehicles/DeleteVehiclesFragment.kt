package com.example.myipoapp.main.view.register.view.vehicles

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myipoapp.R
import com.example.myipoapp.databinding.FragmentDeleteVehiclesBinding
import com.example.myipoapp.main.view.common.util.OnListClickListenerVehicles
import com.example.myipoapp.main.view.database.App
import com.example.myipoapp.main.view.database.vehicles.Vehicles

class DeleteVehiclesFragment : Fragment(R.layout.fragment_delete_vehicles),
    OnListClickListenerVehicles {

    private var binding: FragmentDeleteVehiclesBinding? = null
    private lateinit var list: MutableList<Vehicles>
    private lateinit var adapter: AdapterVehicles
    private lateinit var rv: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDeleteVehiclesBinding.bind(view)

        this.list = mutableListOf<Vehicles>()
        adapter = AdapterVehicles(list, this)

        rv = binding!!.rvDelVehicles
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter


        Thread {
            val app = requireActivity().application as App.App
            val dao = app.db.vehiclesDao()
            val result = dao.query()

            requireActivity().runOnUiThread() {
                list.addAll(result)
                adapter.notifyDataSetChanged()
            }
        }.start()

    }

    private inner class AdapterVehicles(
        private val list: List<Vehicles>,
        private val listener: DeleteVehiclesFragment
    ) : RecyclerView.Adapter<AdapterVehicles.VehiclesViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclesViewHolder {
            val view = layoutInflater.inflate(R.layout.item_vehicles, parent, false)
            return VehiclesViewHolder(view)
        }

        override fun onBindViewHolder(holder: VehiclesViewHolder, position: Int) {
            val itemCurrent = list[position]
            holder.bind(itemCurrent)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        private inner class VehiclesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: Vehicles) {
                val tvVehicles = itemView.findViewById<TextView>(R.id.delete_txt_vehicle)
                tvVehicles.text = item.prefix

                val imgDelete = itemView.findViewById<ImageView>(R.id.delete_img_delete)
                imgDelete.setOnClickListener {
                    listener.onLongClick(adapterPosition, item)
                }

                tvVehicles.setOnClickListener {
                    listener.onClick(item.id, "prefix")
                }
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onClick(id: Int, type: String) {

    }

    override fun onLongClick(position: Int, vehicles: Vehicles) {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.msg_delete_vehicle))
            .setNegativeButton(android.R.string.cancel) { dialog, which ->
            }
            .setPositiveButton(android.R.string.ok) { dialog, which ->

                Thread {
                    val app = requireActivity().application as App.App
                    val dao = app.db.vehiclesDao()
                    val result = dao.delete(vehicles)

                    if (result > 0) {
                        requireActivity().runOnUiThread() {
                            list.removeAt(position)
                            adapter.notifyItemRemoved(position)
                        }
                    }

                }.start()
            }
            .create()
            .show()

    }
}