package nl.kops.jesse.student_645147.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.kops.jesse.student_645147.R


@Composable
fun BottomNavBar(
    navController: NavController,
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .background(Color(0xFFEDF6FF)),
        contentPadding = PaddingValues(horizontal = 20.dp),
        containerColor = Color(0xFFEDF6FF)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                iconRes = R.drawable.ic_home,
                label = "Pokémon",
                selected = selectedTab == "main_screen",
                onClick = {
                    onTabSelected("main_screen")
                    navController.navigate("main_screen") {
                        popUpTo("main_screen") { inclusive = true }
                    }
                }
            )
            BottomNavItem(
                iconRes = R.drawable.ic_favorites,
                label = "Favorites",
                selected = selectedTab == "favorites_screen",
                onClick = {
                    onTabSelected("favorites_screen")
                    navController.navigate("favorites_screen") {
                        popUpTo("favorites_screen") { inclusive = true }
                    }
                }
            )
        }
    }
}
