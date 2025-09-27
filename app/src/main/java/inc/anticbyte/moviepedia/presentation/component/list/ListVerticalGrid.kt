package inc.anticbyte.moviepedia.presentation.component.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import inc.anticbyte.moviepedia.domain.model.Movie
import inc.anticbyte.moviepedia.presentation.component.item.ItemVerticalGrid

@Composable
fun ListVerticalGrid(
    modifier: Modifier = Modifier,
    gridItems: LazyPagingItems<Movie>,
    onMovieClick: (Int) -> Unit
) {

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(gridItems.itemCount) { movie ->
            ItemVerticalGrid(
                modifier = modifier.animateItem(),
                movie = gridItems[movie]!!,
                onMovieClick = onMovieClick)
        }
        gridItems.apply {
            when {
                loadState.append is LoadState.Loading -> {
                    item(span = { GridItemSpan(3) }) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding().plus(16.dp))
                                .size(16.dp)
                                .wrapContentSize(),
                            strokeWidth = 2.dp
                        )
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val e = gridItems.loadState.refresh as LoadState.Error
                    item {
                        Text(text = "Error: ${e.error.localizedMessage}")
                    }
                }
            }
        }

    }
}

