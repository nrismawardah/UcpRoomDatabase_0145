package com.example.ucp2pam.ui.viewmodel.matakuliah

import com.example.ucp2pam.data.entity.Matakuliah

fun Matakuliah.toUIStateMk() : MkUiState = MkUiState(
    matakuliahEvent = this.toDetailMkUiEvent()
)