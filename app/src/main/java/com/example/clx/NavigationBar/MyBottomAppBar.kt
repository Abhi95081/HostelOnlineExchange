package com.example.clx.NavigationBar

import Homepage
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clx.AuthviewModel

@Composable
fun MyBottomAppBar(modifier: androidx.compose.ui.Modifier, navController: NavController, authviewModel: AuthviewModel){

    var navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                contentColor = Color.Cyan
            ) {
                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Home
                        navigationController.navigate("home") {
                            popUpTo(0)
                        }
                    }, modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Home, contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Home) Color.White else Color.Gray
                    )
                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Face
                        navigationController.navigate("chat") {
                            popUpTo(0)
                        }
                    }, modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Face, contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Face) Color.White else Color.Gray
                    )
                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.AddCircle
                        navigationController.navigate("sell") {
                            popUpTo(0)
                        }
                    }, modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.AddCircle, contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.AddCircle) Color.White else Color.Gray
                    )
                }

                IconButton(
                    onClick = {
                        selected.value = Icons.Default.Person
                        navigationController.navigate("account") {
                            popUpTo(0)
                        }
                    }, modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Person, contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Person) Color.White else Color.Gray
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navigationController,
            startDestination = Screens.Home.screen,
        ) {
            composable(Screens.Home.screen) { Homepage(
                modifier = modifier,
                navController = navigationController,
                authviewModel = AuthviewModel()
            )}
            composable(Screens.Chat.screen) {
                chat(
                    modifier = modifier,
                    navController = navigationController,
                    authviewModel = AuthviewModel()
                )
            }
            composable(Screens.Sell.screen) { Sell(
                modifier = modifier,
                navController = navigationController,
                authviewModel = AuthviewModel()
            ) }
            composable(Screens.Account.screen) { Account(
                modifier = modifier,
                navController = navigationController,
                authviewModel = AuthviewModel()
            ) }

        }
    }
}