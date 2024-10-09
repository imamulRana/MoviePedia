package inc.anticbyte.moviepedia.presentation.screens.nowPlaying

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import inc.anticbyte.moviepedia.presentation.component.common.AppFilterChip
import inc.anticbyte.moviepedia.presentation.component.list.ListVerticalGrid
import inc.anticbyte.moviepedia.presentation.screens.ErrorScreen
import inc.anticbyte.moviepedia.presentation.screens.LoadingScreen
import inc.anticbyte.moviepedia.presentation.screens.MoviePediaViewModel
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import inc.anticbyte.moviepedia.utils.AppTabItems

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NowPlayingScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviePediaViewModel,
    onMovieClick: (Int) -> Unit
) {
    val nowPlayingMoviesUiState by viewModel.nowPlayingUiState.collectAsState()
    val nowPlayingMovies = viewModel.nowPlayingMovies.collectAsLazyPagingItems()

    if (nowPlayingMoviesUiState.error.isNotEmpty()) {
        ErrorScreen()
    } else
        Column(modifier = Modifier) {
            TopAppBar(title = { Text("Now Playing", style = MaterialTheme.typography.titleMedium) })
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
