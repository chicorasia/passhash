package br.com.chicorialabs.passhash.ui.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.chicorialabs.passhash.databinding.AddPasswordDialogBinding
import br.com.chicorialabs.passhash.databinding.MainFragmentBinding
import br.com.chicorialabs.passhash.extension.asString
import org.koin.androidx.viewmodel.ext.android.viewModel

// Parte 1
// TODO 003: Criar um Adapter
// TODO 004: Criar a classe para o ViewHolder
// TODO 005: Inicializar o RecyclerView
// TODO 006: Criar um LayoutManager
// Parte 2: Melhores práticas
// TODO 007: Criar um método from() para inflar o ViewHolder
// TODO 008: Criar um método bind() dentro do ViewHolder
// TODO 009: Criar uma regra de negócio para destacar as senhas curtas demais
// TODO 010: Adicionar um onClickListener ao adapter

class MainFragment : Fragment() {

    private val mMainViewModel: MainViewModel by viewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initPasswordList()
        initAlgorithmBtns()

        return root
    }

    private fun initAlgorithmBtns() {
        binding.md5btn.setOnClickListener {
            mMainViewModel.setMd5()
        }

        binding.sha1btn.setOnClickListener {
            mMainViewModel.setSha1()
        }

        binding.sha256btn.setOnClickListener {
            mMainViewModel.setSha256()
        }
    }

    private fun initPasswordList() {
        mMainViewModel.passwordDtoList.observe(viewLifecycleOwner) {
            binding.passwordListTv.text = it.asString()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initFab()
    }

    private fun initFab() {
        binding.floatingActionButton.setOnClickListener {
            createSaveNewPasswordDialog()
        }
    }

    private fun createSaveNewPasswordDialog() {
        val dialogBinding = AddPasswordDialogBinding.inflate(layoutInflater)
        AlertDialog.Builder(activity)
            .setView(dialogBinding.root)
            .setCancelable(true)
            .setTitle("Add new password")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                mMainViewModel.save(dialogBinding.addDialogEdt.text.toString())
            })
            .create()
            .show()
    }

}