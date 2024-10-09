package inc.anticbyte.moviepedia.presentation.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import inc.anticbyte.moviepedia.presentation.theme.MoviePediaTheme

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, buttonModifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Welcome To MoviePedia", style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Sign In to Experience An Entirely Wonderful Movie WikiPedia You Can Also Continue Without Sign In",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Column(
            modifier = Modifier
                .then(buttonModifier)
                .width(IntrinsicSize.Min)
                .align(Alignment.BottomCenter)
        ) {
            Button(modifier = buttonModifier, onClick = {}, shape = CardDefaults.shape) {
                Text(
                    text = "Continue with E-Mail",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier.height(IntrinsicSize.Max),
                contentAlignment = Alignment.Center
            ) {
                HorizontalDivider()
                Text(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(4.dp),
                    text = "OR",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            OutlinedButton(modifier = buttonModifier, onClick = {}, shape = CardDefaults.shape) {
                Text(
                    text = "Continue without registration",
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}


@Preview
@Composable
private fun DefPrev() {
    MoviePediaTheme {
        OnboardingScreen(
            modifier = Modifier.padding(16.dp),
            buttonModifier = Modifier.fillMaxWidth()
        )
    }
}