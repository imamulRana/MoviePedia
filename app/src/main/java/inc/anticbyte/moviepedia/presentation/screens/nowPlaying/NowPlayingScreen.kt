package inc.anticbyte.moviepedia.presentation.screens.nowPlaying

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.presentation.component.list.ListVerticalGrid
import inc.anticbyte.moviepedia.presentation.screens.ErrorScreen
import inc.anticbyte.moviepedia.presentation.screens.LoadingScreen
import inc.anticbyte.moviepedia.presentation.screens.MoviePediaViewModel
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NowPlayingScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviePediaViewModel,
    onMovieClick: (Int) -> Unit,
    onBackClick: () -> Unit = {}
) {
    val nowPlayingMoviesUiState by viewModel.nowPlayingUiState.collectAsStateWithLifecycle()
    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsLazyPagingItems()

    if (nowPlayingMoviesUiState.error.isNotEmpty()) {
        ErrorScreen()
    } else
        Column(modifier = Modifier) {
            TopAppBar(title = { Text("Now Playing", style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back_ios),
                            contentDescription = "Back"
                        )
                    }
                }
            )
            if (nowPlayingMoviesUiState.isLoading) {
                LoadingScreen()
            } else
                ListVerticalGrid(
                    gridItems = nowPlayingMovies,
                    onMovieClick = onMovieClick
                )
        }

}


@Preview
@Composable
private fun DefPrev() {
    MoviePediaTheme {
//        TrendingScreen()
    }
}
