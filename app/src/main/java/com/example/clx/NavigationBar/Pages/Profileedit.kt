package com.example.clx.NavigationBar.Pages

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
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
import coil.compose.rememberAsyncImagePainter
import com.example.clx.AuthviewModel
import com.example.clx.R // Replace with your actual R file import

@Composable
fun Profile(
    modifier: Modifier = Modifier,
    navController: NavController,
    authviewModel: AuthviewModel
) {
    var name by remember { mutableStateOf("") }
    var contactInfo by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> imageUri = uri }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Close button
        TextButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier.size(30.dp),
                tint = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Title Section
        Text(
            text = "Edit Profile",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color(0xFF6200EA),
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

        // Profile Image Section
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .border(3.dp, Color.Gray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (imageUri == null) {
                    Image(
                        painter = rememberAsyncImagePainter(R.drawable.userimage), // Default image
                        contentDescription = "Default User Image",
                        modifier = Modifier.size(120.dp),
                    )
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Selected User Image",
                        modifier = Modifier.size(120.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                onClick = { galleryLauncher.launch("image/*") },
                modifier = Modifier.align(Alignment.CenterVertically),
                colors = androidx.compose.material.ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF03A9F4),
                    contentColor = Color.White
                )
            ) {
                Text("Select Picture")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Name Field
        Text(
            text = "Name",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Color(0xFF03A9F4),
            modifier = Modifier.padding(start = 16.dp)
        )
        BasicTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(1.dp, Color(0xFF03A9F4), CircleShape),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    if (name.isEmpty()) {
                        Text("Enter your name", color = Color.Gray, fontSize = 14.sp)
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Contact Information Field
        Text(
            text = "Contact Information",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Color(0xFF03A9F4),
            modifier = Modifier.padding(start = 16.dp)
        )
        BasicTextField(
            value = contactInfo,
            onValueChange = { contactInfo = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(1.dp, Color(0xFF03A9F4), CircleShape),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    if (contactInfo.isEmpty()) {
                        Text("Enter your mobile number", color = Color.Gray, fontSize = 14.sp)
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Save Button
        Button(
            onClick = {
                navController.navigate("account/${Uri.encode(name)}/${Uri.encode(contactInfo)}/${Uri.encode(imageUri.toString())}")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = androidx.compose.material.ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF6200EA),
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
