package com.example.clx.NavigationBar


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clx.AuthviewModel


@Composable
fun Account(modifier: Modifier, navController: NavController, authviewModel: AuthviewModel) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Position the icon at the top-right
        IconButton(
            onClick = {
                authviewModel.signOut()
            },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Sign out")
        }

        // Centered content
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Account Page", fontSize = 45.sp, color = Color.Gray)
        }
    }
}
