package com.example.clx.NavigationBar.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header with a title
        Text(
            text = "Welcome to Your Homepage",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Image section with rounded corners
        imageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Image",
                modifier = Modifier
                    .size(250.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
        } ?: Text(
            text = "No image available",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Description section with a card and shadow
        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = description ?: "No description provided",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
