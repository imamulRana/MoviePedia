package inc.anticbyte.moviepedia.presentation.screens.movieDetail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @DrawableRes painterResource: Int,
    tooltipText: String,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val state = rememberTooltipState()
    TooltipBox(
        positionProvider = TooltipDefaults.rememberTooltipPositionProvider(),
        state = state,
        tooltip = {
            PlainTooltip(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) { Text(text = tooltipText) }
        }) {
        FilledIconButton(
            onClick = {
                onClick()
            }, modifier = modifier, shape = CardDefaults.shape,
            colors = IconButtonDefaults.filledIconButtonColors(Color.Transparent),
            interactionSource = interactionSource
        ) {
            Icon(painter = painterResource(painterResource), contentDescription = tooltipText)
        }
    }

}

