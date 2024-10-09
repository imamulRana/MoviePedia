package inc.anticbyte.moviepedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import inc.anticbyte.moviepedia.navigation.MoviePediaNavHost
import inc.anticbyte.moviepedia.navigation.MoviePediaScreens
import inc.anticbyte.moviepedia.presentation.component.common.AppBottomBar
import inc.anticbyte.moviepedia.presentation.screens.MoviePediaViewModel
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import kotlinx.serialization.ExperimentalSerializationApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSerializationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val hostState = remember { SnackbarHostState() }
            val navController = rememberNavController()
            val viewModel by viewModels<MoviePediaViewModel>()
            val currentRoute by navController.currentBackStackEntryAsState()
            val currentScreen = currentRoute?.destination?.route?.substringBefore("?")
            MoviePediaTheme {
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(
                            hostState = hostState,
                            snackbar = { })
                    },

                    bottomBar = {
                        if (currentScreen != MoviePediaScreens.MovieDetail::class.qualifiedName && currentScreen != MoviePediaScreens.Search::class.qualifiedName)
                            AppBottomBar(viewModel = viewModel, navController = navController)
                    },
//                    contentWindowInsets = WindowInsets.
                ) { innerPadding ->
                    MoviePediaNavHost(
                        modifier = Modifier
                            .padding(bottom = innerPadding.calculateBottomPadding()),
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}