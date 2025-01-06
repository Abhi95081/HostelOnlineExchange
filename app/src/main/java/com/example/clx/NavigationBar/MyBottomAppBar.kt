package com.example.clx.NavigationBar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MyBottomNavigationBar(modifier: Modifier, navController: NavController) {

    BottomNavigation(
        modifier = modifier
    ) {
        val currentRoute = navController.currentDestination?.route

        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            selected = currentRoute == Screens.Home.screen,
            onClick = {
                navController.navigate(Screens.Home.screen) {
                    // Avoid multiple copies of the same destination
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Face, contentDescription = "Chat") },
            selected = currentRoute == Screens.Chat.screen,
            onClick = {
                navController.navigate(Screens.Chat.screen) {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Account") },
            selected = currentRoute == Screens.Account.screen,
            onClick = {
                navController.navigate(Screens.Account.screen) {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Add, contentDescription = "Sell") },
            selected = currentRoute == Screens.Sell.screen,
            onClick = {
                navController.navigate(Screens.Sell.screen) {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
