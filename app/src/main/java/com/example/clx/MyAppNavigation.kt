package com.example.clx



import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clx.NavigationBar.MyBottomAppBar
import com.example.clx.NavigationBar.Pages.Account
import com.example.clx.NavigationBar.Pages.Chat
import com.example.clx.NavigationBar.Pages.Homepage
import com.example.clx.NavigationBar.Pages.Profile
import com.example.clx.NavigationBar.Pages.Sell
import com.example.clx.login.UserLogin
import com.example.clx.login.loginPage
import signupPage

@Composable
fun MyNavigation(modifier: Modifier = Modifier, authviewModel: AuthviewModel) {

    val navController = rememberNavController()

    val isUserLoggedIn by authviewModel.isUserLoggedIn.collectAsState()


    // LaunchedEffect to handle navigation state when the login status changes
    LaunchedEffect(isUserLoggedIn) {
        if (isUserLoggedIn) {
            navController.navigate("bottom_nav_home") {  // Navigate to MyBottomAppBar
                popUpTo("login") { inclusive = true }
            }
        } else {
            navController.navigate("login") {  // Navigate to home if not logged in
                popUpTo("home") { inclusive = true }
            }
        }
    }

    // Navigation graph
    NavHost(
        navController = navController,
        startDestination = if (isUserLoggedIn) "bottom_nav_home" else "login",  // Start depending on login state
        builder = {
            composable("login") {
                loginPage(modifier, navController, authviewModel)
            }
            composable("signup") {
                signupPage(modifier, navController, authviewModel)
            }
            composable("home") {
                Homepage(modifier, navController,authviewModel)
            }

            composable("bottom_nav_home") {
                MyBottomAppBar(modifier, navController, authviewModel)  // The bottom nav appears after login
            }
            composable("user") {
                UserLogin(modifier, navController, authviewModel)
            }
            composable("account") {
                Account(modifier, navController, authviewModel)
            }
            composable("chat") {
                Chat(modifier, navController, authviewModel)
            }
            composable("sell") {
                Sell(modifier, navController, authviewModel)
            }
            composable("EditProfile") {
                Profile(modifier, navController, authviewModel)
            }

        }
    )
}
