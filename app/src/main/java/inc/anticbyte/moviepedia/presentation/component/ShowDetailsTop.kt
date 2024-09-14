package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.R

@Composable
fun ShowDetailsTop(
    modifier: Modifier = Modifier,
    showName: String = "",
    showGenres: String = "",
    showYear: String = "",
    showRating: String = ""
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = showName, style = MaterialTheme.typography.headlineLarge)
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Image(painter = painterResource(id = R.drawable.star_24px), contentDescription = "")
            Text(text = showRating.plus("/10"))
            Text(text = "Overall rating", color = MaterialTheme.colorScheme.onSurface.copy(.7f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = showYear, modifier = Modifier
                    .clip(SnackbarDefaults.shape)
                    .clickable { }
                    .background(Color(0xff2b2f37))
                    .padding(4.dp),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = showGenres, modifier = Modifier
                    .clip(SnackbarDefaults.shape)
                    .clickable { }
                    .background(Color(0xff2b2f37))
                    .padding(4.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}