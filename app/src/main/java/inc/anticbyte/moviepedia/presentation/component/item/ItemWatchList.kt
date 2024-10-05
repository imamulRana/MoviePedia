package inc.anticbyte.moviepedia.presentation.component.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import inc.anticbyte.moviepedia.domain.model.MovieWatchList
import inc.anticbyte.moviepedia.presentation.component.common.AppGenresChip
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import inc.anticbyte.moviepedia.utils.toDate

@Composable
fun ItemWatchList(
    modifier: Modifier = Modifier,
    movieWatchList: MovieWatchList,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .clip(CardDefaults.shape)
            .clickable(onClick = onClick)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
//            painter = painterResource(R.drawable.image_frost),
            model = movieWatchList.moviePosterPath,
            contentDescription = "",
            modifier = modifier
                .clip(CardDefaults.shape)
                .height(150.dp)
                .aspectRatio(2 / 3f),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.height(IntrinsicSize.Min),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .requiredHeight(28.dp)
                        .wrapContentSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(28.dp)
                            .align(Alignment.Center),
                        progress = { movieWatchList.movieVoteAverage.toFloat() / 10 },
                        strokeWidth = 2.dp,
                        trackColor = MaterialTheme.colorScheme.onBackground.copy(.2f),
                    )
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = movieWatchList.movieVoteAverage.toString(),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Text(
                    text = movieWatchList.movieTitle, style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Text(
                text = toDate(movieWatchList.movieReleaseDate),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground.copy(.7f)
            )
            AppGenresChip(genres = movieWatchList.movieGenres)
        }
    }
}

class WatchListProvider : PreviewParameterProvider<MovieWatchList> {
    override val values = sequenceOf(
        MovieWatchList(
            1, "Venom",
            moviePosterPath = "https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg",
            movieOverview = LoremIpsum(30).values.joinToString(),
            movieReleaseDate = "20 June 2023",
            movieVoteAverage = 7.1
        ),
        MovieWatchList(
            1, "Suppu",
            LoremIpsum(20).values.joinToString(),
        )
    )
}


@Preview(showBackground = true)
@Composable
private fun DefPrev(
    @PreviewParameter(provider = WatchListProvider::class, limit = 2) provider: MovieWatchList
) {
    MoviePediaTheme {
        ItemWatchList(movieWatchList = provider)
    }
}