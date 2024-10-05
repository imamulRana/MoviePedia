package inc.anticbyte.moviepedia.presentation.screens.search

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.domain.model.MovieWatchList
import inc.anticbyte.moviepedia.presentation.component.common.AppGenresChip
import inc.anticbyte.moviepedia.presentation.screens.ErrorScreen
import inc.anticbyte.moviepedia.presentation.screens.LoadingScreen
import inc.anticbyte.moviepedia.presentation.screens.ShowsViewModel
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter


@OptIn(FlowPreview::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: ShowsViewModel,
    onMovieClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val searchUiState by viewModel.searchUiState.collectAsState()

    val (query, setQuery) = rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(query) {
        snapshotFlow { query }
            .debounce(300)
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .collect { query ->
                viewModel.getMovieBySearch(query)
            }
    }

    Column(
        modifier = Modifier.padding(
            top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding().plus(16.dp)
        )
    ) {
        OutlinedTextField(
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = query,
            onValueChange = { setQuery(it) },
            shape = FilterChipDefaults.shape,
            placeholder = { Text("Search Movies", style = MaterialTheme.typography.bodyMedium) },
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
            }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            colors = OutlinedTextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.onBackground,
                unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(.2f),
                focusedBorderColor = MaterialTheme.colorScheme.onBackground
            ),
            leadingIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back_ios),
                        contentDescription = stringResource(id = R.string.search)
                    )
                }
            },

            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = {
                        setQuery("")
                        viewModel.getMovieBySearch("")
                        focusManager.clearFocus()
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cancel),
                            contentDescription = stringResource(id = R.string.search)
                        )
                    }
                }
            }
        )
        Text(
            modifier = Modifier
                .padding(16.dp)
                .animateContentSize(),
            text = if (query.isEmpty()) "Top Searches" else "Search Results",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall
        )
        if (searchUiState.isLoading) {
            LoadingScreen()
        } else if (searchUiState.error.isNotEmpty()) {
            ErrorScreen()
        } else
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (query.isNotEmpty())
                    items(searchUiState.movies) { search ->
                        ItemSearch(watchList = search, onMovieClick = {
                            viewModel.getMovieDetail(search.movieId)
                            onMovieClick(search.movieId)
                        })
                    } else {
                    items(searchUiState.popularSearch) { popular ->
                        ItemSearch(watchList = popular, onMovieClick = {
                            onMovieClick(popular.movieId)
                        })
                    }
                }
            }
    }
}

@Composable
fun ItemSearch(
    modifier: Modifier = Modifier,
    watchList: MovieWatchList,
    onMovieClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .clip(CardDefaults.shape)
            .clickable(onClick = onMovieClick)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(CardDefaults.shape)
                .height(100.dp)
                .aspectRatio(2 / 3f)
                .background(MaterialTheme.colorScheme.surfaceContainer)
            ,
            model = watchList.moviePosterPath,
//            painter = painterResource(R.drawable.image_frost),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        ) {
            Text(
                text = watchList.movieTitle, style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = watchList.movieReleaseDate,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(.7f)
            )
            AppGenresChip(genres = watchList.movieGenres)
        }
    }
}

@Preview()
@Composable
private fun DefPrev() {
    MoviePediaTheme {
    }
}