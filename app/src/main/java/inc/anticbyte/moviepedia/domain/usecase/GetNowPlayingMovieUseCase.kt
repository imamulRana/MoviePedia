package inc.anticbyte.moviepedia.domain.usecase

import androidx.paging.PagingData
import inc.anticbyte.moviepedia.domain.model.Movie
import inc.anticbyte.moviepedia.domain.repo.PagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNowPlayingMovieUseCase @Inject constructor(
    private val pagingRepository: PagingRepository,
) {
    operator fun invoke(onLoading: () -> Unit): Flow<PagingData<Movie>> {
        onLoading()
        return pagingRepository.getNowPlayingMovies()
    }
}