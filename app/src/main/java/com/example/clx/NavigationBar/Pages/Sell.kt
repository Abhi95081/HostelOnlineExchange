package com.example.clx.NavigationBar.Pages

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.clx.AuthviewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch

@Composable
fun Sell(
    modifier: Modifier = Modifier,
    navController: NavController,
    authviewModel: AuthviewModel,
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var firstHandPrice by remember { mutableStateOf<String?>(null) }
    var secondHandPrice by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    // Launcher for selecting an image from the gallery
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Show selected image
        selectedImageUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
        } ?: Text(text = "No image selected", modifier = Modifier.padding(8.dp))

        // Button to open gallery
        Button(
            onClick = { imagePickerLauncher.launch("image/*") },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Pick Image from Gallery")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Description input
        BasicTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(100.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                ) {
                    if (description.text.isEmpty()) {
                        Text(text = "Enter description...")
                    }
                    innerTextField()
                }
            }
        )

        ////Spacer(modifier = Modifier.height(16.dp))

        // Button to check prices
        Button(
            onClick = {
                if (selectedImageUri != null && description.text.isNotEmpty()) {
                    isLoading = true
                    // Simulate an AI call
                    checkPrice(description.text) { firstPrice, secondPrice ->
                        firstHandPrice = firstPrice
                        secondHandPrice = secondPrice
                        isLoading = false
                    }
                } else {
                    Toast.makeText(
                        navController.context,
                        "Please select an image and enter a description.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = if (isLoading) "Checking Price..." else "Check Price")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display prices
        firstHandPrice?.let { Text(text = "First-hand Price: $it", modifier = Modifier.padding(4.dp)) }
        secondHandPrice?.let { Text(text = "Second-hand Price: $it", modifier = Modifier.padding(4.dp)) }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to push to home
        Button(
            onClick = {
                if (selectedImageUri != null && description.text.isNotEmpty()) {
                    navController.navigate("home/${selectedImageUri.toString()}/${description.text}")
                } else {
                    Toast.makeText(
                        navController.context,
                        "Please select an image and enter a description.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Push to Home")
        }
    }
}

// Simulated AI call to check prices
@OptIn(DelicateCoroutinesApi::class)
fun checkPrice(description: String, onResult: (String, String) -> Unit) {
    // Simulate network delay
    kotlinx.coroutines.GlobalScope.launch {
        kotlinx.coroutines.delay(2000)
        onResult("₹${(100..1000).random()}", "₹${(50..500).random()}")
    }
}

