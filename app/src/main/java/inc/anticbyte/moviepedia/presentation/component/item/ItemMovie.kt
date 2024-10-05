package inc.anticbyte.moviepedia.presentation.component.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import inc.anticbyte.moviepedia.domain.model.Movie
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme

@Composable
fun ItemMovie(movie: Movie, onMovieClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .width(100.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(shape = CardDefaults.shape)
                .clickable { onMovieClick() }
                .height(150.dp)
                .fillMaxWidth(),
            model = movie.moviePoster,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .fillMaxWidth(),
            text = movie.movieTitle,
            style = MaterialTheme.typography.bodySmall,
            minLines = 2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground.copy(.7f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    MoviePediaTheme {
        ItemMovie(movie = Movie())
    }
}