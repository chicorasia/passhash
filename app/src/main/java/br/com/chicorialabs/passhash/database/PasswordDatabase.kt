package br.com.chicorialabs.passhash.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.chicorialabs.passhash.dao.PasswordDao
import br.com.chicorialabs.passhash.data.Password


@Database(
    entities = [Password::class],
    version = 1,
    exportSchema = false
)
abstract class PasswordDatabase : RoomDatabase() {
    abstract val passwordDao: PasswordDao

    companion object {

        @Volatile
        private var INSTANCE: PasswordDatabase? = null

        fun getInstance(context: Context): PasswordDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PasswordDatabase::class.java,
                        "passhash_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}