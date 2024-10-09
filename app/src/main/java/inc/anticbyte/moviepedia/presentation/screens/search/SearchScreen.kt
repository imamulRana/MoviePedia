package inc.anticbyte.moviepedia.presentation.screens.search

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.presentation.component.item.ItemSearch
import inc.anticbyte.moviepedia.presentation.screens.ErrorScreen
import inc.anticbyte.moviepedia.presentation.screens.LoadingScreen
import inc.anticbyte.moviepedia.presentation.screens.MoviePediaViewModel
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter


@OptIn(FlowPreview::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: MoviePediaViewModel,
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
            LazyColumn(
                modifier = Modifier.animateContentSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                if (query.isNotEmpty())
                    items(searchUiState.movies) { search ->
                        ItemSearch(modifier= Modifier.animateItem(),
                            watchList = search, onMovieClick = {
                            viewModel.getMovieDetail(search.movieId)
                            onMovieClick(search.movieId)
                        })
                    } else {
                    items(searchUiState.popularSearch) { popular ->
                        ItemSearch(modifier= Modifier.animateItem(),watchList = popular, onMovieClick = {
                            onMovieClick(popular.movieId)
                        })
                    }
                }
            }
    }
}


@Preview()
@Composable
private fun DefPrev() {
    MoviePediaTheme {
    }
}