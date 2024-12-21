package com.example.ucp2pam.ui.customwidget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TopAppBar(
    onBack: () -> Unit,
    onNotificationClick: () -> Unit,
    showBackButton: Boolean = true,
    judul: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF102751))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showBackButton) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Kembali",
                            tint = Color.White
                        )
                    }
                    Text(
                        text = "Kembali",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }
            IconButton(onClick = onNotificationClick) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notifikasi",
                    tint = Color.White
                )
            }
        }
        Text(
            text = judul,
            fontSize = 22.sp,
            fontFamily = FontFamily.Serif,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}
