package com.example.ucp2pam.ui.viewmodel.matakuliah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.data.entity.Matakuliah
import com.example.ucp2pam.repository.RepositoryMk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ReadMkViewModel(
    private val repositoryMk: RepositoryMk
) : ViewModel() {

    val readMkUiState: StateFlow<ReadMkUiState> = repositoryMk.getAllMk()
        .filterNotNull()
        .map {
            ReadMkUiState(
                listMk = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(ReadMkUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                ReadMkUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ReadMkUiState(
                isLoading = true,
            )
        )
}

data class ReadMkUiState (
    val listMk: List<Matakuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)