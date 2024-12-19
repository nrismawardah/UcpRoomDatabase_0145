package com.example.ucp2pam.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.data.entity.Matakuliah
import com.example.ucp2pam.repository.RepositoryMk
import kotlinx.coroutines.launch

class InsertMkViewModel (private val repositoryMk: RepositoryMk) : ViewModel() {

    var uiState by mutableStateOf(MkUiState())

    fun updateState(matakuliahEvent: MatakuliahEvent){
        uiState = uiState.copy(
            matakuliahEvent = matakuliahEvent,
        )
    }

    fun validateFields():Boolean {
        val event = uiState.matakuliahEvent
        val errorState = FormErrorStateMk(
            kodeMk = if (event.kodeMK.isNotEmpty()) null else "Kode tidak boleh kosong",
            namaMK = if (event.namaMK.isNotEmpty()) null else "Nama tidak boleh kosong",
            sksMK = if (event.sksMK.isNotEmpty()) null else "SKS tidak boleh kosong",
            semesterMK = if (event.semesterMK.isNotEmpty()) null else "Semester tidak boleh kosong",
            jenisMK = if (event.jenisMK.isNotEmpty()) null else "Jenis tidak boleh kosong",
            dosenMK = if (event.dosenMK.isNotEmpty()) null else "Dosen pengampu tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveDataMk(){
        val currentEvent = uiState.matakuliahEvent
        if (validateFields()) {
            viewModelScope.launch{
                try {
                    repositoryMk.insertMk(currentEvent.toMatakuliahEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        matakuliahEvent = MatakuliahEvent(),
                        isEntryValid = FormErrorStateMk()
                    )
                } catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid, periksa kembali data Anda"
            )
        }
    }
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}

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