package inc.anticbyte.moviepedia.data.repo

import androidx.paging.PagingData
import inc.anticbyte.moviepedia.data.remote.dto.MovieSearchDto
import inc.anticbyte.moviepedia.data.remote.dto.WatchListMovieDto
import inc.anticbyte.moviepedia.data.remote.movie.MovieCreditDto
import inc.anticbyte.moviepedia.data.remote.movie.MovieDetailDto
import inc.anticbyte.moviepedia.data.remote.movie.MovieKeywordDto
import inc.anticbyte.moviepedia.data.remote.nowPlaying.NowPlayingMovieDto
import inc.anticbyte.moviepedia.data.remote.trending.MovieDto
import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.model.MovieCast
import inc.anticbyte.moviepedia.domain.model.MovieKeyWord
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import inc.anticbyte.moviepedia.utils.app_base_url
import inc.anticbyte.moviepedia.utils.now_playing_movie
import inc.anticbyte.moviepedia.utils.popular_movie
import inc.anticbyte.moviepedia.utils.trending_movie
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implemented Interface of [NetworkRepository]
 */
class NetworkRepositoryImpl @Inject constructor(
    @IoDispatcher private val io: CoroutineDispatcher,
    private val ktorClient: HttpClient
) : NetworkRepository {

    override suspend fun getTrendingMovies(timeWindow: String, page:Int): MovieDto {
        return withContext(io) {
            try {
                val response = ktorClient.get(trending_movie) {
                    parameter("page", page)
                    url.appendPathSegments(timeWindow); parameter("language", "en-US")
                }
                if (response.status.isSuccess()) {
                    response.body()
                } else {
                    throw Exception(response.status.description)
                }
            } catch (exp: Exception) {
                throw Exception(exp.localizedMessage)
            }
        }
    }

    override suspend fun getMovieDetails(movieId: String): MovieDetailDto {
        return withContext(io) {
            try {
                val response = ktorClient.get("movie") {
                    url.appendPathSegments(movieId); parameter("language", "en-US")
                }
                if (response.status.isSuccess()) {
                    response.body()
                } else {
                    throw Exception("API Error")
                }
            } catch (exp: Exception) {
                throw Exception(exp.localizedMessage)
            }
        }
    }

    override suspend fun getPopularMovies(): MovieDto {
        return withContext(io) {
            try {
                val response = ktorClient.get(popular_movie) {
                    parameter("language", "en-US")
                    parameter("page", 1)
                }
                if (response.status.isSuccess()) {
                    response.body<MovieDto>()
                } else {
                    throw Exception(response.status.description)
                }
            } catch (exp: Exception) {
                throw Exception(exp.localizedMessage)
            }
        }
    }

    override suspend fun getNowPlayingMovies(page: Int): NowPlayingMovieDto {
        return withContext(io) {
            try {
                val response = ktorClient.get(now_playing_movie) {
                    parameter("language", "en-US")
                    parameter("page", page)
                }
                if (response.status.isSuccess()) {
                    response.body()
                } else {
                    throw Exception(response.status.description)
                }
            } catch (exp: Exception) {
                throw Exception(exp.localizedMessage)
            }
        }
    }

    override suspend fun getWatchListMovies(): WatchListMovieDto {
        return withContext(io) {
            try {
                val response = ktorClient.get(app_base_url) {
                    url.appendPathSegments("account", "21418184", "watchlist", "movies")
                    parameter("language", "en-US")
                }
                if (response.status.isSuccess()) {
                    response.body()
                } else {
                    throw Exception(response.status.description)
                }
            } catch (exp: Exception) {
                throw Exception(exp.localizedMessage)
            }
        }
    }

    override suspend fun getMovieKeyWords(movieId: String): Result<List<MovieKeyWord>> {
        return withContext(io) {
            runCatching {
                val networkResponse =
                    ktorClient.get("movie") { url.appendPathSegments(movieId, "keywords") }
                val response = networkResponse.body<MovieKeywordDto>()
                if (networkResponse.status.isSuccess()) {
                    response.keywords?.map { it.toMovieKeyWord() }.orEmpty()
                } else {
                    throw Exception("Something went wrong")
                }
            }
            /*try {
                val networkResponse =
                    ktorClient.get("movie") { url.appendPathSegments(movieId, "keywords") }
                val response = networkResponse.body<MovieKeywordDto>()
                if (networkResponse.status.isSuccess()) {
                    response.keywords?.map { it.toMovieKeyWord() }.orEmpty()
                } else {
                    throw Exception("Something went wrong")
                }
            } catch (exp: Exception) {
                throw Exception(exp.localizedMessage)
            }*/
        }
    }

    override suspend fun getMovieCredit(movieId: String): Result<List<MovieCast>> {
        return withContext(io) {
            runCatching {
                val networkResponse =
                    ktorClient.get("movie") { url.appendPathSegments(movieId, "credits") }
                val response = networkResponse.body<MovieCreditDto>()
                if (networkResponse.status.isSuccess()) {
                    response.cast?.map { it.toMovieCast() }.orEmpty()
                } else {
                    throw Exception("Something went wrong")
                }
            }
            /* try {
                 val networkResponse =
                     ktorClient.get("movie") { url.appendPathSegments(movieId, "credits") }
                 val response = networkResponse.body<MovieCreditDto>()
                 if (networkResponse.status.isSuccess()) {
                     response.cast?.map { it.toMovieCast() }.orEmpty()
                 } else {
                     throw Exception("Something went wrong")
                 }
             } catch (exp: Exception) {
                 throw Exception(exp.localizedMessage)
             }*/
        }
    }

    override suspend fun getMovieBySearch(query: String): MovieSearchDto {
        return withContext(io) {
            runCatching {
                val response = ktorClient.get {
                    url.appendPathSegments("search", "movie")
                    parameter("query", query)
                }
                if (response.status.isSuccess()) {
                    response.body<MovieSearchDto>()
                } else {
                    throw Exception(response.status.description)
                }
            }.getOrElse {
                throw Exception(it.localizedMessage)
            }
        }
    }
}