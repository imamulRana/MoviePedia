package inc.anticbyte.moviepedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import inc.anticbyte.moviepedia.navigation.MoviePediaNavHost
import inc.anticbyte.moviepedia.presentation.screens.ShowsViewModel
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val hostState = remember { SnackbarHostState() }
            val navController = rememberNavController()
            val viewModel by viewModels<ShowsViewModel>()
            MoviePediaTheme {
                Scaffold(snackbarHost = {
                    SnackbarHost(
                        hostState = hostState,
                        snackbar = { })
                }, bottomBar = { }) { innerPadding ->
                    MoviePediaNavHost(
                        modifier = Modifier
                            .padding(innerPadding),
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}