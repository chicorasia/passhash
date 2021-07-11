package br.com.chicorialabs.passhash.repository

import br.com.chicorialabs.passhash.data.Password
import java.util.*

class PasswordRepository {

    private val listOfPasswordMock = mutableListOf<Password>(
        Password(0, "MockPassword1"),
        Password(1, "MockPassword2"),
        Password(2, "MockPassword3"),
        Password(3, "MockPassword4")
    )


    fun getAllPasswords() = Collections.unmodifiableList(listOfPasswordMock)

    fun save(newPassword: String) {
        listOfPasswordMock.add(
            Password(listOfPasswordMock.size.toLong(), newPassword)
        )
    }

    fun update(password: Password) {
        val idx = listOfPasswordMock.indexOf(password)
        listOfPasswordMock[idx] = password
    }

    fun delete(password: Password) {
        listOfPasswordMock.remove(password)
    }

}