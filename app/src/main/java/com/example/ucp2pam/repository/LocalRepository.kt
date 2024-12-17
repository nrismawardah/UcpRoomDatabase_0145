package com.example.ucp2pam.repository

import com.example.ucp2pam.data.dao.DosenDao
import com.example.ucp2pam.data.dao.MatakuliahDao
import com.example.ucp2pam.data.entity.Dosen
import com.example.ucp2pam.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

class LocalRepository (
    private val dosenDao: DosenDao,
    private val matakuliahDao: MatakuliahDao
) : RepositoryDosen, RepositoyMk {

    // Implementasi untuk RepositoyDosen

    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }

    override suspend fun insertDs(dosen: Dosen) {
        dosenDao.insertDosen(dosen)
    }

    // Implementasi untuk RepositoyMk

    override fun getAllMk(): Flow<List<Matakuliah>> {
        return matakuliahDao.getAllMk()
    }

    override suspend fun insertMk(matakuliah: Matakuliah) {
        matakuliahDao.insertMatakuliah(matakuliah)
    }

    override suspend fun updateMk(matakuliah: Matakuliah) {
        matakuliahDao.updateMatakuliah(matakuliah)
    }

    override suspend fun deleteMk(matakuliah: Matakuliah) {
        matakuliahDao.deleteMatakuliah(matakuliah)
    }

    override fun getMk(kodeMk: String): Flow<Matakuliah> {
        return matakuliahDao.getMk(kodeMk)
    }
}