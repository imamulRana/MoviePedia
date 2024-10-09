package inc.anticbyte.moviepedia.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.navigation.MoviePediaScreens
import inc.anticbyte.moviepedia.presentation.component.common.AppCarousel
import inc.anticbyte.moviepedia.presentation.component.list.ListNowPlayingMovies
import inc.anticbyte.moviepedia.presentation.component.list.ListPopularMovie
import inc.anticbyte.moviepedia.presentation.component.list.ListTrendingMovie
import inc.anticbyte.moviepedia.presentation.screens.ErrorScreen
import inc.anticbyte.moviepedia.presentation.screens.LoadingScreen
import inc.anticbyte.moviepedia.presentation.screens.MoviePediaViewModel
import inc.anticbyte.moviepedia.utils.AppTabItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MoviePediaViewModel, navController: NavController) {
    val uiState by viewModel.homeUiState.collectAsState()
    val scrollState = rememberScrollState()

    if (uiState.isLoading) {
        LoadingScreen()
    } else if (uiState.error.isNotEmpty()) {
        ErrorScreen()
    } else {
        Column(
            modifier = Modifier.verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleMedium
                )
            }, actions = {
                IconButton(onClick = {
                    viewModel.getMovieBySearch("")
                    navController.navigate(MoviePediaScreens.Search)
                }) {
                    Icon(painter = painterResource(R.drawable.ic_search), contentDescription = "")
                }
            })
            AppCarousel(viewModel = viewModel, onMovieClick = {
                viewModel.getMovieDetail(it)
                navController.navigate(
                    MoviePediaScreens.MovieDetail(it)
                )
            })
            // Trending List Movies

            ListTrendingMovie(
                sectionTitle = R.string.trending_movies,
                movies = uiState.trendingMovies,
                navigateToScreenDetails = { movieId ->
                    viewModel.getMovieDetail(movieId)
                    navController.navigate(MoviePediaScreens.MovieDetail(movieId))
                },
                navigateToScreenTrending = {
                    viewModel.getTrendingMovies(AppTabItems.DAY.timeWindow)
                    navController.navigate(MoviePediaScreens.Trending)
                })

            // Popular List Movies

            ListPopularMovie(
                sectionTitle = R.string.popular_movies,
                movies = uiState.popularMovies,
                navigateToScreenDetails = { movieId ->
                    viewModel.getMovieDetail(movieId)
                    navController.navigate(MoviePediaScreens.MovieDetail(movieId))
                }
            )

            // Now Playing List Movies

            ListNowPlayingMovies(
                sectionTitle = R.string.now_playing,
                movies = uiState.nowPlayingMovies,
                navigateToScreenDetails = { movieId ->
                    viewModel.getMovieDetail(movieId)
                    navController.navigate(MoviePediaScreens.MovieDetail(movieId))
                },
                navigateToScreenNowPlaying = {
                    viewModel.getNowPlayingMovies()
                    navController.navigate(MoviePediaScreens.NowPlaying)
                }
            )
        }
    }
}
