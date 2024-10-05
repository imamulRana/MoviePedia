package inc.anticbyte.moviepedia.presentation.component.common

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme

@Composable
fun AppFilterChip(
    modifier: Modifier = Modifier,
    chips: List<String>,
    selectedIndex: Int,
    onChipSelected: (Int) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            chips.forEachIndexed { index, chip ->
                CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
                    FilterChip(
                        modifier = Modifier.animateContentSize(animationSpec = spring()),
                        selected = selectedIndex == index,
                        onClick = { onChipSelected(index) },
                        label = { Text(chip) },
                        leadingIcon = {
                            if (selectedIndex == index) Icon(
                                Icons.Default.Check,
                                contentDescription = ""
                            )
                        }, shape = MaterialTheme.shapes.small,
                        colors = FilterChipDefaults.filterChipColors(selectedContainerColor = MaterialTheme.colorScheme.primary,
                            selectedLabelColor = MaterialTheme.colorScheme.background,
                            selectedLeadingIconColor = MaterialTheme.colorScheme.background
                            )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    MoviePediaTheme {
//        AppFilterChip(chips = listOf(""))
    }
}