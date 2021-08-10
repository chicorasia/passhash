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

class PasswordDtoAdapter(
    var onClickListener: (passwordDto: MainViewModel.PasswordDto) -> Unit = {}
) : ListAdapter<MainViewModel.PasswordDto,
        PasswordDtoAdapter.PasswordDtoViewHolder>(PasswordDtoCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordDtoViewHolder =
        PasswordDtoViewHolder.from(parent)

    override fun onBindViewHolder(holder: PasswordDtoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onClickListener)
    }

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

    class PasswordDtoCallback : DiffUtil.ItemCallback<MainViewModel.PasswordDto>() {
        override fun areItemsTheSame(
            oldItem: MainViewModel.PasswordDto,
            newItem: MainViewModel.PasswordDto
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MainViewModel.PasswordDto,
            newItem: MainViewModel.PasswordDto
        ): Boolean {
            return oldItem.password == newItem.password && oldItem.hash == newItem.hash
        }

    }



}




