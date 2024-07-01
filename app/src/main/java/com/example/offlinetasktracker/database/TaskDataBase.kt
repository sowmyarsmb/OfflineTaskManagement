package com.example.offlinetasktracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.offlinetasktracker.model.Task
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(entities = [Task::class], version = 2, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context,passphrase: CharArray): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val passphraseByteArray: ByteArray = SQLiteDatabase.getBytes(passphrase)
                val factory = SupportFactory(passphraseByteArray)
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                )    .openHelperFactory(factory)
                    .fallbackToDestructiveMigration() // Allow destructive migrations
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}


