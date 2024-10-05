package inc.anticbyte.moviepedia.domain.usecase

import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.model.MovieWatchList
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTopSearchMovieUseCase @Inject constructor(
    private val repository: NetworkRepository,
    @IoDispatcher private val io: CoroutineDispatcher
) {
    suspend operator fun invoke(onLoading: () -> Unit): Result<List<MovieWatchList>> {
        return withContext(io) {
            runCatching {
                onLoading()
                val response =
                    repository.getPopularMovies().results?.map { it.toTopSearchMovie() }.orEmpty()
                if (response.isEmpty()) {
                    Result.failure(Exception("Something went wrong"))
                } else {
                    Result.success(response.shuffled())
                }
            }.getOrElse {
                Result.failure(it)
            }
        }
    }
}
