package com.example.clx.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clx.AuthviewModel

@Composable
fun loginPage(modifier: Modifier, navController: NavController, authviewModel: AuthviewModel) {


     Column(
          modifier = Modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Top,
          horizontalAlignment = Alignment.CenterHorizontally
     ) {
          Spacer(modifier = Modifier.height(160.dp))
          Text(
               text = "HLX",
               fontSize = 100.sp,
               style = androidx.compose.ui.text.TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold),
               color = Color.Blue
          )

          Text(
               text = "Chandigarh University",
               style = androidx.compose.ui.text.TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
          )

          Spacer(modifier = Modifier.height(160.dp))

          Button(onClick = {
               navController.navigate("phone")
          }) {
               Text(text = "Continue with Phone", Modifier.width(200.dp))
          }

          Spacer(modifier = Modifier.height(16.dp))

          Button(onClick = {
               navController.navigate("googleLogin")
          }) {
               Text(text = "Continue with Google", Modifier.width(200.dp))
          }

          Spacer(modifier = Modifier.height(30.dp))
          
          Text(text = "OR")

          Spacer(modifier = Modifier.height(30.dp))

          TextButton(onClick = {
               navController.navigate("Signup")
          }) {
               Text(text = "Login With Email")
          }

          Spacer(modifier = Modifier.height(20.dp))

          Text(text =" If you continue, you are accepting\n" +
                  "     HLX Terms and Conditions ")
     }
}

     

