package com.example.ucp2pam.ui.view.matakuliah

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.data.entity.Matakuliah
import com.example.ucp2pam.ui.customwidget.TopAppBar
import com.example.ucp2pam.ui.navigasi.AlamatNavigasi
import com.example.ucp2pam.ui.viewmodel.PenyediaViewModel
import com.example.ucp2pam.ui.viewmodel.matakuliah.DetailMkUiState
import com.example.ucp2pam.ui.viewmodel.matakuliah.DetailMkViewModel
import com.example.ucp2pam.ui.viewmodel.matakuliah.toMatakuliahEntity

object DetailMk : AlamatNavigasi {
    override val route: String = "detail_mk"
}

@Composable
fun DetailMkView (
    modifier: Modifier = Modifier,
    viewModel: DetailMkViewModel = viewModel (factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = { },
    onEditClick: (String) -> Unit = { },
    onDeleteClick: () -> Unit = { }
){
    Scaffold (
        modifier= Modifier
            .fillMaxSize()
            .padding(15.dp),
            //.padding(top = 18.dp),
        topBar = {
            TopAppBar (
                judul = "Detail Matakuliah",
                showBackButton = true,
                onNotificationClick = {},
                onBack = onBack,
                modifier = modifier
                    .offset(y = (-10).dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEditClick(viewModel.detailMkUiState.value.detailMkUiEvent.kodeMK)
                },
                shape = MaterialTheme.shapes.medium,
                containerColor = Color(0xFF102751),
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Matakuliah",
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->
        val detailUiState by viewModel.detailMkUiState.collectAsState()

        BodyDetailMk(
            modifier = Modifier
                .padding(innerPadding)
                .offset(y = (-50).dp),
            detailUiState = detailUiState,
            onDeleteClick = {
                viewModel.deleteMk()
                onDeleteClick()
            }
        )
    }
}

@Composable
fun BodyDetailMk (
    modifier: Modifier = Modifier,
    detailUiState: DetailMkUiState = DetailMkUiState(),
    onDeleteClick: () -> Unit = { }
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    when {
        detailUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        detailUiState.isUiEventNotEmpty -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ItemDetailMk(
                    matakuliah = detailUiState.detailMkUiEvent.toMatakuliahEntity(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        deleteConfirmationRequired = true
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF102751),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Delete")
                }
                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        detailUiState.isUiEventEmpty -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun ItemDetailMk (
    modifier: Modifier = Modifier,
    matakuliah: Matakuliah
){
    Card (
        modifier = modifier
            .fillMaxWidth(),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color(0xFFDBE8FC)
        )
    ){
        Column (
            modifier = Modifier
                .padding(16.dp)
        ){
            ComponentDetailMk (judul = "Kode Matakuliah", isinya = matakuliah. kodeMK)
            Spacer (modifier = Modifier.padding(4.dp))
            ComponentDetailMk (judul = "Nama Matakuliah", isinya = matakuliah. namaMK)
            Spacer (modifier = Modifier.padding(4.dp))
            ComponentDetailMk (judul = "SKS Matakuliah", isinya = matakuliah.sksMK)
            Spacer (modifier = Modifier.padding(4.dp))
            ComponentDetailMk (judul = "Semester Matakuliah", isinya = matakuliah. semesterMK)
            Spacer (modifier = Modifier.padding(4.dp))
            ComponentDetailMk (judul = "Jenis Matakuliah", isinya = matakuliah.jenisMK)
            Spacer (modifier = Modifier.padding(4.dp))
            ComponentDetailMk (judul = "Dosen Pengampu", isinya = matakuliah.dosenMK)
        }
    }
}

@Composable
fun ComponentDetailMk (
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray

        )
        Text(
            text = isinya, fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* Do nothing */ },
        title = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Delete Data")
            }
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Apakah anda yakin ingin menghapus data?")
            }
        },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel", color = Color.Black)
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes", color = Color.Black)
            }
        }
    )
}
