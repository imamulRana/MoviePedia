package inc.anticbyte.moviepedia.presentation.component

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun MovieImage(
    modifier: Modifier = Modifier, imageUrl: String,
    contentScale: ContentScale = ContentScale.Crop
) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(imageUrl) {
        bitmap = loadImageFromUrl(imageUrl)
    }

    bitmap?.let {
        Image(
            modifier = modifier,
            bitmap = it.asImageBitmap(),
            contentDescription = null,
            contentScale = contentScale
        )
    }
}

suspend fun loadImageFromUrl(imageUrl: String): Bitmap? {
    val client = HttpClient(CIO)

    return try {
        // Fetch the image as an HttpResponse
        val response: HttpResponse = client.get(imageUrl)

        // Read the response content as a ByteArray
        val byteArray = response.readBytes()

        // Convert the byte array to a Bitmap on the IO thread
        withContext(Dispatchers.IO) {
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    } finally {
        client.close()
    }
}
