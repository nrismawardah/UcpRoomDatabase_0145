package com.example.ucp2pam.ui.viewmodel.matakuliah

import com.example.ucp2pam.data.entity.Matakuliah

fun Matakuliah.toDetailMkUiEvent() : MatakuliahEvent {
    return MatakuliahEvent(
        kodeMK = kodeMK,
        namaMK = namaMK,
        sksMK = sksMK,
        semesterMK = semesterMK,
        jenisMK = jenisMK,
        dosenMK = dosenMK
    )
}