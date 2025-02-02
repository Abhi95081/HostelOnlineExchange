package com.example.clx.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clx.AuthState
import com.example.clx.AuthviewModel
import com.example.clx.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition



@Composable


fun UserLogin(modifier: Modifier,
              navController: NavController, authviewModel: AuthviewModel){

    var email by remember {
        mutableStateOf("")
    }

    var pass by remember {
        mutableStateOf("")
    }


    val authState = authviewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("bottom_nav_home")
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
            else-> Unit
        }
    }

    //Lotie animation

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottieanimationlogin))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            modifier=Modifier.size(300.dp),
            composition = composition)
        Text(
            text = "User Login",
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = email, onValueChange = {
            email = it
        },
            label = {
                Text(text = "Email")
            },
            singleLine = true

        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = pass,
            onValueChange = {
            pass = it
        },label = {
            Text(text = "Password")
        },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            authviewModel.UserLogin(email,pass)
        }, enabled = authState.value != AuthState.Loading
        ) {
            Text(text = "Login")
        }
    }
}