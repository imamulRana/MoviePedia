package inc.anticbyte.moviepedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import inc.anticbyte.moviepedia.navigation.MoviePediaNavHost
import inc.anticbyte.moviepedia.presentation.screens.ErrorScreen
import inc.anticbyte.moviepedia.presentation.screens.LoadingScreen
import inc.anticbyte.moviepedia.presentation.screens.ShowsViewModel
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import inc.anticbyte.moviepedia.utils.RequestState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.Transparent.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(Color.Transparent.toArgb())
        )
        setContent {
            val viewModel by viewModels<ShowsViewModel>()
            val show by viewModel.allShow.collectAsState()
            val navController = rememberNavController()
            MoviePediaTheme {
                when (show) {
                    RequestState.Loading -> {
                        LoadingScreen()
                    }

                    is RequestState.Success -> {
                        MoviePediaNavHost(
                            modifier = Modifier.fillMaxSize(),
                            navController = navController,
                            viewModel = viewModel
                        )
                    }

                    is RequestState.Error -> {
                        RequestState.Error("Something went wrong")
                        ErrorScreen()
                    }
                }
            }
        }
    }
}
