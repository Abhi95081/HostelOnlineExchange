package com.example.clx

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clx.login.homepage
import com.example.clx.login.loginPage
import com.example.clx.login.signupPage
import com.example.clx.mobilePhone.MobileSignup
import com.example.clx.mobilePhone.googleLogin

@Composable

fun MyNavigation(modifier: Modifier = Modifier, authviewModel: AuthviewModel){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login"){
            loginPage(modifier ,navController,authviewModel)
        }

        composable("signup"){
           signupPage(modifier,navController,authviewModel)
        }

        composable("home"){
            homepage(modifier,navController,authviewModel)
        }

        composable("phone") {
            MobileSignup(modifier = modifier, navController = navController, authviewModel = authviewModel)
        }


        composable("googleLogin"){
            googleLogin(modifier,navController,authviewModel)
        }
    })

}

