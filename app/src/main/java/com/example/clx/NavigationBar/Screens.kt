package com.example.clx.NavigationBar

sealed class Screens(val screen: String) {
    data object Home : Screens("home")  // Just a string identifier
    data object Chat : Screens("chat")
    data object Account : Screens("account")
    data object Sell : Screens("sell")

}
