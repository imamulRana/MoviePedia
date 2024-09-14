package inc.anticbyte.moviepedia.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.presentation.component.SavedShowItem
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme

@Composable
fun SavedShowsScreen(modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(10) {
            SavedShowItem(modifier = Modifier.fillMaxWidth(), navigateToShowDetails = {})
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    MoviePediaTheme {
        SavedShowsScreen()
    }
}