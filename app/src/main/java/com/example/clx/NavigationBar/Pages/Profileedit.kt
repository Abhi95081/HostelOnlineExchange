package com.example.clx.NavigationBar.Pages

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clx.AuthviewModel

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.clx.R // Replace with your actual R file import

@Composable
fun Profile(
    modifier: Modifier = Modifier,
    navController: NavController,
    authviewModel: AuthviewModel
) {
    // State variables to hold the user input data
    var name by remember { mutableStateOf("") }
    var contactInfo by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Close button
        TextButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier.size(30.dp),
                tint = Color.Red // Vibrant red color for the close icon
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Header
        Text(
            text = "Basic Information",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF6200EA), // Deep Purple color
            modifier = Modifier.padding(start = 16.dp)
        )

        Divider(
            color = Color(0xFF6200EA),
            thickness = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Profile Image Selector
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // Circular image placeholder or default image
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, Color.Gray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (imageUri == null) {
                    // Default user image
                    Image(
                        painter = painterResource(id = R.drawable.userimage), // Replace with your default image resource
                        contentDescription = "Default User Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .border(2.dp, Color.Gray, CircleShape)
                            .padding(2.dp)
                    )
                } else {
                    // Load the selected image (e.g., from URI)
                    // For demonstration, you can integrate Coil or Glide for image loading.
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { /* Open gallery to select image */ },
                colors = androidx.compose.material.ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF03A9F4), // Light Blue
                    contentColor = Color.White
                )
            ) {
                Text("Select Picture")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Name Input
        Text(
            text = "Name",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Color(0xFF03A9F4), // Light Blue
            modifier = Modifier.padding(start = 16.dp)
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            label = { Text("Enter your name") },
            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFE3F2FD), // Light Blue Background
                focusedIndicatorColor = Color(0xFF03A9F4), // Light Blue Line
                cursorColor = Color(0xFF03A9F4) // Light Blue Cursor
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Contact Information Input
        Text(
            text = "Contact Information",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Color(0xFF03A9F4),
            modifier = Modifier.padding(start = 16.dp)
        )
        TextField(
            value = contactInfo,
            onValueChange = { contactInfo = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            label = { Text("Enter your Mobile Number") },
            colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFE3F2FD),
                focusedIndicatorColor = Color(0xFF03A9F4),
                cursorColor = Color(0xFF03A9F4)
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Save Button
        Button(
            onClick = {
                // Navigate to the Account screen while preserving bottom navigation
                navController.navigate("account")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = androidx.compose.material.ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF6200EA), // Deep Purple
                contentColor = Color.White
            ),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "Save",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
