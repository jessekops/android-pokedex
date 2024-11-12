package nl.kops.jesse.student_645147

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import nl.kops.jesse.student_645147.ui.navigation.Navigation
import nl.kops.jesse.student_645147.ui.theme.Student645147Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Student645147Theme {
                val navController = rememberNavController()
                Navigation(navController = navController)
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
