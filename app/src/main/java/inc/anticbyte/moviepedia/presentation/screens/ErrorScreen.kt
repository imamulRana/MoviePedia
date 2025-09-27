package inc.anticbyte.moviepedia.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import inc.anticbyte.moviepedia.R
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    exception: Exception = Exception(),
    onRetry: () -> Unit = {}
) {
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        val result = snackBarHostState.showSnackbar(
            message = exception.message ?: "Something went wrong",
            actionLabel = "Retry",
        )
        if (result == SnackbarResult.ActionPerformed) {
            onRetry()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackBarHostState, snackbar = { Snackbar(snackbarData = it) })
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = modifier
                        .size(300.dp),
                    painter = painterResource(id = R.drawable.bg_no_data),
                    contentDescription = null
                )
                Text(
                    text = exception.message ?: "Something went wrong",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    MoviePediaTheme {
        ErrorScreen()
    }
}