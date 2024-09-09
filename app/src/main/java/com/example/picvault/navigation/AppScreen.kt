package com.example.picvault.navigation

sealed class AppScreen (val route: String, val title: String) {
    data object Login: AppScreen("login", "Login")
    data object Home: AppScreen("home", "Home")
    data object Budget: AppScreen("budget", "Budget")
    data object Schedule: AppScreen("schedule", "Schedule")
    data object Users: AppScreen("users", "Users")
    data object ORFI: AppScreen("orfi", "ORFI")
    data object Dashboard: AppScreen("dashboard", "Dashboard")
}