package com.example.ucp2pam.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2pam.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

//Tabel dosen memiliki operasi read dan create

@Dao
interface DosenDao {

    @Query("select * from dosen")
    fun getAllDosen() : Flow<List<Dosen>>

    @Insert
    suspend fun insertDosen(
        dosen: Dosen
    )

}