package com.example.clx.NavigationBar.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.clx.AuthviewModel
import com.example.clx.R

@Composable
fun Account(
    modifier: Modifier = Modifier,
    navController: NavController,
    authviewModel: AuthviewModel,
) {

    var name by remember { mutableStateOf("HLX User") }

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
                text = name ,
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(50.dp))


            Button(
                onClick = {
                    navController.navigate("EditProfile")
                },
                modifier = Modifier
                    .width(400.dp)
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



        Button(
            onClick = {
                // Handle logout action here
                authviewModel.signOut() // Assuming a logout method exists in the AuthviewModel
                navController.navigate("login") // Navigate to the login screen after logout
            },
            modifier = Modifier
                .width(300.dp)
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
}
