package inc.anticbyte.moviepedia.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.data.remote.ShowDto
import inc.anticbyte.moviepedia.navigation.MoviePediaScreens

@Composable
fun ShowList(
    modifier: Modifier = Modifier,
    showList: List<ShowDto>,
    navigateToDetails: (MoviePediaScreens.ShowDetailScreen) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(modifier = Modifier.padding(start = 16.dp), text = "Shows")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(showList.size) { show ->
                ShowListItem(
                    show = showList[show],
                    onShowClick = {
                        navigateToDetails(
                            MoviePediaScreens.ShowDetailScreen(
                                showId = showList[show].id ?: 0,
                                showName = showList[show].name ?: "",
                                showImage = showList[show].image?.original ?: "",
                                showDescription = showList[show].summary ?: "",
                                showGenre = showList[show].genres?.joinToString(separator = ",")
                                    ?: "",
                                showYear = showList[show].premiered?.take(5) ?: "",
                                showRating = showList[show].rating?.average?.toString() ?: ""
                            )
                        )
                    }
                )
                Log.d("ClickedShow", "ShowsList: $show,${showList[show].id}")
            }
        }
    }
}