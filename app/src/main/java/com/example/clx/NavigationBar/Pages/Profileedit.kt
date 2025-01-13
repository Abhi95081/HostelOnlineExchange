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
        TextButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier.size(30.dp)
            )
        }

        // Spacer for space between the close button and content
        Spacer(modifier = Modifier.height(30.dp))

        // "Basic information" text
        Text(
            text = "Basic information",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(start = 16.dp)
        )

        // Border line below "Basic information"
        Spacer(modifier = Modifier.height(16.dp))

        // Profile image selector
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // Circular image placeholder
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, Color.Gray, CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Button to take a picture from the gallery
            Button(onClick = { /* Open gallery to select image */ }) {
                Text("Select Picture")
            }
        }

        // Spacer for space between sections
        Spacer(modifier = Modifier.height(16.dp))

        // Text field for name or other general information
        Text(
            text = "Name: ",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )
        // TextField for name input
        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Enter your name") }
        )

        Spacer(modifier = Modifier.height(16.dp))


        // Contact information section (mobile number or email)
        Text(
            text = "Contact Information",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )
        // TextField for contact information input (email or mobile)
        TextField(
            value = contactInfo,
            onValueChange = { contactInfo = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Enter your Mobile Number") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Save Button
        Button(
            onClick = {

                navController.navigate("account")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}

