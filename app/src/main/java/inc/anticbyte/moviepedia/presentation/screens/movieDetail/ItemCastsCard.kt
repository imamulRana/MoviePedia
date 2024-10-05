package inc.anticbyte.moviepedia.presentation.screens.movieDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.domain.model.MovieCast
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ItemCastsCard(cast: MovieCast) {
    Column(
        modifier = Modifier
            .clip(CardDefaults.shape)
            .clickable { }
            .size(100.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(45.dp)
                .background(MaterialTheme.colorScheme.surfaceContainer),
//                painter = painterResource(R.drawable.image_frost),
            model = cast.castProfileUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text(
            text = cast.castName,
            maxLines = 2,
            minLines = 2,
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )

    }
}


@Preview
@Composable
private fun DefPrev() {
    MoviePediaTheme {
        ItemCastsCard(cast = MovieCast(0, "name", "profile_path", "character"))
    }
}