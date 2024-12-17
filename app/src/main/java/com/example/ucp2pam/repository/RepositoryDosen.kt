package com.example.ucp2pam.repository

import com.example.ucp2pam.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepositoryDosen {

    fun getAllDosen(): Flow<List<Dosen>>

    suspend fun insertDs(dosen: Dosen)

}