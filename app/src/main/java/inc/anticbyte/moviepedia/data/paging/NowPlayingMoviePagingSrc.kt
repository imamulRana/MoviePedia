package inc.anticbyte.moviepedia.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import inc.anticbyte.moviepedia.domain.model.Movie
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository


class NowPlayingMoviePagingSrc(
    private val networkRepository: NetworkRepository
) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return runCatching {
            val page = params.key ?: 1
            val response = networkRepository.getNowPlayingMovies(page = page)
                .results?.map { it.toMovie() }.orEmpty()
            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        }.getOrElse {
            LoadResult.Error(it)
        }
    }
}