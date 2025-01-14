package com.example.clx.NavigationBar.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.clx.AuthState
import com.example.clx.AuthviewModel
import com.example.clx.R

@Composable
fun Account(
    modifier: Modifier = Modifier,
    navController: NavController,
    authviewModel: AuthviewModel,
    name: String = "HLX User", // Default name
    mobile: String = "Not Provided", // Default mobile
    imageUri: String? = null
) {
    val authState = authviewModel.authState.observeAsState(AuthState.Unauthenticated)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Title
            Text(
                text = "Account Details",
                fontSize = 28.sp,
                color = Color(0xFF6200EA),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Profile Picture
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(3.dp, Color.Gray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (imageUri == null) {
                    Image(
                        painter = painterResource(id = R.drawable.userimage),
                        contentDescription = "Default User Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(CircleShape)
                    )
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "User Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // User Details
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = name,
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = mobile,
                    fontSize = 18.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Edit Profile Button
            Button(
                onClick = { navController.navigate("EditProfile") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EA),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Edit Profile",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Logout Button
            Button(
                onClick = { authviewModel.signOut() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Logout",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Loading State Overlay
        if (authState.value is AuthState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        }
    }
}
