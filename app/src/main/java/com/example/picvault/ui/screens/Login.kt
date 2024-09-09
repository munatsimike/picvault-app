package com.example.picvault.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.picvault.navigation.AppScreen

object Login {

    @Composable
    fun Screen(navHostController: NavHostController) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Login", modifier = Modifier.clickable {
                navHostController.navigate(AppScreen.Home.route)
            })
        }
    }
}