package inc.anticbyte.moviepedia.presentation.component.common

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import inc.anticbyte.moviepedia.R

@Composable
fun AppPrimaryButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {},
    @StringRes primaryButtonText: Int = R.string.add_to_watchlist,
) {
    Button(modifier = modifier, onClick = onButtonClick, shape = SnackbarDefaults.shape, colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.background)) {
        Text(text = stringResource(primaryButtonText))
    }
}