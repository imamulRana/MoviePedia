package inc.anticbyte.moviepedia.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.R

@Composable
fun ErrorScreen(modifier: Modifier = Modifier, exception: Exception = Exception()) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = modifier.align(Alignment.Center)
        ) {

            Image(
                modifier = modifier
                    .size(200.dp),
                painter = painterResource(id = R.drawable.error_robot),
                contentDescription = null
            )
            Text(
                text = exception.message ?: "Something went wrong",
            )
        }
        Button(
            modifier = modifier.align(Alignment.BottomCenter),
            onClick = {},
            shape = SnackbarDefaults.shape
        ) {
            Text(text = stringResource(id = R.string.btn_retry).uppercase())
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefPrev() {
//    ErrorScreen()
}