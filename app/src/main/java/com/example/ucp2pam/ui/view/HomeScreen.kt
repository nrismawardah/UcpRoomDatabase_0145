package com.example.ucp2pam.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.ucp2pam.R

@Composable
fun HomeScreen(
    onDosenClick: () -> Unit,
    onMatakuliahClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxSize().weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Selamat Datang",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = Color.White,
                        fontFamily = FontFamily.Serif
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Masukkan data dosen atau matakuliah",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    ,
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onDosenClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF406197),
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_info_details),
                        contentDescription = "Dosen Icon",
                        modifier = Modifier.padding(end = 12.dp),
                        tint = Color.White
                    )
                    Text(
                        text = "Dosen",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Button(
                    onClick = onMatakuliahClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .padding(bottom = 70.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF406197),
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_agenda),
                        contentDescription = "Matakuliah Icon",
                        modifier = Modifier.padding(end = 12.dp),
                        tint = Color.White
                    )
                    Text(
                        text = "Matakuliah",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}