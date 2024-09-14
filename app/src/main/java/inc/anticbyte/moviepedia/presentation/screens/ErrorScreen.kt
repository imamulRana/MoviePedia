package inc.anticbyte.moviepedia.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = modifier.fillMaxSize(.7f),
            painter = painterResource(id = R.drawable.error_robot),
            contentDescription = null
        )
        Spacer(modifier = modifier.height(16.dp))
        Button(onClick = {}, shape = SnackbarDefaults.shape)
        { Text(text = stringResource(id = R.string.btn_retry).uppercase()) }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefPrev() {
    ErrorScreen()
}