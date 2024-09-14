package inc.anticbyte.moviepedia.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.data.remote.ShowDto
import inc.anticbyte.moviepedia.navigation.MoviePediaScreens
import inc.anticbyte.moviepedia.presentation.component.AppCarousel
import inc.anticbyte.moviepedia.presentation.component.ShowList
import inc.anticbyte.moviepedia.utils.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ShowsViewModel,
    navigateToDetailScreen: (MoviePediaScreens.ShowDetailScreen) -> Unit,
) {
    val show by viewModel.allShow.collectAsState()
    val showData = (show as RequestState.Success<List<ShowDto>>)
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item { AppCarousel(showDto = showData.data.take(5)) }
        item {
            ShowList(
                showList = showData.data,
                navigateToDetails = { data ->
                    navigateToDetailScreen(data)
                })
        }
    }
}
