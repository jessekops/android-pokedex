package nl.kops.jesse.student_645147.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nl.kops.jesse.student_645147.ui.components.BottomNavBar
import nl.kops.jesse.student_645147.ui.favorites.FavoritesScreen
import nl.kops.jesse.student_645147.ui.main.MainScreen

@Composable
fun Navigation(navController: NavHostController) {
    // Track the selected tab state
    var selectedTab = remember { mutableStateOf("main_screen") }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController,
                selectedTab = selectedTab.value,  // Pass state value
                onTabSelected = { newTab ->
                    selectedTab.value = newTab  // Update state value when tab is selected
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = "main_screen",
            modifier = Modifier.padding(it)
        ) {
            composable("main_screen") {
                MainScreen(navController)
            }
            composable("favorites_screen") {
                FavoritesScreen(navController)
            }
        }
    }
}