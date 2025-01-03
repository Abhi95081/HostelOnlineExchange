package com.example.clx.mobilePhone

import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clx.AuthState
import com.example.clx.AuthviewModel
import com.example.clx.R
import com.example.clx.mobilePhone.AuthState.Loading
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

object AuthState {
    var Loading: Boolean by mutableStateOf(false)
}

@Composable
fun MobileSignup(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthviewModel) {
    val user by remember { mutableStateOf(Firebase.auth.currentUser) }
    val context = LocalContext.current
    val authState by authViewModel.authState.observeAsState()
    val launcher = rememberFirebaseAuthLauncher(
        onAuthComplete = { result ->
            authViewModel.checkAuthState() // Trigger ViewModel to update state
            Loading = false
        },
        onAuthError = { exception ->
            Log.e("GoogleAuth", "Sign-In failed: ${exception.message}", exception)
            Loading = false
        }
    )
    val token = stringResource(id = R.string.clint_id)

    // Observe the authentication state and navigate accordingly
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
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (Loading) {
            CircularProgressIndicator()
        } else {
            if (authState is AuthState.Unauthenticated || authState == null) {
                Button(onClick = {
                    try {
                        // Create GoogleSignInOptions
                        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(token)
                            .requestEmail()
                            .build()

                        val googleSignInClient = GoogleSignIn.getClient(context, gso)

                        // Sign out the current account before triggering the sign-in intent
                        googleSignInClient.signOut().addOnCompleteListener {
                            // After sign-out, launch the Google Sign-In intent
                            Loading = true
                            launcher.launch(googleSignInClient.signInIntent)
                        }
                    } catch (e: Exception) {
                        Log.e("GoogleSignIn", "Initialization failed", e)
                        Loading = false
                    }
                }) {
                    Text(text = "Sign in via Google")
                }
            }
        }
    }
}


@Composable
fun rememberFirebaseAuthLauncher(
    onAuthComplete: (AuthResult) -> Unit,
    onAuthError: (Exception) -> Unit
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val idToken = account?.idToken
            if (idToken != null) {
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                scope.launch {
                    try {
                        val authResult = Firebase.auth.signInWithCredential(credential).await()
                        onAuthComplete(authResult)
                    } catch (authException: Exception) {
                        Log.e("FirebaseAuth", "Sign-In with credential failed", authException)
                        onAuthError(authException)
                    }
                }
            } else {
                onAuthError(IllegalStateException("Google ID Token is null"))
            }
        } catch (e: ApiException) {
            Log.e("GoogleAuth", "Google Sign-In failed", e)
            onAuthError(e)
        } catch (e: Exception) {
            Log.e("GoogleAuth", "Unexpected error", e)
            onAuthError(e)
        }
    }
}
