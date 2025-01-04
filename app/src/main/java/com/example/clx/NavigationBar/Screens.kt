package com.example.clx.NavigationBar

sealed class Screens(val screen: String) {
    object Home : Screens("home")  // Just a string identifier
    object Chat : Screens("chat")
    object Account : Screens("account")
    object Sell : Screens("sell")
}
