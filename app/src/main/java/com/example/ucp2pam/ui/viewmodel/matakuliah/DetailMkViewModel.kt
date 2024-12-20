package com.example.ucp2pam.ui.viewmodel.matakuliah

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.data.entity.Matakuliah
import com.example.ucp2pam.repository.RepositoryMk
import com.example.ucp2pam.ui.navigasi.DetailMk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailMkViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryMk: RepositoryMk,

    ) : ViewModel() {
    private val kodeMk: String = checkNotNull(savedStateHandle[DetailMk.KodeMk])

    val detailMkUiState: StateFlow<DetailMkUiState> = repositoryMk.getMk(kodeMk)
        .filterNotNull()
        .map {
            DetailMkUiState(
                detailMkUiEvent = it.toDetailMkUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailMkUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailMkUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailMkUiState(
                isLoading = true,
            ),
        )

    fun deleteMk() {
        detailMkUiState.value.detailMkUiEvent.toMatakuliahEntity().let {
            viewModelScope.launch {
                repositoryMk.deleteMk(it)
            }
        }
    }
}

data class DetailMkUiState(
    val detailMkUiEvent: MatakuliahEvent = MatakuliahEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailMkUiEvent == MatakuliahEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailMkUiEvent != MatakuliahEvent()
}

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