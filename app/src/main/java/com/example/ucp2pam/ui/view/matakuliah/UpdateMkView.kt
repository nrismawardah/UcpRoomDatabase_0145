package com.example.ucp2pam.ui.view.matakuliah

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2pam.ui.customwidget.TopAppBar
import com.example.ucp2pam.ui.viewmodel.PenyediaViewModel
import com.example.ucp2pam.ui.viewmodel.dosen.ReadDosenViewModel
import com.example.ucp2pam.ui.viewmodel.matakuliah.UpdateMkViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UpdateMkView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateMkViewModel = viewModel(factory = PenyediaViewModel.Factory),
    dosenViewModel: ReadDosenViewModel = viewModel()

){
    val uiState = viewModel.updateMkUIState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        println("LaunchedEffect triggered")
        uiState.snackBarMessage?.let { message ->
            println("Launching coroutine: $message")
            coroutineScope.launch {
                println("Launching coroutine for snackbar")
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long
                )
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold (
        modifier=modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                judul = "Edit Matakuliah",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        }
    ){ padding ->
        Column (
            modifier= Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(5.dp)
        ){
            InsertBodyMk(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateMkState(updateEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()){
                            viewModel.updateDataMk()
                            delay(500)
                            withContext(Dispatchers.Main){
                                onNavigate()
                            }
                        }
                    }
                },
                viewModel = dosenViewModel
            )
        }
    }
}