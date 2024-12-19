package com.example.ucp2pam.ui.viewmodel.dosen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.data.entity.Dosen
import com.example.ucp2pam.repository.RepositoryDosen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ReadDosenView(
    private val repositoryDosen: RepositoryDosen
) : ViewModel() {

    val readDosenUiState: StateFlow<ReadDosenUiState> = repositoryDosen.getAllDosen()
        .filterNotNull()
        .map {
            ReadDosenUiState(
                listDosen = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(ReadDosenUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                ReadDosenUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ReadDosenUiState(
                isLoading = true
            )
        )
}

data class ReadDosenUiState (
    val listDosen: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)