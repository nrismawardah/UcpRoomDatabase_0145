package com.example.ucp2pam.ui.viewmodel.dosen

import com.example.ucp2pam.data.entity.Dosen

data class ReadDosenUiState (
    val listDosen: List<Dosen> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)