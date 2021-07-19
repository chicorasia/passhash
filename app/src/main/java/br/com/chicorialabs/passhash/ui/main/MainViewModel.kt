package br.com.chicorialabs.passhash.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chicorialabs.passhash.data.Password
import br.com.chicorialabs.passhash.repository.PasswordRepository
import kotlinx.coroutines.launch

// TODO 001: Criar uma data class PasswordDto (data transfer object)
// TODO 002: Criar um atributo passwordsWithHash que aplica uma transformação aos objetos recebidos do Dao
// TODO 003: Criar uma extension function Password.applyMD5()
// TODO 004: Criar uma extension function para converter o List<PasswordDto> em uma string formatada
// TODO 006: Criar um campo MutableLiveData<String> para manter o valor de hashAlgorithm
// TODO 007: Criar métodos para setar o valor de _HashAlgorithm
// TODO 009: Refatorar o método applyMd5() para receber uma string como parâmetro
// TODO 010: Reescrever o passwordWithHash para aplicar um switchMap



class MainViewModel(private val passwordRepository: PasswordRepository) : ViewModel() {

    val _passwordList: LiveData<List<Password>> =
        passwordRepository.passwords
    val passwordList: LiveData<List<Password>>
        get() = _passwordList

    fun save(newPassword: String) {
        viewModelScope.launch {
            passwordRepository.save(newPassword)
        }
    }

}

fun List<Password>.asString(): String {
    return StringBuilder().run {
        this@asString.forEach {
            this.append("id: ${it.id}, password: ${it.password}\n")
        }
        this.toString()
    }
}