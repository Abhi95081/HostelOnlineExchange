package com.example.clx.NavigationBar.Pages

import android.graphics.Paint.Style
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clx.AuthviewModel
import com.example.clx.R

@Composable
fun Account(modifier: Modifier = Modifier, navController: NavController, authviewModel: AuthviewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "\uD835\uDDDB\uD835\uDD43ᵡ", fontSize = 15.sp, color = Color.Blue)

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.userimage),
                contentDescription = "User Details",
                modifier = Modifier
                    .size(100.dp) // Adjust the size as needed
                    .clip(CircleShape) // Makes the image circular
                    .border(2.dp, Color.Gray, CircleShape) // Optional: Add a border
            )

            Spacer(modifier = Modifier.width(16.dp)) // Add space between the image and text

            Text(
                text = "HLX User",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier.padding(20.dp)
                .width(300.dp) // Set the width of the rectangle
                .height(50.dp)
                .background(Color.Black)// Set the height of the rectangle
                .border(2.dp, Color.Gray) // Add a border to make the rectangle visible
                .padding(8.dp)
            , // Optional padding inside the rectangle
            contentAlignment = Alignment.Center // Center the button inside the rectangle
        ) {
            TextButton(
                onClick = { /*TODO: Handle Edit Profile action*/ }
            ) {
                Text(
                    text = "Edit Profile",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            }
        }



        Button(
            onClick = {
                // Handle logout action here
                authviewModel.signOut() // Assuming a logout method exists in the AuthviewModel
                navController.navigate("login") // Navigate to the login screen after logout
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Logout")
        }
    }
}
