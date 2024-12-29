package inc.anticbyte.moviepedia.presentation.screens.watchList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import inc.anticbyte.moviepedia.navigation.MoviePediaScreens
import inc.anticbyte.moviepedia.presentation.component.item.ItemWatchList
import inc.anticbyte.moviepedia.presentation.screens.ErrorScreen
import inc.anticbyte.moviepedia.presentation.screens.LoadingScreen
import inc.anticbyte.moviepedia.presentation.screens.MoviePediaViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchListScreen(viewModel: MoviePediaViewModel, navController: NavController) {
    val movies by viewModel.watchListUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        snapshotFlow { movies.movies }
            .distinctUntilChanged()
            .collectLatest {
                viewModel.getMovieWatchList()
            }
    }

    if (movies.isLoading) {
        LoadingScreen()
    } else if (movies.movies.isEmpty()) {
        ErrorScreen(onRetry = {
            viewModel.getMovieWatchList()
        })
    } else {
        Column {
            TopAppBar(title = {
                Text(
                    text = "Watchlist",
                    style = MaterialTheme.typography.titleMedium
                )
            })
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(
                    top = 8.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp
                )
            ) {
                items(movies.movies, key = { it.movieId }) { movie ->
                    ItemWatchList(movieWatchList = movie, onClick = {
                        viewModel.getMovieDetail(movie.movieId)
                        navController.navigate(MoviePediaScreens.MovieDetail(movie.movieId))
                    }, modifier = Modifier.animateItem())
                }
            }
        }
    }
}

@Preview
@Composable
private fun DefPrev() {
//    WatchListScreen()
}
