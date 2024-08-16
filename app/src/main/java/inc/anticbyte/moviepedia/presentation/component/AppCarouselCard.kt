package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.data.local.DataSource.superHeroesList
import inc.anticbyte.moviepedia.data.remote.ShowDto
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppCarouselCard(modifier: Modifier = Modifier, showDto: List<ShowDto>) {
    val state = rememberPagerState { superHeroesList.size }
    HorizontalPager(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeightIn(300.dp, 500.dp),
        state = state,
        pageSize = PageSize.Fill,
    ) {
        Box(modifier = modifier) {
            MovieImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeightIn(300.dp, 500.dp)
                    .drawWithContent {
                        drawContent()
                        drawRect(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                )
                            )
                        )
                    },
                imageUrl = showDto[it].image?.original ?: "",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                CarouselCardText(movieName = superHeroesList[it].second, movieYear = "2023")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    MoviePediaTheme {
//        AppCarouselCard()
    }
}