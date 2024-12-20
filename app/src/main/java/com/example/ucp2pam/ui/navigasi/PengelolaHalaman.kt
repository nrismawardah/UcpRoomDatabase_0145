package com.example.ucp2pam.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2pam.ui.view.HomeScreen
import com.example.ucp2pam.ui.view.dosen.ReadDosenView
import com.example.ucp2pam.ui.view.matakuliah.ReadMkView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = Home.route
    ){
        composable(
            route = Home.route
        ){
            HomeScreen(
                onDosenClick = {
                    navController.navigate(ReadDosen.route)
                },
                onMatakuliahClick = {
                    navController.navigate(ReadMk.route)
                }
            )
        }
        composable(
            route = ReadDosen.route
        ){
            ReadDosenView(
                onAddMDs = {},
                onDetailClick = { nidn ->

                }
            )
        }
        composable(
            route = ReadMk.route
        ){
            ReadMkView(
                onAddMk = {},
                onDetailClick = { kodeMK ->
                }
            )
        }
    }
}