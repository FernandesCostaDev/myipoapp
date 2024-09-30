package com.example.myipoapp.main.view.register.view

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.myipoapp.R
import com.example.myipoapp.databinding.FragmentDeleteUserBinding
import com.example.myipoapp.main.view.common.util.OnListClickListener
import com.example.myipoapp.main.view.database.User

class DeleteUserFragment : Fragment(R.layout.fragment_delete_user), OnListClickListener {
    private var binding: FragmentDeleteUserBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDeleteUserBinding.bind(view)
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

    }

}