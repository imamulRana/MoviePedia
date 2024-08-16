package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.data.remote.ShowDto

@Composable
fun ShowsList(modifier: Modifier = Modifier, shows: List<ShowDto>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(shows.size) {
            ShowsListCard(
                show = shows[it]
            )
        }
    }
}