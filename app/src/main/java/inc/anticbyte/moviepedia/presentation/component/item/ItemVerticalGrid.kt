package inc.anticbyte.moviepedia.presentation.component.item

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import inc.anticbyte.moviepedia.domain.model.Movie

@Composable
fun ItemVerticalGrid(modifier: Modifier = Modifier, onMovieClick: (Int) -> Unit, movie: Movie) {
    AsyncImage(
        modifier = modifier
            .animateContentSize()
            .aspectRatio(2 / 3f)
            .clip(CardDefaults.shape)
            .clickable(onClick = { onMovieClick(movie.movieId) })
            .background(MaterialTheme.colorScheme.surfaceContainer),
        model = movie.moviePoster,
//        painter = painterResource(R.drawable.image_frost),
        contentDescription = "",
        contentScale = ContentScale.Crop
    )
}

