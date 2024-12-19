package com.example.ucp2pam.ui.viewmodel.dosen

import com.example.ucp2pam.data.entity.Dosen

data class DosenUiState(
    val dosenEvent: DosenEvent = DosenEvent(),
    val isEntryValid: FormErrorStateDosen = FormErrorStateDosen(),
    val snackBarMessage: String? = null
)

data class FormErrorStateDosen(
    val nidn: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
){
    fun isValid(): Boolean{
        return nidn == null && nama == null && jenisKelamin == null
    }
}

fun DosenEvent.toDosenEntity(): Dosen = Dosen(
    nidn = nidn,
    nama = nama,
    jenisKelamin = jenisKelamin
)

data class DosenEvent(
    val nidn: String = "",
    val nama: String = "",
    val jenisKelamin: String = ""
)