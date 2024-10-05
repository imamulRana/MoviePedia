/*
package inc.anticbyte.moviepedia.presentation.screens.trending

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import inc.anticbyte.moviepedia.presentation.component.common.AppFilterChip
import inc.anticbyte.moviepedia.presentation.component.item.TrendingItemMovie
import inc.anticbyte.moviepedia.presentation.screens.ShowsViewModel
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import inc.anticbyte.moviepedia.utils.AppTabItems

@Composable
fun TrendingScreen(modifier: Modifier = Modifier, viewModel: ShowsViewModel) {

    val trendingScreenUiState by viewModel.trendingScreenUiState.collectAsState()

    val pagerState = rememberPagerState(pageCount = { AppTabItems.entries.size })


    Column {
        AppFilterChip(chips = AppTabItems.entries.map { it.tabName })
        HorizontalPager(
            state = pagerState,
            key = { AppTabItems.entries[it].timeWindow },
            pageSize = PageSize.Fill,
            userScrollEnabled = false,
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
            ) {
                items(trendingScreenUiState.trendingAll.results) { trendingItem ->
                    TrendingItemMovie(
                        movieDto = trendingItem
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    MoviePediaTheme {
        TrendingScreen(viewModel = viewModel<ShowsViewModel>())
    }
}*/
