package com.example.clx.NavigationBar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun MainScreen() {
    var selectedSection by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            MyBottomNavigationBar(
                selectedSection = selectedSection,
                onSectionSelected = { selectedSection = it }
            )
        }
    ) { innerPadding ->
        // Content based on the selected section
        when (selectedSection) {
            0 -> HomeContent(Modifier.padding(innerPadding))
            1 -> ChatContent(Modifier.padding(innerPadding))
            2 -> AccountContent(Modifier.padding(innerPadding))
            3 -> SellContent(Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun MyBottomNavigationBar(
    selectedSection: Int,
    onSectionSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomNavigation(modifier = modifier) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            selected = selectedSection == 0,
            onClick = { onSectionSelected(0) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Face, contentDescription = "Chat") },
            selected = selectedSection == 1,
            onClick = { onSectionSelected(1) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Account") },
            selected = selectedSection == 2,
            onClick = { onSectionSelected(2) }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Add, contentDescription = "Sell") },
            selected = selectedSection == 3,
            onClick = { onSectionSelected(3) }
        )
    }
}

@Composable
fun HomeContent(modifier: Modifier) {
    // Your Home content here
    Text("Home Screen", modifier = modifier)
}

@Composable
fun ChatContent(modifier: Modifier) {
    // Your Chat content here
    Text("Chat Screen", modifier = modifier)
}

@Composable
fun AccountContent(modifier: Modifier) {
    // Your Account content here
    Text("Account Screen", modifier = modifier)
}

@Composable
fun SellContent(modifier: Modifier) {
    // Your Sell content here
    Text("Sell Screen", modifier = modifier)
}