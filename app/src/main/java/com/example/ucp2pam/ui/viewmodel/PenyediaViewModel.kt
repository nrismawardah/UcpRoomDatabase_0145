package com.example.ucp2pam.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2pam.Ucp2App
import com.example.ucp2pam.ui.viewmodel.dosen.InsertDosenViewModel
import com.example.ucp2pam.ui.viewmodel.dosen.ReadDosenViewModel
import com.example.ucp2pam.ui.viewmodel.matakuliah.DetailMkViewModel
import com.example.ucp2pam.ui.viewmodel.matakuliah.InsertMkViewModel
import com.example.ucp2pam.ui.viewmodel.matakuliah.ReadMkViewModel
import com.example.ucp2pam.ui.viewmodel.matakuliah.UpdateMkViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {

        // Untuk dosen
        initializer {
            ReadDosenViewModel(
                ucp2App().containerApp.repositoryDosen
            )
        }
        initializer {
            InsertDosenViewModel(
                ucp2App().containerApp.repositoryDosen
            )
        }

        // Untuk matakuliah
        initializer {
            ReadMkViewModel(
                ucp2App().containerApp.repositoryMk
            )
        }
        initializer {
            InsertMkViewModel(
                ucp2App().containerApp.repositoryMk
            )
        }
        initializer {
            DetailMkViewModel(
                ucp2App().containerApp.repositoryMk
            )
        }
        initializer {
            UpdateMkViewModel(
                ucp2App().containerApp.repositoryMk
            )
        }
    }
}

fun CreationExtras.ucp2App(): Ucp2App =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Ucp2App)