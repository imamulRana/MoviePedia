package inc.anticbyte.moviepedia.presentation.screens.castDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.presentation.component.common.DetailTextSection
import inc.anticbyte.moviepedia.presentation.component.list.ListPopularMovie
import inc.anticbyte.moviepedia.presentation.screens.MoviePediaViewModel
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import inc.anticbyte.moviepedia.utils.calculateAge

@Composable
fun CastDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviePediaViewModel,
    onMovieClick: (Int) -> Unit
) {
    val castDetailUiState by viewModel.castDetailUiState.collectAsStateWithLifecycle()
    if (castDetailUiState.isLoading) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.surfaceContainer),
                model = castDetailUiState.castDetail.personImage,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = castDetailUiState.castDetail.personName,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    DetailTextSection(sectionTitle = "Age") {
                        Text(
                            text = castDetailUiState.castDetail.personAge + calculateAge(
                                castDetailUiState.castDetail.personAge
                            ),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(.7f)
                        )
                    }

                    DetailTextSection(sectionTitle = "Gender") {
                        Text(
                            text = castDetailUiState.castDetail.personGender,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(.7f)
                        )
                    }
                }
            }
            ListPopularMovie(
                sectionTitle = R.string.known_for,
                movies = castDetailUiState.castDetail.movieCredit,
                navigateToScreenDetails = { onMovieClick(it) })
        }
}


@Preview
@Composable
private fun DefPrev() {
    MoviePediaTheme {
//        CastDetailScreen()
    }
}