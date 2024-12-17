package com.example.ucp2pam.repository

import com.example.ucp2pam.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

interface RepositoyMk {

    fun getAllMk() : Flow<List<Matakuliah>>

    suspend fun insertMk(matakuliah: Matakuliah)

    suspend fun updateMk (matakuliah: Matakuliah)

    suspend fun deleteMk (matakuliah: Matakuliah)

    fun getMk (kodeMk: String) : Flow<Matakuliah>

}