package com.example.notebook.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notebook.model.note

@Database(entities = [note::class], version = 2)
abstract class AppDatabase : RoomDatabase() {


    abstract fun noteDao(): NoteDao

    companion object{

        @Volatile
        private var Instance : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {

            val MIGRATION_1_2 = object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                }
            }

            val tempInstance = Instance
            if (tempInstance !=  null) return tempInstance

            val instance = Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,"note_table")
                .addMigrations(MIGRATION_1_2).build()

            Instance = instance
            return instance

        }
    }


}