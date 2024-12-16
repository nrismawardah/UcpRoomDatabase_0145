package com.example.ucp2pam.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2pam.data.dao.DosenDao
import com.example.ucp2pam.data.dao.MatakuliahDao
import com.example.ucp2pam.data.entity.Matakuliah
import com.example.ucp2pam.data.entity.Dosen


@Database(entities = [Dosen::class, Matakuliah::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dosenDao(): DosenDao
    abstract fun matakuliahDao(): MatakuliahDao

    companion object {
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "AppDatabase"
                )
                    .build().also { Instance = it }
            })
        }
    }
}