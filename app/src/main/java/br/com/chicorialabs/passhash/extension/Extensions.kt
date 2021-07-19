package br.com.chicorialabs.passhash.extension

import br.com.chicorialabs.passhash.data.Password
import br.com.chicorialabs.passhash.ui.main.MainViewModel
import java.security.MessageDigest

fun List<MainViewModel.PasswordDto>.asString(): String {
    return StringBuilder().run {
        this@asString.forEach {
            this.append("id: ${it.id}, " +
                    "password: ${it.password}\nhash: ${it.hash.takeLast(16)}\n****\n")
        }
        this.toString()
    }
}



fun Password.applyHash(hashAlgorithm: String) : String {
    val bytes = MessageDigest.getInstance(hashAlgorithm).digest(this.password.toByteArray())
    return bytes.joinToString("") {
        "%02x".format(it)
    }
}