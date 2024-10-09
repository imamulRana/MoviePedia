package inc.anticbyte.moviepedia.domain.repo

import inc.anticbyte.moviepedia.data.remote.dto.MovieSearchDto
import inc.anticbyte.moviepedia.data.remote.dto.WatchListMovieDto
import inc.anticbyte.moviepedia.data.remote.movie.MovieDetailDto
import inc.anticbyte.moviepedia.data.remote.nowPlaying.NowPlayingMovieDto
import inc.anticbyte.moviepedia.data.remote.trending.MovieDto
import inc.anticbyte.moviepedia.domain.model.MovieCast
import inc.anticbyte.moviepedia.domain.model.MovieKeyWord


interface NetworkRepository {
    suspend fun getTrendingMovies(timeWindow: String, page: Int): MovieDto
    suspend fun getPopularMovies(): MovieDto
    suspend fun getNowPlayingMovies(page: Int): NowPlayingMovieDto
    suspend fun getWatchListMovies(): WatchListMovieDto
    suspend fun getMovieDetails(movieId: String): MovieDetailDto
    suspend fun getMovieKeyWords(movieId: String): Result<List<MovieKeyWord>>
    suspend fun getMovieCredit(movieId: String): Result<List<MovieCast>>
    suspend fun getMovieBySearch(query: String): MovieSearchDto
}