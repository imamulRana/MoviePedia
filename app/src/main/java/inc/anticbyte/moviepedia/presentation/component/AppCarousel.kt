package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import inc.anticbyte.moviepedia.data.remote.ShowDto
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import kotlin.math.absoluteValue

@Composable
fun AppCarousel(modifier: Modifier = Modifier, showDto: List<ShowDto>) {
    val state =
        rememberPagerState(initialPage = Int.MAX_VALUE.div(2), pageCount = { Int.MAX_VALUE })
    val pageOffset = state.currentPageOffsetFraction.absoluteValue
    HorizontalPager(
        state = state, modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        pageSpacing = 8.dp,
        pageSize = PageSize.Fill
    ) { page ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val scale = lerp(1f, .7f, pageOffset.coerceIn(0f, 1f))
            AsyncImage(
                modifier = Modifier
                    .graphicsLayer(scaleX = scale, scaleY = scale)
                    .clip(CardDefaults.shape)
                    .clickable { }
                    .fillMaxWidth()
                    .requiredHeightIn(300.dp, 500.dp),
                model = showDto[page % showDto.size].image?.original,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = showDto[page % showDto.size].name ?: "N/A",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = showDto[page % showDto.size].premiered?.take(4) ?: "N/A",
                    style = MaterialTheme.typography.bodyLarge
                )
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