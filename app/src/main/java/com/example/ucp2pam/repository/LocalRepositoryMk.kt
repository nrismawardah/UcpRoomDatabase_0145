package com.example.ucp2pam.repository

import com.example.ucp2pam.data.dao.MatakuliahDao
import com.example.ucp2pam.data.entity.Matakuliah
import kotlinx.coroutines.flow.Flow

class LocalRepositoryMk (
    private val matakuliahDao: MatakuliahDao
) : RepositoryMk {

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