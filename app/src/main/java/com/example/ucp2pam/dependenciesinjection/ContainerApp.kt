package com.example.ucp2pam.dependenciesinjection

import android.content.Context
import com.example.ucp2pam.data.database.AppDatabase
import com.example.ucp2pam.repository.LocalRepositoryDosen
import com.example.ucp2pam.repository.LocalRepositoryMk
import com.example.ucp2pam.repository.RepositoryDosen
import com.example.ucp2pam.repository.RepositoyMk

interface InterfaceContainerApp {
    val repositoryDosen: RepositoryDosen
    val repositoyMk: RepositoyMk
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {

    override val repositoryDosen: RepositoryDosen by lazy {
        LocalRepositoryDosen(AppDatabase.getDatabase(context).dosenDao())
    }

    override val repositoyMk: RepositoyMk by lazy {
        LocalRepositoryMk(AppDatabase.getDatabase(context).matakuliahDao())
    }
}
