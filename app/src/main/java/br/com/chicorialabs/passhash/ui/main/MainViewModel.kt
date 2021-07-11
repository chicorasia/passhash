package br.com.chicorialabs.passhash.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.chicorialabs.passhash.data.Password
import br.com.chicorialabs.passhash.repository.PasswordRepository


class MainViewModel(private val passwordRepository: PasswordRepository) : ViewModel() {

    private val _passwordList = MutableLiveData<List<Password>>().apply {
        value = passwordRepository.getAllPasswords()
    }
    val passwordList: LiveData<List<Password>>
        get() = _passwordList

    fun getPasswordsAsList(): String {
        return StringBuilder().run {
            passwordList.value?.forEach {
                this.append("id: ${it.id}, password: ${it.password}\n")
            }
            this.toString()
        }
    }

    fun save(newPassword: String) { passwordRepository.save(newPassword) }


}