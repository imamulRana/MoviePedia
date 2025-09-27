package inc.anticbyte.moviepedia.domain.repo

import androidx.paging.PagingData
import inc.anticbyte.moviepedia.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface PagingRepository {
    fun getTrendingMovies(timeWindow: String): Flow<PagingData<Movie>>
    fun getNowPlayingMovies(): Flow<PagingData<Movie>>
}