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
import androidx.compose.material3.DropdownMenu
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
import com.example.ucp2pam.Ucp2App
import com.example.ucp2pam.ui.customwidget.TopAppBar
import com.example.ucp2pam.ui.navigasi.AlamatNavigasi
import com.example.ucp2pam.ui.viewmodel.PenyediaViewModel
import com.example.ucp2pam.ui.viewmodel.dosen.ReadDosenViewModel
import com.example.ucp2pam.ui.viewmodel.matakuliah.FormErrorStateMk
import com.example.ucp2pam.ui.viewmodel.matakuliah.InsertMkViewModel
import com.example.ucp2pam.ui.viewmodel.matakuliah.MatakuliahEvent
import com.example.ucp2pam.ui.viewmodel.matakuliah.MkUiState
import kotlinx.coroutines.launch

object InsertMk : AlamatNavigasi{
    override val route: String = "insert_mk"
}

@Composable
fun InsertMkView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertMkViewModel = viewModel(factory = PenyediaViewModel.Factory)
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
                .padding(5.dp)
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
    ReadDosenViewModel: ReadDosenViewModel = viewModel(factory = PenyediaViewModel.Factory),
    modifier: Modifier = Modifier
){
    val jenisMk = listOf("Wajib", "Pilihan")
    val semesterMk = listOf("Ganjil", "Genap")
    val dosenUiState by ReadDosenViewModel.readDosenUiState.collectAsState()
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
            placeholder = { Text("Masukkan Kode Matakuliah") },
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
            placeholder = { Text("Masukkan Nama Matakuliah") },
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
            placeholder = { Text("Masukkan SKS Matakuliah") },
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
        DropDownDosen(
            judul = "Pilih Dosen Pengampu",
            options = dosenUiState.listDosen.map { it.nama },
            selectedOption = matakuliahEvent.dosenMK,
            onOptionSelected = {
                selectedDosen -> onValueChange(matakuliahEvent.copy(dosenMK = selectedDosen))
            },
            isError = errorState.dosenMK != null,
            errorMessage = errorState.dosenMK
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownDosen(
    judul: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
){
    var expanded by remember { mutableStateOf(false) }
    var currentSelection by remember { mutableStateOf(selectedOption) }

    Column {
        OutlinedTextField(
            value = currentSelection,
            onValueChange = {},
            readOnly = true,
            label = { Text(judul) },
            trailingIcon = {
                androidx.compose.material3.IconButton(onClick = {expanded = !expanded}) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            isError = isError
        )
        DropdownMenu (
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ){
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        currentSelection = option
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
        if (isError && errorMessage != null){
            Text(
                text = errorMessage,
                color = Color.Red
            )
        }
    }
}