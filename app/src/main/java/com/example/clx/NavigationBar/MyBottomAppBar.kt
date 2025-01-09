package com.example.clx.NavigationBar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.clx.AuthviewModel
import com.example.clx.NavigationBar.Pages.Account
import com.example.clx.NavigationBar.Pages.Chat
import com.example.clx.NavigationBar.Pages.Homepage
import com.example.clx.NavigationBar.Pages.Sell

@Composable
fun MyBottomAppBar(modifier: Modifier, navController: NavController, authviewModel: AuthviewModel) {

    val navItemList = listOf(
        Navitems("Home",Icons.Default.Home),
        Navitems("Chat",Icons.Default.Face),
        Navitems("Sell",Icons.Default.Add),
        Navitems("Account",Icons.Default.Person)

    )

    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(modifier=Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navitems ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                        },
                        label = { Text(text = navitems.label) },
                        icon = {
                            Icon(
                                imageVector = navitems.icon,
                                contentDescription = "Icon"
                            )
                        })
                }
            }
        }
        ) { innerPadding ->
        ContentScreen(modifier.padding(innerPadding),selectedItem, navController, authviewModel)
    }


}

@Composable
fun ContentScreen(modifier: Modifier = Modifier,selectedindex : Int, navController: NavController, authviewModel: AuthviewModel) {

    when(selectedindex){
        0 -> Homepage(modifier, navController, authviewModel)
        1 ->  Chat(modifier, navController, authviewModel)
        2 ->  Sell(modifier, navController, authviewModel)
        3 -> Account(modifier, navController, authviewModel)

    }
}