package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.R

@Composable
fun AppSnackBar(modifier: Modifier = Modifier, snackbarData: SnackbarData) {
    Snackbar(modifier = modifier.padding(8.dp), shape = SnackbarDefaults.shape) {
        Text(text = stringResource(id = R.string.error_message_ui))
    }
}