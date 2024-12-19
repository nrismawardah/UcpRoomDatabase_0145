package com.example.ucp2pam.ui.viewmodel.matakuliah

import com.example.ucp2pam.data.entity.Matakuliah

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