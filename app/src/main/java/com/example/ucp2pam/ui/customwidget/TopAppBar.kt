package com.example.ucp2pam.ui.customwidget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopAppBar(
    onBack: () -> Unit,
    showBackButton: Boolean = true,
    judul: String,
    modifier: Modifier = Modifier
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
    ){
        Row {
            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                if (showBackButton) {
                    Row (
                        modifier = Modifier.fillMaxSize(),
                    ){
                        TextButton(
                            onClick = onBack,
                            modifier = Modifier
                        ) {
                            Text(
                                "Back"
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                Icon(
                    Icons.Filled.Notifications,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Text(
            text = judul,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}