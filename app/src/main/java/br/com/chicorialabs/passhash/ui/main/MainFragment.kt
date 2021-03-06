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

    // TODO 008: Inicializar os buttons de algoritmo
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