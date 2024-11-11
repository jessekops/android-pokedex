package nl.kops.jesse.student_645147

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.kops.jesse.student_645147.ui.components.BottomNavBar
import nl.kops.jesse.student_645147.ui.favorites.FavoritesScreen
import nl.kops.jesse.student_645147.ui.main.MainScreen
import nl.kops.jesse.student_645147.ui.navigation.Navigation
import nl.kops.jesse.student_645147.ui.theme.Student645147Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Student645147Theme {
                val navController = rememberNavController()
                Navigation(navController = navController) // Use NavGraph
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Student645147Theme {
        val navController = rememberNavController()
        Navigation(navController = navController)
    }
}
