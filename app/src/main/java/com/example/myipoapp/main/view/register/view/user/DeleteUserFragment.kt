package com.example.myipoapp.main.view.register.view.user

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
import com.example.myipoapp.databinding.FragmentDeleteUserBinding
import com.example.myipoapp.main.view.common.util.OnListClickListener
import com.example.myipoapp.main.view.database.App
import com.example.myipoapp.main.view.database.User

class DeleteUserFragment : Fragment(R.layout.fragment_delete_user), OnListClickListener {
    private var binding: FragmentDeleteUserBinding? = null
    private lateinit var list: MutableList<User>
    private lateinit var adapter: AdapterNames
    private lateinit var rv: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDeleteUserBinding.bind(view)

        this.list = mutableListOf<User>()

        adapter = AdapterNames(list, this)

        rv = binding!!.rvDelUser
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        Thread {
            val app = requireActivity().application as App.App
            val dao = app.db.userDao()
            val resultado = dao.query()

            requireActivity().runOnUiThread() {
                list.addAll(resultado)
                adapter.notifyDataSetChanged()
            }
        }.start()
    }

    private inner class AdapterNames(private val list: List<User>, private val listener: DeleteUserFragment) : RecyclerView.Adapter<AdapterNames.NamesViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NamesViewHolder {
            val view = layoutInflater.inflate(R.layout.item_names, parent, false)
            return NamesViewHolder(view)
        }

        override fun onBindViewHolder(holder: NamesViewHolder, position: Int) {
            val itemCurrent = list[position]
            holder.bind(itemCurrent)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        private inner class NamesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(item: User) {
                val tvName = itemView.findViewById<TextView>(R.id.delete_txt_name)
                tvName.text = item.fireman

                val imgDelete = itemView.findViewById<ImageView>(R.id.delete_img_delete)
                imgDelete.setOnClickListener {
                    listener.onLongClick(adapterPosition, item)
                }

                tvName.setOnClickListener {
                    listener.onClick(item.id, "name")
                }
            }
        }
    }

    override fun onClick(id: Int, type: String) {
    }

    override fun onLongClick(position: Int, usuario: User) {

        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.msg_deletar))
            .setNegativeButton(android.R.string.cancel) { dialog, which ->
            }
            .setPositiveButton(android.R.string.ok) { dialog, which ->

                Thread {
                    val app = requireActivity().application as App.App
                    val dao = app.db.userDao()
                    val result = dao.delete(usuario)

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