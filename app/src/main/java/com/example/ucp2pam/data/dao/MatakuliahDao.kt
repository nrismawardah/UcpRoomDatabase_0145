package com.example.ucp2pam.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2pam.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

//Tabel matakuliah memiliki operasi read, create, update, delete dan detail

@Dao
interface MatakuliahDao {

    @Query("select * from matakuliah")
    fun getAllMk() : Flow<List<Matakuliah>>

    @Insert
    suspend fun insertMk(matakuliah: Matakuliah)

    @Update
    suspend fun updateMk (matakuliah: Matakuliah)

    @Delete
    suspend fun deleteMk (matakuliah: Matakuliah)

    @Query("select * from matakuliah where kodeMK = :kodeMk")
    fun getMk (kodeMk: String) : Flow<Matakuliah>

}