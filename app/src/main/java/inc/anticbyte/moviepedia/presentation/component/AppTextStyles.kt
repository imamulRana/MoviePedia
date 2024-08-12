package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CarouselCardText(
    modifier: Modifier = Modifier,
    movieName: String,
    movieYear: String
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = movieName, style = MaterialTheme.typography.titleLarge, color = Color.White)
        Text(text = movieYear, style = MaterialTheme.typography.titleSmall, color = Color.White)
    }
}