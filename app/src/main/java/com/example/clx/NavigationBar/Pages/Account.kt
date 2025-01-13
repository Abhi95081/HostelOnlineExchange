package com.example.clx.NavigationBar.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clx.AuthState
import com.example.clx.AuthviewModel
import com.example.clx.R
@Composable

fun Account(
    modifier: Modifier = Modifier,
    navController: NavController,
    authviewModel: AuthviewModel,
) {
    val authState = authviewModel.authState.observeAsState(AuthState.Unauthenticated)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Title
        Text(
            text = "\uD835\uDDDB\uD835\uDD43ᵡ", // The cool "HLX" font
            fontSize = 15.sp,
            color = Color.Blue
        )

        Spacer(modifier = Modifier.height(50.dp))

        // User Image and Details Row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.userimage), // Placeholder image
                contentDescription = "User Details",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "HLX User", // Display user name
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "contactInfo", // Display contact info
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        // Edit Profile Button
        Button(
            onClick = {
                navController.navigate("EditProfile")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = androidx.compose.foundation.shape.RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Blue
            ),
        ) {
            Text(
                text = "Edit Profile",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Authentication state buttons or loading indicator
        when (authState.value) {
            is AuthState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(20.dp),
                    color = Color.Blue
                )
            }

            is AuthState.Authenticated, is AuthState.Unauthenticated -> {
                Button(
                    onClick = {
                        authviewModel.signOut() // Trigger logout
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Blue
                    ),
                ) {
                    Text(text = "Logout")
                }
            }

            is AuthState.Unauthenticated -> {
                // Handle navigation to login if needed
                navController.navigate("login") {
                    popUpTo("account") { inclusive = true } // Clear the back stack
                }
            }

            is AuthState.Error -> {
                Text(
                    text = (authState.value as AuthState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
