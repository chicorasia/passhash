package br.com.chicorialabs.passhash.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.chicorialabs.passhash.R
import br.com.chicorialabs.passhash.databinding.ItemPasswordDtoBinding
import br.com.chicorialabs.passhash.ui.main.MainViewModel


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

    override fun getItemCount(): Int = listPasswordDto.size

    class PasswordDtoViewHolder private constructor(private val binding: ItemPasswordDtoBinding) :
        RecyclerView.ViewHolder(binding.root) {

//        Tarefas do arquivo BindingUtils


//        TODO 012: Refatoração



        fun bind(
            item: MainViewModel.PasswordDto,
            onClickListener: (passwordDto: MainViewModel.PasswordDto) -> Unit
        ) {
            with(binding) {
                passwordDto = item
                itemEditBtn.setOnClickListener {
                    onClickListener(item)
                }
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

}




