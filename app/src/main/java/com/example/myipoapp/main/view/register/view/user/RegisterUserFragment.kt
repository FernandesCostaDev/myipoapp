package com.example.myipoapp.main.view.register.view.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.myipoapp.R
import com.example.myipoapp.databinding.FragmentRegisterUserBinding
import com.example.myipoapp.main.view.common.util.TxtWatcher
import com.example.myipoapp.main.view.database.App
import com.example.myipoapp.main.view.database.users.User
import com.example.myipoapp.main.view.main.view.MainActivity

class RegisterUserFragment : Fragment(R.layout.fragment_register_user) {

    private var binding: FragmentRegisterUserBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterUserBinding.bind(view)

        var cod = 0
        var fireman = ""
        var crbm = ""
        var obm = ""

        binding?.let {
            with(it) {

                val itemGraduacao = resources.getStringArray(R.array.graduacao)
                val adapterGraduacao = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    itemGraduacao
                )
                registerAutoGraduacao.setAdapter(adapterGraduacao)

                val itemsCrbm = resources.getStringArray(R.array.crbm)
                val adapterCrbm =
                    ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemsCrbm)
                registerAutoCrbm.setAdapter(adapterCrbm)

                registerAutoCrbm.addTextChangedListener {
                    crbm = registerAutoCrbm.text.toString()
                    when (crbm) {
                        "1º CRBM Curitiba" -> {
                            val itemsObm = resources.getStringArray(R.array.obm_1crbm)
                            val adapterObm =ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,itemsObm)
                            registerAutoObm.setAdapter(adapterObm)
                        }

                        "2º CRBM Londrina" -> {
                            val itemsObm = resources.getStringArray(R.array.obm_2crbm)
                            val adapterObm =
                                ArrayAdapter(
                                    requireContext(),
                                    android.R.layout.simple_list_item_1,
                                    itemsObm
                                )
                            registerAutoObm.setAdapter(adapterObm)
                        }

                        "3º CRBM Cascavel" -> {
                            val itemsObm = resources.getStringArray(R.array.obm_3crbm)
                            val adapterObm =
                                ArrayAdapter(
                                    requireContext(),
                                    android.R.layout.simple_list_item_1,
                                    itemsObm
                                )
                            registerAutoObm.setAdapter(adapterObm)
                        }
                    }
                }

                registerEditName.addTextChangedListener(watcher)
                registerAutoGraduacao.addTextChangedListener(watcher)
                registerAutoCrbm.addTextChangedListener(watcher)
                registerAutoObm.addTextChangedListener(watcher)
                registerTxtFireman.addTextChangedListener(watcher)
                registerTxtCrbm.addTextChangedListener(watcher)
                registerTxtObm.addTextChangedListener(watcher)

                registerButtonAdd.setOnClickListener {
                    val service =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    service.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)

                    fireman = registerAutoGraduacao.text.toString()
                        .trim() + " " + registerEditName.text.toString().trim()
                    crbm = registerAutoCrbm.text.toString()
                    obm = registerAutoObm.text.toString()

                    registerTxtFireman.text = fireman
                    registerTxtCrbm.text = crbm
                    registerTxtObm.text = obm
                }

                registerButtonSave.setOnClickListener {
                    val graduation = registerAutoGraduacao.text.toString()

                    when (graduation) {
                        "Sd. QPBM" -> {
                            cod = 1
                        }
                        "Cb. QPBM" -> {
                            cod = 2
                        }
                        "3º Sgt. QPBM" -> {
                            cod = 3
                        }
                        "2º Sgt. QPBM" -> {
                            cod = 4
                        }
                        "1º Sgt. QPBM" -> {
                            cod = 5
                        }
                        "Subten. QPBM" -> {
                            cod = 6
                        }
                        "Asp. QOBM" -> {
                            cod = 7
                        }
                        "2º Ten. QOBM" -> {
                            cod = 8
                        }
                        "1º Ten. QOBM" -> {
                            cod = 9
                        }
                    }

                    Thread {
                        val app = requireActivity().application as App.App
                        val dao = app.db.userDao()
                        dao.insert(
                            User(
                                cod = cod,
                                fireman = fireman,
                                crbm = crbm,
                                obm = obm
                            )
                        )
                        requireActivity().runOnUiThread {
                          Toast.makeText(requireContext(), "Dados salvo com sucesso! \uD83E\uDDD1\u200D\uD83D\uDE92", Toast.LENGTH_SHORT).show()
                            val intent = Intent(requireContext(), MainActivity::class.java)
                            startActivity(intent)
                        }
                    }.start()
                }
            }
        }
    }

    private val watcher = TxtWatcher {
        binding?.registerButtonAdd?.isEnabled =
            binding?.registerEditName?.text.toString().isNotEmpty() &&
                    binding?.registerAutoGraduacao?.text.toString().isNotEmpty() &&
                    binding?.registerAutoCrbm?.text.toString().isNotEmpty() &&
                    binding?.registerAutoObm?.text.toString().isNotEmpty()

        binding?.registerButtonSave?.isEnabled =
            binding?.registerTxtFireman?.text.toString().isNotEmpty() &&
                    binding?.registerTxtCrbm?.text.toString().isNotEmpty() &&
                    binding?.registerTxtObm?.text.toString().isNotEmpty()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}