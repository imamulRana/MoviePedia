package inc.anticbyte.moviepedia.presentation.screens.trending

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
fun TrendingScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviePediaViewModel,
    onMovieClick: (Int) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { AppTabItems.entries.size })
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val trendingMovies by viewModel.trendingUiState.collectAsState()
    val trendingMoviesDay = viewModel.trendingMovies.collectAsLazyPagingItems()



    if (trendingMovies.error.isNotEmpty()) {
        ErrorScreen()
    } else
        Column(modifier = Modifier) {
            TopAppBar(title = { Text("Trending", style = MaterialTheme.typography.titleMedium) })
            AppFilterChip(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 4.dp),
                selectedIndex = selectedIndex,
                chips = AppTabItems.entries.map { it.tabName },
                onChipSelected = { selectedIndex = it;viewModel.getTrendingMovies(AppTabItems.entries[selectedIndex].timeWindow) }
            )
            if (trendingMovies.isLoading) {
                LoadingScreen()
            } else
                when (AppTabItems.entries[selectedIndex]) {
                    AppTabItems.DAY -> {
                        ListVerticalGrid(
                            gridItems = trendingMoviesDay,
                            onMovieClick = onMovieClick
                        )
                    }

                    AppTabItems.WEEK -> {
                        ListVerticalGrid(
                            gridItems = trendingMoviesDay,
                            onMovieClick = onMovieClick
                        )
                    }
                }
        }

}


@Preview
@Composable
private fun DefPrev() {
    MoviePediaTheme {
//        TrendingScreen()
    }
}
