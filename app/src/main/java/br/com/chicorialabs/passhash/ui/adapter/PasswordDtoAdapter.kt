package br.com.chicorialabs.passhash.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.chicorialabs.passhash.R
import br.com.chicorialabs.passhash.databinding.ItemPasswordDtoBinding
import br.com.chicorialabs.passhash.ui.main.MainViewModel

// TODO 001: Converter o Adapter em uma classe ListAdapter<T, VH>()
// TODO 003: Eliminar o campo listPasswordDto
class PasswordDtoAdapter(
    private val listPasswordDto: List<MainViewModel.PasswordDto>,
    var onClickListener: (passwordDto: MainViewModel.PasswordDto) -> Unit = {}
) : RecyclerView.Adapter<PasswordDtoAdapter.PasswordDtoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordDtoViewHolder =
        PasswordDtoViewHolder.from(parent)

    override fun onBindViewHolder(holder: PasswordDtoViewHolder, position: Int) {
        val item = listPasswordDto[position]
        holder.bind(item, onClickListener)
    }

//  TODO 004: Eliminar o método getItemCount()
    override fun getItemCount(): Int = listPasswordDto.size

    class PasswordDtoViewHolder private constructor(private val binding: ItemPasswordDtoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: MainViewModel.PasswordDto,
            onClickListener: (passwordDto: MainViewModel.PasswordDto) -> Unit
        ) {
            with(binding) {
                passwordDto = item
                itemEditBtn.setOnClickListener {
                    onClickListener(item)
                }
                executePendingBindings()
            }
        }

        companion object {
            fun from(parent: ViewGroup): PasswordDtoViewHolder {
                val binding: ItemPasswordDtoBinding = ItemPasswordDtoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return PasswordDtoViewHolder(binding)
            }
        }

    }

//    TODO 002: Criar um PasswordDtoCallback()


}




