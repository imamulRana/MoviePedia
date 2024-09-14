package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import inc.anticbyte.moviepedia.data.remote.ShowDto

@Composable
fun ShowListItem(
    modifier: Modifier = Modifier, show: ShowDto, onShowClick: () -> Unit
) {
    Column(
        modifier = modifier
            .requiredWidthIn(70.dp, 100.dp)
            .wrapContentHeight()
            .clip(CardDefaults.shape)
            .background(Color.Transparent)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .requiredHeightIn(105.dp, 150.dp)
                .clip(CardDefaults.shape)
                .clickable { onShowClick() },
            model = show.image?.medium,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = show.name ?: "",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
