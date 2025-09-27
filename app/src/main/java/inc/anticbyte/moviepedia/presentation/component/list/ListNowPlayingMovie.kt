package inc.anticbyte.moviepedia.presentation.component.list

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.domain.model.Movie
import inc.anticbyte.moviepedia.presentation.component.item.ItemMovie

@Composable
fun ListNowPlayingMovies(
    movies: List<Movie>,
    navigateToScreenDetails: (movieId: Int) -> Unit = {},
    @StringRes sectionTitle: Int = R.string.latest_movies,
    navigateToScreenNowPlaying: () -> Unit = {},
) {
    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(sectionTitle),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(movies) { movie ->
                ItemMovie(
                    movie = movie,
                    onMovieClick = { navigateToScreenDetails(movie.movieId) })
            }
            item {
                IconButton(
                    modifier = Modifier.offset(y = 50.dp),
                    onClick = { navigateToScreenNowPlaying() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_forward),
                        contentDescription = null
                    )
                }
            }
        }
    }
}
