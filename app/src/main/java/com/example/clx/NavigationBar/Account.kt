package com.example.clx.NavigationBar


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.clx.AuthState
import com.example.clx.AuthviewModel


@Composable
fun Account(modifier: androidx.compose.ui.Modifier, navController: NavController, authviewModel: AuthviewModel){

    val authState = authviewModel.authState.observeAsState()


    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Account Page", fontSize = 45.sp, color = Color.Gray)

        TextButton(onClick = {
            authviewModel.signOut()
        }) {
            androidx.compose.material3.Text(text = "Sign out")
        }

    }
}