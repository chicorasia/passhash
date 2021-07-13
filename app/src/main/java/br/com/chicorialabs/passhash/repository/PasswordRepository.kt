package br.com.chicorialabs.passhash.repository

import androidx.lifecycle.LiveData
import br.com.chicorialabs.passhash.dao.PasswordDao
import br.com.chicorialabs.passhash.data.Password

class PasswordRepository(private val passwordDao: PasswordDao) {

    val passwords: LiveData<List<Password>>
        get() = passwordDao.getAll()

    suspend fun save(newPassword: String) {
        passwordDao.save(Password(password = newPassword))
    }

    suspend fun update(password: Password) {
        passwordDao.update(password)
    }

    suspend fun delete(password: Password) {
        passwordDao.remove(password)
    }

    fun get(password: Password) : Password =
        passwordDao.get(password.id)

}

