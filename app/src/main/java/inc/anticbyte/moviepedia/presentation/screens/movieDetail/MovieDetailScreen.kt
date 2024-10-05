package inc.anticbyte.moviepedia.presentation.screens.movieDetail

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.presentation.component.common.AppGenresChip
import inc.anticbyte.moviepedia.presentation.component.common.DetailTextSection
import inc.anticbyte.moviepedia.presentation.component.common.MovieDetailMediaSection
import inc.anticbyte.moviepedia.presentation.screens.ErrorScreen
import inc.anticbyte.moviepedia.presentation.screens.LoadingScreen
import inc.anticbyte.moviepedia.presentation.screens.ShowsViewModel

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: ShowsViewModel,
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    val movieDetailUiState by viewModel.movieDetailUiState.collectAsState()

    if (movieDetailUiState.isLoading) {
        LoadingScreen()
    } else if (movieDetailUiState.movieDetail.movieTitle.isNotEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
        ) {
            MovieDetailMediaSection(
                movieBackdropUrl = movieDetailUiState.movieDetail.movieBackdropUrl,
                moviePosterUrl = movieDetailUiState.movieDetail.moviePosterUrl,
                movieTitle = movieDetailUiState.movieDetail.movieTitle,
                movieVoteAverage = movieDetailUiState.movieDetail.movieVoteAverage,
                movieVoteCount = movieDetailUiState.movieDetail.movieVoteCount,
                movieReleaseDate = movieDetailUiState.movieDetail.movieReleaseDate,
                movieGenres = movieDetailUiState.movieDetail.movieGenres,
                onBackClick = onBackClick,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DetailTextSection(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    sectionTitle = "Overview", sectionContent = {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = movieDetailUiState.movieDetail.movieOverview,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(.7f),
                            textAlign = TextAlign.Justify
                        )
                    })
                DetailTextSection(modifier = Modifier.padding(horizontal = 16.dp),sectionTitle = "cast", sectionContent = {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        items(movieDetailUiState.movieDetail.movieCasts) {
                            ItemCastsCard(cast = it)
                        }
                    }
                })
                DetailTextSection(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    sectionTitle = "keywords",
                    sectionContent = {
                        AppGenresChip(modifier = Modifier.padding(horizontal = 16.dp), genres = movieDetailUiState.movieDetail.movieKeywords)
                    })
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                onClick = {},
                shape = SnackbarDefaults.shape,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(text = "Add to watchlist", style = MaterialTheme.typography.titleSmall)
            }
        }
    } else {
        ErrorScreen(exception = Exception(movieDetailUiState.error))
    }
}