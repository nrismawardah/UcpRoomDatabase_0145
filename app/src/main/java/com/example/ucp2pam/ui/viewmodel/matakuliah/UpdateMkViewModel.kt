package com.example.ucp2pam.ui.viewmodel.matakuliah

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.data.entity.Matakuliah
import com.example.ucp2pam.repository.RepositoryMk
import com.example.ucp2pam.ui.navigasi.DestinasiUpdateMk
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMkViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk
) : ViewModel() {

    var updateMkUIState by mutableStateOf(MkUiState())
        private set

    private val _kodeMk: String = checkNotNull(savedStateHandle[DestinasiUpdateMk.KodeMk])

    init {
        viewModelScope.launch {
            updateMkUIState = repositoryMk.getMk(_kodeMk)
                .filterNotNull()
                .first()
                .toUIStateMk()
        }
    }

    fun updateMkState(matakuliahEvent: MatakuliahEvent) {
        updateMkUIState = updateMkUIState.copy(
            matakuliahEvent = matakuliahEvent
        )
    }

    fun validateFields(): Boolean {
        val event = updateMkUIState.matakuliahEvent
        val errorState = FormErrorStateMk(
            kodeMk = if (event.kodeMK.isNotEmpty()) null else "Kode tidak boleh kosong",
            namaMK = if (event.namaMK.isNotEmpty()) null else "Nama tidak boleh kosong",
            sksMK = if (event.sksMK.isNotEmpty()) null else "SKS tidak boleh kosong",
            semesterMK = if (event.semesterMK.isNotEmpty()) null else "Semester tidak boleh Kosong",
            jenisMK = if (event.jenisMK.isNotEmpty()) null else "Jenis tidak boleh Kosong",
            dosenMK = if (event.dosenMK.isNotEmpty()) null else "Dosen Pengampu tidak boleh kosong",
        )
        updateMkUIState = updateMkUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateDataMk() {
        val currentEvent = updateMkUIState.matakuliahEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMk.updateMk(currentEvent.toMatakuliahEntity())
                    updateMkUIState = updateMkUIState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        matakuliahEvent = MatakuliahEvent(),
                        isEntryValid = FormErrorStateMk()
                    )
                    println(
                        "snackBarMessage diatur: ${
                            updateMkUIState.snackBarMessage
                        }"
                    )
                } catch (e: Exception) {
                    updateMkUIState = updateMkUIState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateMkUIState = updateMkUIState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateMkUIState = updateMkUIState.copy(snackBarMessage = null)
    }
}

fun Matakuliah.toUIStateMk() : MkUiState = MkUiState(
    matakuliahEvent = this.toDetailMkUiEvent()
)