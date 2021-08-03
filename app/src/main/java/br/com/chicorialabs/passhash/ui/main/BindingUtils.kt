package br.com.chicorialabs.passhash.ui.main

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.chicorialabs.passhash.R


@BindingAdapter("itemCheckIc")
fun ImageView.changeIcon(item: MainViewModel.PasswordDto?) {
    item?.let {
        setImageResource(
            when (item.password.length) {
                in 1..7 -> R.drawable.ic_baseline_warning_amber_24
                else -> R.drawable.ic_baseline_check_24
            }
        )
    }
}

@BindingAdapter("itemPasswordTv")
fun TextView.setPasswordWithColor(item: MainViewModel.PasswordDto?) {
    item?.let {
        setTextColor(Color.BLACK)
        text = item.password
        if (item.password.length <= 7) {
            setTextColor(Color.RED)
        }
    }

}

@BindingAdapter("itemHashTv")
fun TextView.setHashText(item: MainViewModel.PasswordDto?) {
    item?.let {
        text = item.hash
    }
}