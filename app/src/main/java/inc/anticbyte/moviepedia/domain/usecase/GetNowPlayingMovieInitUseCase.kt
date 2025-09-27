package inc.anticbyte.moviepedia.domain.usecase

import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.model.Movie
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNowPlayingMovieInitUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
    @IoDispatcher private val io: CoroutineDispatcher
) {
    suspend operator fun invoke(onLoading: () -> Unit): Result<List<Movie>> {
        return withContext(io) {
            runCatching {
//                onLoading()
                networkRepository.getNowPlayingMovies(page = 1).results?.map { it.toMovie() }
                    .orEmpty()
            }
        }
    }
}