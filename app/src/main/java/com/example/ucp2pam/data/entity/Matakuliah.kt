package com.example.ucp2pam.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matakuliah")
data class Matakuliah(
    @PrimaryKey
    val kodeMK: String,
    val namaMK: String,
    val sksMK: String,
    val semesterMK: String,
    val jenisMK: String,
    val dosenMK: String
)
