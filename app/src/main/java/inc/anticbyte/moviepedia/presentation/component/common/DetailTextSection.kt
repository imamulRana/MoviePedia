package inc.anticbyte.moviepedia.presentation.component.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailTextSection(
    modifier: Modifier = Modifier,
    sectionTitle: String,
    sectionContent: @Composable () -> Unit
) {
    Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            modifier = modifier,
            text = sectionTitle.uppercase(), style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        sectionContent()
    }
}