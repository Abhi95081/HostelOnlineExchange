package com.example.clx

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clx.login.UserLogin
import com.example.clx.login.homepage
import com.example.clx.login.loginPage
import com.example.clx.login.signupPage
import com.example.clx.mobilePhone.MobileSignup

@Composable
fun MyNavigation(modifier: Modifier = Modifier, authviewModel: AuthviewModel) {
    val navController = rememberNavController()

    val isUserLoggedIn by authviewModel.isUserLoggedIn.collectAsState()

    // LaunchedEffect to handle navigation state when the login status changes
    LaunchedEffect(isUserLoggedIn) {
        if (isUserLoggedIn) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        } else {
            navController.navigate("login") {
                popUpTo("home") { inclusive = true }
            }
        }
    }

    // Navigation graph
    NavHost(
        navController = navController,
        startDestination = if (isUserLoggedIn) "home" else "login",
        builder = {
            composable("login") {
                loginPage(modifier, navController, authviewModel)
            }
            composable("signup") {
                signupPage(modifier, navController, authviewModel)
            }
            composable("home") {
                homepage(modifier, navController, authviewModel)
            }
            composable("phone") {
                MobileSignup(modifier, navController,authviewModel)
            }
            composable("user") {
                UserLogin(modifier, navController, authviewModel)
            }
        }
    )
}
