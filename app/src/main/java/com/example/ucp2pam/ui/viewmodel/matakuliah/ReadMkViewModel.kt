package com.example.ucp2pam.ui.viewmodel.matakuliah

import com.example.ucp2pam.data.entity.Matakuliah

data class ReadMkUiState (
    val listMk: List<Matakuliah> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)