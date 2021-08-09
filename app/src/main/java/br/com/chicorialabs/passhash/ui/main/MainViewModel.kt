package br.com.chicorialabs.passhash.ui.main

import androidx.lifecycle.*
import br.com.chicorialabs.passhash.data.Password
import br.com.chicorialabs.passhash.extension.applyHash
import br.com.chicorialabs.passhash.repository.PasswordRepository
import kotlinx.coroutines.launch


class MainViewModel(private val passwordRepository: PasswordRepository) : ViewModel() {

    val _passwordList: LiveData<List<Password>> =
        passwordRepository.passwords
    val passwordList: LiveData<List<Password>>
        get() = _passwordList

    val _hashAlgorithm = MutableLiveData<String>("MD5")
    val hashAlgorithm: String
        get() = _hashAlgorithm.value ?: "MD5"

//    TODO 003: Comentar ou remover o bloco init { }
    init {
        addTestData()
    }

    val passwordDtoList: LiveData<List<PasswordDto>> =
        Transformations.switchMap(_hashAlgorithm) {
            passwordList.map {
                it.map { password ->
                    PasswordDto(
                        id = password.id,
                        password = password.password,
                        hash = password.applyHash(hashAlgorithm)
                    )
                }
            }
        }

    fun setMd5() {
        _hashAlgorithm.value = "MD5"
    }

    fun setSha1() {
        _hashAlgorithm.value = "SHA-1"
    }

    fun setSha256() {
        _hashAlgorithm.value = "SHA-256"
    }


    fun save(newPassword: String) {
        viewModelScope.launch {
            passwordRepository.save(newPassword)
        }
    }

    fun update(id: Long, password: String) {
        viewModelScope.launch {
            passwordRepository.update(Password(id = id, password = password))
        }
    }

    data class PasswordDto(
        val id: Long,
        val password: String,
        val hash: String
    )

//    TODO 001: Limpar o appdata e rodar para adicionar 200 senhas ao armazenamento
//    TODO 002: Comentar ou remover o addTestData()
    fun addTestData() {
        viewModelScope.launch {
            if (passwordList.value.isNullOrEmpty()) {
                for (i in 1..200) {
                    passwordRepository.save("MockPassword$i")
                }
            }
        }
    }
}

