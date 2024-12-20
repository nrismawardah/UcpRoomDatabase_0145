package com.example.ucp2pam.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2pam.ui.view.HomeScreen
import com.example.ucp2pam.ui.view.dosen.InsertDosenView
import com.example.ucp2pam.ui.view.dosen.ReadDosenView
import com.example.ucp2pam.ui.view.matakuliah.DetailMkView
import com.example.ucp2pam.ui.view.matakuliah.InsertMkView
import com.example.ucp2pam.ui.view.matakuliah.ReadMkView
import com.example.ucp2pam.ui.view.matakuliah.UpdateMkView

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
                onBack = {
                    navController.navigate(Home.route)
                },
                onAddMDs = {
                    navController.navigate(InsertDosen.route)
                }
            )
        }
        composable(
            route = ReadMk.route
        ){
            ReadMkView(
                onBack = {
                    navController.navigate(Home.route)
                },
                onAddMk = {
                    navController.navigate(InsertMk.route)
                },
                onDetailClick = { kodeMK ->
                }
            )
        }
        composable(
            route = InsertDosen.route
        ){
            InsertDosenView(
                onBack = {
                    navController.navigate(ReadDosen.route)
                },
                onNavigate = {
                    navController.navigate(ReadDosen.route)
                },
                modifier = modifier
            )
        }
        composable(
            route = InsertMk.route
        ){
            InsertMkView(
                onBack = {
                    navController.navigate(ReadMk.route)
                },
                onNavigate = {
                    navController.navigate(ReadMk.route)
                },
                modifier = modifier
            )
        }
        composable(
            DetailMk.routesWithArg,
            arguments = listOf(
                navArgument(DetailMk.KodeMk){
                    type = NavType.StringType
                }
            )
        ){
            val kodeMk = it.arguments?.getString(DetailMk.KodeMk) ?: ""

            kodeMk?.let { kodeMk ->
                DetailMkView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${UpdateMk.route}/$kodeMk")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            UpdateMk.routesWithArg,
            arguments = listOf(
                navArgument(UpdateMk.KodeMk){
                    type = NavType.StringType
                }
            )
        ){
            UpdateMkView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}