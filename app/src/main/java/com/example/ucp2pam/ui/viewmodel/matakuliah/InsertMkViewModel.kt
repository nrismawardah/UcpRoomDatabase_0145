package com.example.ucp2pam.ui.viewmodel.matakuliah

import com.example.ucp2pam.data.entity.Matakuliah

data class MkUiState(
    val matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    val isEntryValid: FormErrorStateMk = FormErrorStateMk(),
    val snackBarMessage: String? = null
)

data class FormErrorStateMk(
    val kodeMk: String? = null,
    val namaMK: String? = null,
    val sksMK: String? = null,
    val semesterMK: String? = null,
    val jenisMK: String? = null,
    val dosenMK: String? = null
){
    fun isValid(): Boolean{
        return kodeMk == null && namaMK == null && sksMK == null &&
                semesterMK == null && jenisMK == null && dosenMK == null
    }
}

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