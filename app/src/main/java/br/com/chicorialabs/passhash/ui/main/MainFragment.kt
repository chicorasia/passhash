package br.com.chicorialabs.passhash.ui.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.chicorialabs.passhash.R
import br.com.chicorialabs.passhash.databinding.AddPasswordDialogBinding
import br.com.chicorialabs.passhash.databinding.MainFragmentBinding
import br.com.chicorialabs.passhash.ui.adapter.PasswordDtoAdapter
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

//    TODO 005: Submeter a lista para o ListAdapter
    private fun initPasswordList() {
        mMainViewModel.passwordDtoList.observe(viewLifecycleOwner) { passwordDtoList ->
            val adapter = PasswordDtoAdapter(passwordDtoList)
            adapter.onClickListener = { passwordDto ->
                createUpdadePasswordDialog(passwordDto)
            }
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        }

    }

    private fun createUpdadePasswordDialog(passwordDto: MainViewModel.PasswordDto) {
        val dialogBinding = AddPasswordDialogBinding.inflate(layoutInflater)
        dialogBinding.addDialogEdt.setText(passwordDto.password)
        AlertDialog.Builder(activity)
            .setView(dialogBinding.root)
            .setCancelable(true)
            .setTitle(getString(R.string.edit_password_dialog))
            .setNegativeButton(getString(R.string.cancel), null)
            .setPositiveButton(getString(R.string.ok), DialogInterface.OnClickListener { _, _ ->
                mMainViewModel.update(passwordDto.id,
                    dialogBinding.addDialogEdt.text.toString())
            })
            .create()
            .show()
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
            .setTitle(getString(R.string.add_new_password))
            .setNegativeButton(getString(R.string.cancel), null)
            .setPositiveButton(getString(R.string.ok), DialogInterface.OnClickListener { _, _ ->
                mMainViewModel.save(dialogBinding.addDialogEdt.text.toString())
            })
            .create()
            .show()
    }

}