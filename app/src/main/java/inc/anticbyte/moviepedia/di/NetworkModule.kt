package inc.anticbyte.moviepedia.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import inc.anticbyte.moviepedia.utils.access_token
import inc.anticbyte.moviepedia.utils.app_base_url
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.io.File
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideKtorClient(
        @ApplicationContext context: Context
    ): HttpClient {
        return HttpClient(CIO) {
            defaultRequest {
                url(app_base_url)
                header(HttpHeaders.Authorization, "Bearer $access_token")
            }
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.BODY
            }
            install(ContentNegotiation) {
                json(json = Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(HttpCache) {
                publicStorage(FileStorage(File(context.cacheDir, "http_cache")))
            }
        }
    }
}