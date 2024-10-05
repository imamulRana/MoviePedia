package inc.anticbyte.moviepedia.presentation.component.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScrollableTab(
    modifier: Modifier = Modifier,
    tabItems: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
) {
    PrimaryScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex,
        tabs = {
            tabItems.forEachIndexed { index, tabItem ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    text = { Text(text = tabItem) })
            }
        }, divider = { })
}