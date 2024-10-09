package inc.anticbyte.moviepedia.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import inc.anticbyte.moviepedia.data.paging.NowPlayingMoviePagingSrc
import inc.anticbyte.moviepedia.data.paging.TrendingMoviePagingSrc
import inc.anticbyte.moviepedia.domain.model.Movie
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import inc.anticbyte.moviepedia.domain.repo.PagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class PagingRepositoryImpl @Inject constructor(
    private val networkRepository: NetworkRepository
) : PagingRepository {
    override fun getTrendingMovies(timeWindow: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { TrendingMoviePagingSrc(networkRepository, timeWindow) }
        ).flow
    }

    override fun getNowPlayingMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { NowPlayingMoviePagingSrc(networkRepository) }
        ).flow
    }
}