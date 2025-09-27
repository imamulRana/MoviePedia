package inc.anticbyte.moviepedia.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomScaffoldTest(modifier: Modifier = Modifier) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val state = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize()
                    .background(Color.Gray),
            ) {
                Button(onClick = {
                    scope.launch {
                        if (state.bottomSheetState.isVisible) {
                            state.bottomSheetState.hide()
                        } else
                            state.bottomSheetState.show()
                    }
                }) {
                    Text(text = "Hello")
                }
            }

        },
        scaffoldState = state,
        sheetPeekHeight = 128.dp,
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Button(
                onClick = {
                    scope.launch {
                        state.bottomSheetState.show()
                    }
                }
            ) { }
            Text(text = "Content")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalSheetTest(modifier: Modifier = Modifier) {
    val sheetState = rememberModalBottomSheetState()
    var isExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        OutlinedButton(onClick = { isExpanded = true }) {
            Text(text = "Open Sheet")
        }
    }
    ModalBottomSheet(
        onDismissRequest = {
            isExpanded = false
        },
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
//                    .padding(top = 16.dp)
                .fillMaxSize()
//                    .background(Color.Gray)
        ) {
            Button(onClick = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        isExpanded = false
                    }
                }
            }) {
                Text(text = "Hello")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    MoviePediaTheme {
//    BottomScaffoldTest()
        ModalSheetTest()
    }
}