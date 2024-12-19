package com.example.ucp2pam.ui.viewmodel.matakuliah

import com.example.ucp2pam.data.entity.Matakuliah

fun MatakuliahEvent.toMatakuliahEntity(): Matakuliah = Matakuliah(
    kodeMK = kodeMK,
    namaMK = namaMK,
    sksMK = sksMK,
    semesterMK = semesterMK,
    jenisMK = jenisMK,
    dosenMK = dosenMK
)

data class MatakuliahEvent(
    val kodeMK: String = "",
    val namaMK: String = "",
    val sksMK: String = "",
    val semesterMK: String = "",
    val jenisMK: String = "",
    val dosenMK: String = ""
)