package com.example.ucp2pam.ui.view.matakuliah

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.ui.customwidget.TopAppBar
import com.example.ucp2pam.ui.viewmodel.PenyediaViewModel
import com.example.ucp2pam.ui.viewmodel.dosen.ReadDosenViewModel
import com.example.ucp2pam.ui.viewmodel.matakuliah.FormErrorStateMk
import com.example.ucp2pam.ui.viewmodel.matakuliah.InsertMkViewModel
import com.example.ucp2pam.ui.viewmodel.matakuliah.MatakuliahEvent
import com.example.ucp2pam.ui.viewmodel.matakuliah.MkUiState
import kotlinx.coroutines.launch

@Composable
fun InsertMkView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertMkViewModel = viewModel(factory = PenyediaViewModel.Factory),
    dosenViewModel: ReadDosenViewModel = viewModel()
){
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage){
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ){ padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Matakuliah",
                modifier = Modifier
            )
            InsertBodyMk(
                uiState = uiState,
                onValueChange = { updatedEvent ->
                    viewModel.updateState(updatedEvent)
                },
                onClick = {
                    viewModel.saveDataMk()
                    onNavigate()
                },
                viewModel = dosenViewModel
            )
        }
    }
}

@Composable
fun InsertBodyMk(
    modifier: Modifier = Modifier,
    onValueChange: (MatakuliahEvent) -> Unit,
    uiState: MkUiState,
    onClick: () -> Unit,
    viewModel: ReadDosenViewModel
){
    Column (
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        FormMk(
            matakuliahEvent = uiState.matakuliahEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            viewModel = viewModel,
            modifier = Modifier
                .fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text("Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormMk(
    matakuliahEvent: MatakuliahEvent = MatakuliahEvent(),
    onValueChange: (MatakuliahEvent) -> Unit = {},
    errorState: FormErrorStateMk = FormErrorStateMk(),
    viewModel: ReadDosenViewModel,
    modifier: Modifier = Modifier
){
    val jenisMk = listOf("Pemrograman", "Database", "Jaringan", "UI/UX")
    val semesterMk = listOf("Ganjil", "Genap")
    val dosenUiState by viewModel.readDosenUiState.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    Column (
        modifier = modifier
            .fillMaxWidth()
    ){
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = matakuliahEvent.kodeMK,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(kodeMK = it))
            },
            label = { Text("Kode") },
            isError = errorState.kodeMk !=null,
            placeholder = { Text("Masukkan Kode") },
        )
        Text(
            text = errorState.kodeMk ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = matakuliahEvent.namaMK,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(namaMK = it))
            },
            label = { Text("Nama") },
            isError = errorState.namaMK !=null,
            placeholder = { Text("Masukkan Nama") },
        )
        Text(
            text = errorState.namaMK ?: "",
            color = Color.Red
        )
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = matakuliahEvent.sksMK,
            onValueChange = {
                onValueChange(matakuliahEvent.copy(sksMK = it))
            },
            label = { Text("SKS") },
            isError = errorState.sksMK !=null,
            placeholder = { Text("Masukkan SKS") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = errorState.sksMK ?: "",
            color = Color.Red
        )
        Spacer(
            modifier = Modifier.height(5.dp)
        )
        Text(
            text = "Semester"
        )
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            semesterMk.forEach { smt ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = matakuliahEvent.semesterMK == smt,
                        onClick = {
                            onValueChange(matakuliahEvent.copy(semesterMK = smt))
                        },
                    )
                    Text(
                        text = smt,
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier.height(5.dp)
        )
        Text(
            text = "Jenis Matakuliah"
        )
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            jenisMk.forEach { jmk ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    RadioButton(
                        selected = matakuliahEvent.jenisMK == jmk,
                        onClick = {
                            onValueChange(matakuliahEvent.copy(jenisMK = jmk))
                        },
                    )
                    Text(
                        text = jmk,
                    )
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = matakuliahEvent.dosenMK,
                onValueChange = { },
                label = { Text("Dosen Pengampu") },
                isError = errorState.dosenMK != null,
                placeholder = { Text("Pilih Dosen Pengampu") },
                readOnly = true,

                trailingIcon = {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                dosenUiState.listDosen.forEach { dosen ->
                    DropdownMenuItem(
                        text = { Text(dosen.nama) },
                        onClick = {
                            onValueChange(matakuliahEvent.copy(dosenMK = dosen.nama)
                            )
                            expanded = false
                        }
                    )
                }
            }
        }
        Text(
            text = errorState.dosenMK ?: "",
            color = Color.Red
        )
    }
}
