package com.example.ucp2pam.ui.viewmodel.matakuliah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2pam.data.entity.Matakuliah
import com.example.ucp2pam.repository.RepositoyMk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow


data class ReadMkUiState (
    val listMk: List<Matakuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)