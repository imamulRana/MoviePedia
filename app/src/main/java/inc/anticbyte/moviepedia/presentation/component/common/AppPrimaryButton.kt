package inc.anticbyte.moviepedia.presentation.component.common

import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.R

@Composable
fun AppPrimaryButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {},
    @StringRes primaryButtonText: Int = R.string.add_to_watchlist,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = onButtonClick,
        shape = CardDefaults.shape,
        colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.background)
    ) {
        Text(text = stringResource(primaryButtonText), style = MaterialTheme.typography.titleSmall)
    }
}

@Composable
fun AppSecondaryButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit = {},
    @StringRes secondaryButtonText: Int = R.string.remove_from_watchlist,
    interactionSource: MutableInteractionSource
) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = onButtonClick,
        shape = CardDefaults.shape,
        interactionSource = interactionSource,
    ) {
        Text(
            text = stringResource(secondaryButtonText),
            style = MaterialTheme.typography.titleSmall,
        )
    }
}