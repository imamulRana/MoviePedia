package inc.anticbyte.moviepedia.presentation.component.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import inc.anticbyte.moviepedia.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailMediaSection(
    colorsGradient: List<Color> = listOf(Color.Transparent, MaterialTheme.colorScheme.background),
    movieBackdropUrl: String,
    moviePosterUrl: String,
    movieTitle: String,
    movieVoteAverage: Double,
    movieVoteCount: Int,
    movieReleaseDate: String,
    movieGenres: List<String>,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        TopAppBar(modifier = Modifier.zIndex(1f), title = {}, navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back_ios),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent))
        AsyncImage(
            //backdrop
            model = movieBackdropUrl,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .drawWithContent {
                    drawContent()
                    drawRect(
                        Brush.verticalGradient(
                            colors = colorsGradient
                        )
                    )
                },
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            AsyncImage(
                model = moviePosterUrl,
                contentDescription = "",
                modifier = Modifier
                    .height(150.dp)
                    .width(100.dp)
                    .align(Alignment.Bottom)
                    .background(MaterialTheme.colorScheme.surfaceContainer),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.Bottom),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = movieTitle,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(R.drawable.ic_star),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = movieVoteAverage.toString().take(3),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "(${movieVoteCount})",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground.copy(.7f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = movieReleaseDate.take(4),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                AppGenresChip(genres = movieGenres)
            }
        }
    }
}