package com.example.clx.login

import android.util.Log
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clx.AuthState
import com.example.clx.AuthviewModel
import com.example.clx.R
import com.example.clx.rememberFirebaseAuthLauncher.rememberFirebaseAuthLauncher
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object AuthState {
     var Loading: Boolean by mutableStateOf(false)
}

@Composable
fun loginPage(modifier: Modifier, navController: NavController, authviewModel: AuthviewModel) {

     var loading by remember { mutableStateOf(false) }
     val user by remember { mutableStateOf(Firebase.auth.currentUser) }
     val context = LocalContext.current
     val authState by authviewModel.authState.observeAsState()

     // AuthLauncher
     val launcher = rememberFirebaseAuthLauncher(
          onAuthComplete = { result ->
               authviewModel.checkAuthState() // Trigger ViewModel to update state
               loading = false
          },
          onAuthError = { exception ->
               Log.e("GoogleAuth", "Sign-In failed: ${exception.message}", exception)
               loading = false
          }
     )

     val token = stringResource(id = R.string.clint_id)

     // Observe authentication state and navigate
     LaunchedEffect(authState) {
          when (authState) {
               is AuthState.Authenticated -> {
                    navController.navigate("home") {
                         popUpTo("signup") { inclusive = true }
                    }
               }
               else -> {} // Do nothing for other states
          }
     }

     Column(
          modifier = modifier.fillMaxSize(),
          verticalArrangement = Arrangement.Top,
          horizontalAlignment = Alignment.CenterHorizontally
     ) {
          Spacer(modifier = Modifier.height(100.dp))  // Decreased height here

          Text(
               text = "\uD835\uDDDB\uD835\uDD43ᵡ", // Logo or app name
               fontSize = 100.sp,
               style = TextStyle(fontWeight = FontWeight.Bold),
               color = Color.Blue
          )

          Spacer(modifier = Modifier.height(20.dp))  // Reduced space between logo and text

          Text(
               text = "Chandigarh University",
               style = TextStyle(fontWeight = FontWeight.Bold)
          )

          Spacer(modifier = Modifier.height(60.dp))  // Reduced space between text and email button

          Column(
               modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
          ) {
               if (loading) {
                    CircularProgressIndicator()
               } else {
                    // Continue with Email Button
                    Button(
                         onClick = {
                              navController.navigate("user")  // Navigate to the 'user' screen
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
                                   style = TextStyle(fontWeight = FontWeight.Bold),
                                   color = Color.White,
                                   fontSize = 20.sp
                              )
                         }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    // Continue with Google Button
                    Button(
                         onClick = {
                              try {
                                   val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                        .requestIdToken(token)
                                        .requestEmail()
                                        .build()

                                   val googleSignInClient = GoogleSignIn.getClient(context, gso)

                                   // Sign out the current account before triggering the sign-in intent
                                   googleSignInClient.signOut().addOnCompleteListener {
                                        loading = true
                                        launcher.launch(googleSignInClient.signInIntent)
                                   }
                              } catch (e: Exception) {
                                   Log.e("GoogleSignIn", "Initialization failed", e)
                                   loading = false
                              }
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
                                   painter = painterResource(id = R.drawable.google_logo),
                                   contentDescription = "Google Logo Icon",
                                   modifier = Modifier.size(24.dp)
                              )
                              Spacer(modifier = Modifier.width(8.dp))
                              Text(
                                   text = "Continue with Google",
                                   style = TextStyle(fontWeight = FontWeight.Bold),
                                   color = Color.White,
                                   fontSize = 20.sp
                              )
                         }
                    }
               }
          }

          Spacer(modifier = Modifier.height(10.dp))

          Text(text = "OR")

          Spacer(modifier = Modifier.height(10.dp))

          // Create Account Button
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

          Text(text = "If you continue, you are accepting\n HLX Terms and Conditions")
     }
}
