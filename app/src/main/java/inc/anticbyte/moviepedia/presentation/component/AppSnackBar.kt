package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppSnackBar(modifier: Modifier = Modifier, snackBarData: String) {
    val scope = rememberCoroutineScope()
    Snackbar(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        shape = CircleShape
    )
    {
        Text(snackBarData, color = Color.Blue)
    }
}