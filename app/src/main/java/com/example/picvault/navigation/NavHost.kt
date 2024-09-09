package com.example.picvault.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.picvault.ui.screens.Budget
import com.example.picvault.ui.screens.Dashboard
import com.example.picvault.ui.screens.Home
import com.example.picvault.ui.screens.Login
import com.example.picvault.ui.screens.ORFI
import com.example.picvault.ui.screens.Schedule
import com.example.picvault.ui.screens.Users

object NavHost {
    @Composable
    fun MyNavHost(navController: NavHostController) {
        NavHost(navController = navController, startDestination = AppScreen.Login.route) {

            composable(AppScreen.Login.route) {
                Login.Screen(navHostController = navController)
            }

            composable(AppScreen.Home.route) {
                Home.Screen(navController)
            }

            composable(AppScreen.Dashboard.route) {
                Dashboard.Screen()
            }

            composable(AppScreen.Schedule.route) {
                Schedule.Screen()
            }

            composable(AppScreen.Budget.route) {
                Budget.Screen()
            }

            composable(AppScreen.Users.route) {
                Users.Screen()
            }

            composable(AppScreen.ORFI.route) {
                ORFI.Screen()
            }
        }
    }
}