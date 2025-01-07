package com.example.clx.NavigationBar

sealed class Screen(val screen: String) {
    data object Home : Screen("home")  // Just a string identifier
    data object Chat : Screen("chat")
    data object Account : Screen("account")
    data object Sell : Screen("sell")

}
