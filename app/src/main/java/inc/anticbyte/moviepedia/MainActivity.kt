package inc.anticbyte.moviepedia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import inc.anticbyte.moviepedia.data.remote.ShowDto
import inc.anticbyte.moviepedia.presentation.component.AppBottomBar
import inc.anticbyte.moviepedia.presentation.component.AppCarouselCard
import inc.anticbyte.moviepedia.presentation.component.AppSnackBar
import inc.anticbyte.moviepedia.presentation.component.ShowsList
import inc.anticbyte.moviepedia.presentation.screens.ErrorScreen
import inc.anticbyte.moviepedia.presentation.screens.ShowsViewModel
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import inc.anticbyte.moviepedia.utils.RequestState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel by viewModels<ShowsViewModel>()
            val show by viewModel.show.collectAsState()
            val hostState = remember { SnackbarHostState() }
            MoviePediaTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = {
                    SnackbarHost(
                        hostState = hostState,
                        snackbar = { AppSnackBar(snackBarData = it) })
                }, bottomBar = { AppBottomBar() }) { innerPadding ->

                    Column(Modifier.padding(innerPadding)) {
                        when (show) {
                            RequestState.Loading -> {
                                LoadingScreen()
                            }

                            is RequestState.Success -> {
                                val showData = (show as RequestState.Success<List<ShowDto>>)
                                LazyColumn {
                                    item {
                                        AppCarouselCard(showDto = showData.data.subList(0, 6))
                                    }
                                    item {
                                        ShowsList(shows = showData.data)
                                    }
                                }
                            }

                            is RequestState.Error -> {
                                RequestState.Error("Something went wrong")
                                LaunchedEffect(Unit) {
                                    hostState.showSnackbar(
                                        message = hostState.currentSnackbarData?.visuals?.message
                                            ?: ""
                                    )
                                }
                                ErrorScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}