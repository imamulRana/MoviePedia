package inc.anticbyte.moviepedia.presentation.screens.movieDetail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.presentation.component.common.AppGenresChip
import inc.anticbyte.moviepedia.presentation.component.common.DetailTextSection
import inc.anticbyte.moviepedia.presentation.component.common.MovieDetailMediaSection
import inc.anticbyte.moviepedia.presentation.screens.ErrorScreen
import inc.anticbyte.moviepedia.presentation.screens.LoadingScreen
import inc.anticbyte.moviepedia.presentation.screens.MoviePediaViewModel
import inc.anticbyte.moviepedia.presentation.screens.castDetail.CastDetailScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviePediaViewModel,
    onBackClick: () -> Unit,
    onCastClick: (Int) -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val movieDetailUiState by viewModel.movieDetailUiState.collectAsStateWithLifecycle()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    if (movieDetailUiState.isLoading) {
        LoadingScreen()
    } else if (movieDetailUiState.movieDetail.movieTitle.isNotEmpty()) {
        Scaffold(modifier = Modifier.consumeWindowInsets(WindowInsets.systemBars), snackbarHost = {
            SnackbarHost(hostState = snackBarHostState, snackbar = { Snackbar(snackbarData = it) })
        }) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(innerPadding)
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
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AppIconButton(
                            onClick = {
                                val intent = Intent().apply {
                                    action = Intent.ACTION_VIEW
                                    data =
                                        Uri.parse("https://youtu.be/s3PV9hat814?si=ekJGUcqwXoCTt0jY")
                                    `package` = "com.google.android.youtube"
                                }
                                context.startActivity(intent)
                            },
                            painterResource = R.drawable.ic_play_circle,
                            tooltipText = "Watch trailer",
                        )
                        AppIconButton(
                            onClick = {
                                viewModel.postAddOrRemoveWatchList(
                                    movieDetailUiState.movieDetail.movieId,
                                    !movieDetailUiState.movieDetail.isMovieInWatchList,
                                    onSuccess = {
                                        scope.launch {
                                            snackBarHostState.showSnackbar(
                                                message = if (movieDetailUiState.movieDetail.isMovieInWatchList) "Added to watchlist"
                                                else "Removed from watchlist"
                                            )
                                        }
                                    }
                                )
                            },
                            painterResource =
                            if (movieDetailUiState.movieDetail.isMovieInWatchList) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark_outline,
                            tooltipText = "Add to watchlist",
                        )
                        AppIconButton(
                            onClick = {
                                val intent = Intent()
                                    .apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(
                                            Intent.EXTRA_TEXT,
                                            movieDetailUiState.movieDetail.movieTitle
                                        )
                                        type = "text/plain"
                                    }
                                context.startActivity(intent)
                            },
                            painterResource = R.drawable.ic_share,
                            tooltipText = "Share",
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        AppIconButton(
                            onClick = {
                                scope.launch {
                                    snackBarHostState.showSnackbar(
                                        message = "Coming Soon!"
                                    )
                                }
                            },
                            painterResource = R.drawable.ic_favorite,
                            tooltipText = "Add to favorites",
                        )
                    }
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
                    DetailTextSection(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        sectionTitle = "production companies"
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = movieDetailUiState.movieDetail.productionCompany.joinToString(
                                ", "
                            ),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground.copy(.7f),
                            textAlign = TextAlign.Justify
                        )
                    }
                    DetailTextSection(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        sectionTitle = "keywords",
                        sectionContent = {
                            AppGenresChip(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                genres = movieDetailUiState.movieDetail.movieKeywords
                            )
                        })
                    DetailTextSection(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        sectionTitle = "cast",
                        sectionContent = {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                contentPadding = PaddingValues(horizontal = 8.dp)
                            ) {
                                items(movieDetailUiState.movieDetail.movieCasts) {
                                    ItemCastsCard(cast = it, onCastClick = { castId ->
                                        onCastClick(castId)
                                        scope.launch {
                                            bottomSheetState.expand()
                                        }.invokeOnCompletion {
                                            isBottomSheetVisible = true
                                        }
                                    })
                                }
                            }
                        })
                }
            }
        }
    } else {
        ErrorScreen(exception = Exception(movieDetailUiState.error))
    }
    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { isBottomSheetVisible = false },
            sheetState = bottomSheetState,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            CastDetailScreen(
                modifier = Modifier,
                viewModel = viewModel,
                onMovieClick = {
                    viewModel.getMovieDetail(it)
                    scope.launch {
                        bottomSheetState.hide()
                        scrollState.animateScrollTo(0)
                    }.invokeOnCompletion {
                        isBottomSheetVisible = false
                    }
                }
            )
        }
    }
}