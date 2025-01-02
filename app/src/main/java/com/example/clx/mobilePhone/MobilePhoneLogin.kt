package com.example.clx.mobilePhone

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

@Composable
fun MobileSignup(modifier: Modifier = Modifier, navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var otp by remember { mutableStateOf(TextFieldValue("")) }
    var isOtpVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var verificationId by remember { mutableStateOf<String?>(null) }
    var isCooldownActive by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center) {


        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,

            ) {
            //  Spacer(modifier = Modifier.height(150.dp))
            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(45.dp)

            ) {
                Text(
                    text = "Login with Phone Number",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { if (it.text.length <= 10) phoneNumber = it },
                    placeholder = { Text("Enter phone number") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    isError = errorMessage != null
                )

                errorMessage?.let {
                    Text(text = it, color = Color.Red, fontSize = 12.sp)
                }

                if (isOtpVisible) {
                    OutlinedTextField(
                        value = otp,
                        onValueChange = { otp = it },
                        placeholder = { Text("Enter OTP") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                Button(
                    onClick = {
                        if (!isOtpVisible && !isCooldownActive) {
                            isLoading = true
                            sendOtp(
                                auth = auth,
                                context = context as Activity,
                                phoneNumber = "+91${phoneNumber.text}",
                                onCodeSent = { id ->
                                    verificationId = id
                                    isOtpVisible = true
                                    errorMessage = null
                                    isLoading = false
                                    isCooldownActive = true
                                },
                                onFailure = { error ->
                                    errorMessage = error
                                    isLoading = false
                                }
                            )
                        } else if (isOtpVisible) {
                            verifyOtp(
                                auth = auth,
                                verificationId = verificationId,
                                otp = otp.text,
                                onSuccess = {
                                    navController.navigate("Home")
                                },
                                onFailure = { error ->
                                    errorMessage = error
                                    isLoading = false
                                }
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading && (!isCooldownActive || isOtpVisible)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
                    } else {
                        Text(text = if (isOtpVisible) "Verify OTP" else if (isCooldownActive) "Wait for Cooldown" else "Send OTP")
                    }
                }

                // Handle cooldown here
                if (isCooldownActive) {
                    startCooldown(60) {
                        isCooldownActive = false
                    }
                }

            }
        }
    }
}

private fun sendOtp(
    auth: FirebaseAuth,
    context: Activity,
    phoneNumber: String,
    onCodeSent: (String) -> Unit,
    onFailure: (String) -> Unit
) {
    Log.d("PhoneAuth", "Attempting to send OTP for phone number: $phoneNumber")

    val options = PhoneAuthOptions.newBuilder(auth)
        .setPhoneNumber(phoneNumber)
        .setTimeout(60L, TimeUnit.SECONDS)
        .setActivity(context)
        .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("PhoneAuth", "Verification completed: $credential")
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.e("PhoneAuth", "Verification failed: ${e.localizedMessage}", e)
                onFailure(e.localizedMessage ?: "Unknown error")
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d("PhoneAuth", "Code sent: $verificationId")
                onCodeSent(verificationId)
            }
        })
        .build()

    try {
        PhoneAuthProvider.verifyPhoneNumber(options)
    } catch (e: Exception) {
        Log.e("PhoneAuth", "Error occurred while sending OTP", e)
        onFailure("Error occurred while sending OTP: ${e.localizedMessage}")
    }
}

private fun verifyOtp(
    auth: FirebaseAuth,
    verificationId: String?,
    otp: String,
    onSuccess: () -> Unit,
    onFailure: (String) -> Unit
) {
    if (verificationId == null) {
        onFailure("Verification ID is null")
        return
    }

    val credential = PhoneAuthProvider.getCredential(verificationId, otp)
    auth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("PhoneAuth", "Sign-in successful")
                onSuccess()
            } else {
                Log.e("PhoneAuth", "Sign-in failed", task.exception)
                onFailure(task.exception?.localizedMessage ?: "Unknown error")
            }
        }
}

@Composable
private fun startCooldown(seconds: Int, onCooldownEnd: () -> Unit) {
    var remainingTime by remember { mutableStateOf(seconds) }

    LaunchedEffect(remainingTime) {
        while (remainingTime > 0) {
            kotlinx.coroutines.delay(1000L)
            remainingTime--
        }
        onCooldownEnd()
    }
}
