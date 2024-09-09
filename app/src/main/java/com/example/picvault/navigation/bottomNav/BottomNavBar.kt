package com.example.picvault.navigation.bottomNav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.picvault.navigation.AppScreen
import com.example.picvault.navigation.bottomNav.NavBarItems.moreItems
import com.example.picvault.navigation.bottomNav.NavBarItems.primaryItems

@Composable
fun BottomNavBar(navHostController: NavHostController) {

    var moreMenuExpanded by remember { mutableStateOf(false) }
    var isBarVisible by rememberSaveable { mutableStateOf(false) }

    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    isBarVisible = isBottomNavVisible(currentRoute)

    AnimatedVisibility(
        visible = isBarVisible,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        NavigationBar {
            primaryItems().forEach { navItem ->
                NavigationBarItem(
                    selected = navItem.route == currentRoute,
                    onClick = {
                        navHostController.navigate(navItem.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = { Icon(imageVector = navItem.image, contentDescription = "") },
                    label = { Text(text = navItem.title) })
            }

            NavigationBarItem(
                selected = false,
                onClick = { moreMenuExpanded = true },
                icon = {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "more icon"
                    )
                },
                label = { Text(text = "More") }
            )
        }
        MoreItemsMenu(moreMenuExpanded, navHostController) { moreMenuExpanded = false }
    }
}

@Composable
private fun MoreItemsMenu(
    isExpanded: Boolean,
    navHostController: NavHostController,
    onDismiss: () -> Unit,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    // Adjust the offset to move the dropdown to the right
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = onDismiss,
        offset = DpOffset(x = screenWidth.dp, y = 0.dp), // Adjust x to move right
        properties = PopupProperties(
            // Prevents the dropdown from closing when interacting with it
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
        )
    ) {
        moreItems().forEach { item ->
            DropdownMenuItem(
                onClick = {
                    navHostController.navigate(item.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    onDismiss() // Close the menu after selection
                }
            ) {
                Icon(imageVector = item.image, contentDescription = item.title)
                Spacer(Modifier.width(10.dp))
                Text(item.title)
            }
        }
    }
}

fun isBottomNavVisible(currentRoute: String?): Boolean {
    return currentRoute != AppScreen.Home.route && currentRoute != AppScreen.Login.route
}