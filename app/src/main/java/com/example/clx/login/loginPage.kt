package com.example.clx.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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
               text = "\uD835\uDDDB\uD835\uDD43ᵡ",
               fontSize = 100.sp,
               style = TextStyle(fontWeight = FontWeight.Bold),
               color = Color.Blue
          )

          Text(
               text = "Chandigarh University",
               style = TextStyle(fontWeight = FontWeight.Bold)
          )

          Spacer(modifier = Modifier.height(120.dp))

          Column(
               verticalArrangement = Arrangement.spacedBy(15.dp), // Adjust spacing here
               horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier.fillMaxWidth()
          ) {
               Button(
                    onClick = {
                         navController.navigate("user")
                    },
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(15.dp),
                    modifier = Modifier.height(50.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                         contentColor = Color.White,
                         containerColor = Color.Blue
                    ),
                    elevation = androidx.compose.material3.ButtonDefaults.buttonElevation(
                         defaultElevation = 10.dp
                    )
               ) {
                    Row(
                         verticalAlignment = Alignment.CenterVertically,
                         modifier = Modifier.width(250.dp)
                    ) {
                         Icon(
                              imageVector = Icons.Default.MailOutline,
                              contentDescription = "Email Icon",
                              tint = Color.White,
                              modifier = Modifier.size(20.dp)
                         )
                         Spacer(modifier = Modifier.width(8.dp))
                         Text(
                              text = "Continue with Email",
                              style = TextStyle(
                                   fontWeight = FontWeight.Bold
                              ),
                              color = Color.White,
                              fontSize = 20.sp
                         )
                    }
               }

               // Spacer removed or managed by `Arrangement.spacedBy`

               Button(
                    onClick = {
                         navController.navigate("phone")
                    },
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(15.dp),
                    modifier = Modifier.height(50.dp),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                         contentColor = Color.White,
                         containerColor = Color.Blue
                    ),
                    elevation = androidx.compose.material3.ButtonDefaults.buttonElevation(
                         defaultElevation = 5.dp
                    )
               ) {
                    Row(
                         verticalAlignment = Alignment.CenterVertically,
                         modifier = Modifier.width(250.dp)
                    ) {
                         Icon(
                              imageVector = Icons.Default.Phone,
                              contentDescription = "Phone Icon",
                              tint = Color.White,
                              modifier = Modifier.size(24.dp)
                         )
                         Spacer(modifier = Modifier.width(8.dp))
                         Text(
                              text = "Continue with Phone",
                              style = TextStyle(
                                   fontWeight = FontWeight.Bold
                              ),
                              color = Color.White,
                              fontSize = 20.sp
                         )
                    }
               }
          }


          Spacer(modifier = Modifier.height(10.dp))
          
          Text(text = "OR")

          Spacer(modifier = Modifier.height(10.dp))

          TextButton(
               onClick = {
                    navController.navigate("Signup")
               }
          ) {
               Text(
                    text = "Create Account",
                    style = TextStyle(
                         textDecoration = TextDecoration.Underline,
                         fontWeight = FontWeight.Bold // Make the text bold
                    ),
                    color = Color.Blue
               )
          }


          Spacer(modifier = Modifier.height(10.dp))

          Text(text =" If you continue, you are accepting\n" +
                  "     HLX Terms and Conditions ")
     }
}

     

