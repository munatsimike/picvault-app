package com.example.picvault.navigation.bottomNav

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.picvault.R
import com.example.picvault.navigation.AppScreen

object NavBarItems {
    @Composable
    fun primaryItems () = listOf(
        BarItem(
            title = AppScreen.Home.title, image = ImageVector.vectorResource(id = R.drawable.home_24px), route = AppScreen.Home.route
        ),

        BarItem(
            title = AppScreen.Dashboard.title,
            image = ImageVector.vectorResource(id = R.drawable.dashboard_24px),
            route = AppScreen.Dashboard.route
        ),

        BarItem(
            title = AppScreen.Schedule.title,
            image = ImageVector.vectorResource(id = R.drawable.date_range_24px),
            route = AppScreen.Schedule.route
        ),
    )

    @Composable
    fun moreItems() = listOf(

        BarItem(
            title = AppScreen.Budget.title,
            image = ImageVector.vectorResource(id = R.drawable.money_bag_24px),
            route = AppScreen.Budget.route
        ),

        BarItem(
            title = AppScreen.ORFI.title,
            image = ImageVector.vectorResource(id = R.drawable.help_center_24px),
            route = AppScreen.ORFI.route
        ),

        BarItem(
            title = AppScreen.Users.title,
            image = ImageVector.vectorResource(id = R.drawable.users),
            route = AppScreen.Users.route
        ),
    )
}