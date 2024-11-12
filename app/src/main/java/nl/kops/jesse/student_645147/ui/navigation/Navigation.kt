package nl.kops.jesse.student_645147.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nl.kops.jesse.student_645147.ui.components.BottomNavBar
import nl.kops.jesse.student_645147.ui.details.DetailScreen
import nl.kops.jesse.student_645147.ui.favorites.FavoritesScreen
import nl.kops.jesse.student_645147.ui.main.MainScreen

@Composable
fun Navigation(navController: NavHostController) {
    var selectedTab = remember { mutableStateOf("main_screen") }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController,
                selectedTab = selectedTab.value,
                onTabSelected = { newTab ->
                    selectedTab.value = newTab
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
            composable("detailscreen/{pokemonId}") { backStackEntry ->
                val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toInt() ?: 0
                DetailScreen(navController, pokemonId, viewModel = hiltViewModel())
            }
        }
    }
}
