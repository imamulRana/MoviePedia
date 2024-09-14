package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme

@Composable
fun SavedShowItem(modifier: Modifier = Modifier, navigateToShowDetails: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(SnackbarDefaults.shape)
            .clickable { navigateToShowDetails() }
        ,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            modifier = Modifier
                .clip(SnackbarDefaults.shape)
                .size(height = 150.dp, width = 100.dp),
            painter = painterResource(R.drawable.image_superman),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(top = 4.dp)
                .weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "SuperMan",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = "2014", style = MaterialTheme.typography.bodySmall)
                Text(text = "Action, Drama, Combat", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    MoviePediaTheme {
        SavedShowItem(navigateToShowDetails = {})
    }
}