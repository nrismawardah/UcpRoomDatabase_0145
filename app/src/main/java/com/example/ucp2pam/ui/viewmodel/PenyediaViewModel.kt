package com.example.ucp2pam.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.ucp2pam.Ucp2App

fun CreationExtras.ucp2App(): Ucp2App =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Ucp2App)