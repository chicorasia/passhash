package br.com.chicorialabs.passhash.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.chicorialabs.passhash.data.Password

@Dao
interface PasswordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(password: Password)

    @Delete
    suspend fun remove(password: Password)

    @Update
    suspend fun update(password: Password)

    @Query("SELECT * FROM table_password")
    fun getAll() : LiveData<List<Password>>

    @Query("SELECT * FROM table_password WHERE id = :key")
    fun get(key: Long) : Password
}