package inc.anticbyte.moviepedia.presentation.component.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import inc.anticbyte.moviepedia.presentation.screens.MoviePediaViewModel
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import kotlin.math.absoluteValue

@Composable
fun AppCarousel(
    modifier: Modifier = Modifier,
    viewModel: MoviePediaViewModel,
    onMovieClick: (Int) -> Unit
) {
    val nowPlayingMovies by viewModel.homeUiState.collectAsState()
    val pagerState = rememberPagerState(initialPage = 5, initialPageOffsetFraction = -0.1f) { Int.MAX_VALUE }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            pageSize = PageSize.Fixed(200.dp),
            modifier = Modifier,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            snapPosition = SnapPosition.Center
        ) { page: Int ->
            val pageOffset = (
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(2 / 3f)
                    .graphicsLayer {
                        scaleY = lerp(
                            start = 0.8f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleX = lerp(
                            start = 0.8f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .clip(SnackbarDefaults.shape)
                    .clickable { onMovieClick(nowPlayingMovies.featuredMovie[page % nowPlayingMovies.featuredMovie.size].movieId) },
                model = nowPlayingMovies.featuredMovie[page % nowPlayingMovies.featuredMovie.size].moviePoster,
//                painter = painterResource(pagerItem[page % pagerItem.size]),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            nowPlayingMovies.featuredMovie[pagerState.currentPage % nowPlayingMovies.featuredMovie.size].movieTitle,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    MoviePediaTheme {
//        AppCarousel()
    }
}