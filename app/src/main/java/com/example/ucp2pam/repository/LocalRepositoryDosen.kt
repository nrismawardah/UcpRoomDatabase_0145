package com.example.ucp2pam.repository

import com.example.ucp2pam.data.dao.DosenDao
import com.example.ucp2pam.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDosen (
    private val dosenDao: DosenDao
) : RepositoryDosen {

    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }

    override suspend fun insertDs(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }

}