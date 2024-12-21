package com.example.ucp2pam.ui.viewmodel.dosen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.data.entity.Dosen
import com.example.ucp2pam.repository.RepositoryDosen
import kotlinx.coroutines.launch

class InsertDosenViewModel (
    private val repositoryDosen: RepositoryDosen) : ViewModel()
{

    var uiState by mutableStateOf(DosenUiState())

    fun updateState(dosenEvent: DosenEvent){
        uiState = uiState.copy(
            dosenEvent = dosenEvent,
        )
    }

    fun validateFields():Boolean{
        val event = uiState.dosenEvent
        val errorStateDosen = FormErrorStateDosen(
            nidn = if (event.nidn.isNotEmpty()) null else "NIDN tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis kelamin tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorStateDosen)
        return errorStateDosen.isValid()
    }

    fun saveDataDosen(){
        val currentEvent = uiState.dosenEvent
        if (validateFields()) {
            viewModelScope.launch{
                try {
                    repositoryDosen.insertDs(currentEvent.toDosenEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        dosenEvent = DosenEvent(),
                        isEntryValid = FormErrorStateDosen()
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