package com.example.clx.NavigationBar.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.clx.AuthviewModel

@Composable
fun Homepage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authviewModel: AuthviewModel
) {
    // Retrieve arguments from NavController
    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
    val imageUri = savedStateHandle?.get<String>("imageUri")
    val description = savedStateHandle?.get<String>("description")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the image if available
        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
        } ?: Text(text = "No image available", modifier = Modifier.padding(8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        // Display the description
        Text(
            text = description ?: "No description provided",
            modifier = Modifier.padding(8.dp)
        )
    }
}

