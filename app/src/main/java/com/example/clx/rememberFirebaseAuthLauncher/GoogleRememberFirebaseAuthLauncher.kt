package com.example.clx.rememberFirebaseAuthLauncher

import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


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
