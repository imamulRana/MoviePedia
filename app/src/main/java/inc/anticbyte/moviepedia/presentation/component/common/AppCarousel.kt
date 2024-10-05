package inc.anticbyte.moviepedia.presentation.component.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import inc.anticbyte.moviepedia.domain.model.Movie
import kotlin.math.absoluteValue


@Composable
fun AppCarousel(
    modifier: Modifier = Modifier, topMovie: List<Movie>, onClick: () -> Unit,
    pagerState: PagerState
) {
    val pageOffset = pagerState.currentPageOffsetFraction.absoluteValue
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 300.dp),
        pageSpacing = 16.dp,
        pageSize = PageSize.Fill,
    ) { page ->
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val scale = lerp(1f, .7f, pageOffset.coerceIn(0f, 1f))
            AsyncImage(
                modifier = Modifier
                    .graphicsLayer(scaleX = scale, scaleY = scale)
                    .clip(CardDefaults.shape)
                    .clickable { onClick() }
                    .fillMaxWidth()
                    .height(300.dp),
                model = topMovie[page % topMovie.size].moviePoster,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = topMovie[page % topMovie.size].movieTitle,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    minLines = 2
                )
                Text(
                    text = "N/A",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}