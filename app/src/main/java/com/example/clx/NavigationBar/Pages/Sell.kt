package com.example.clx.NavigationBar.Pages

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    var userFirstPrice by remember { mutableStateOf<String?>(null) }
    var userSecondPrice by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    // Launcher for selecting an image from the gallery
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> selectedImageUri = uri }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Show selected image or placeholder
        selectedImageUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
        } ?: Text(text = "No image selected", modifier = Modifier.padding(8.dp))

        Button(
            onClick = { imagePickerLauncher.launch("image/*") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.Cyan, shape = RoundedCornerShape(12.dp)),
        ) {
            Text(text = "Pick Image", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Description input
        TextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(8.dp)
                .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(12.dp)),
            placeholder = { Text(text = "Enter description...", color = Color.Gray) },
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to check prices
        Button(
            onClick = {
                if (selectedImageUri != null && description.text.isNotEmpty()) {
                    isLoading = true
                    checkPrice(description.text) { firstPrice, secondPrice ->
                        firstHandPrice = firstPrice
                        secondHandPrice = secondPrice
                        isLoading = false
                    }
                } else {
                    Toast.makeText(navController.context, "Select an image and enter description", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.Cyan, shape = RoundedCornerShape(12.dp)),
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text(text = "Check Price", color = Color.White, fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display predicted prices
        secondHandPrice?.let {
            Text(text = "Second-hand Price: $it", modifier = Modifier.padding(8.dp), fontSize = 16.sp, color = Color.Green)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // User input for prices
        TextField(
            value = userSecondPrice?.let { TextFieldValue(it) } ?: TextFieldValue(""),
            onValueChange = { userSecondPrice = it.text },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(60.dp)
                .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(12.dp)),
            placeholder = { Text(text = "Enter Second-hand price...", color = Color.Gray) },
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 14.sp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to push to home with entered data
        Button(
            onClick = {
                if (selectedImageUri != null && description.text.isNotEmpty() && userFirstPrice != null && userSecondPrice != null) {
                    navController.navigate("home/${selectedImageUri.toString()}/${description.text}/$userFirstPrice/$userSecondPrice")
                } else {
                    Toast.makeText(navController.context, "Complete all fields before proceeding.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.Cyan, shape = RoundedCornerShape(12.dp)),
        ) {
            Text(text = "Push to Home", color = Color.White, fontSize = 16.sp)
        }
    }
}

// Simulated AI call to check prices
@OptIn(DelicateCoroutinesApi::class)
fun checkPrice(description: String, onResult: (String, String) -> Unit) {
    kotlinx.coroutines.GlobalScope.launch {
        kotlinx.coroutines.delay(2000)
        onResult("₹${(100..1000).random()}", "₹${(50..500).random()}")
    }
}
