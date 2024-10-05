package inc.anticbyte.moviepedia.domain.usecase

import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.model.Movie
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import inc.anticbyte.moviepedia.utils.RequestState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPopularMovieUseCase @Inject constructor(
    private val repository: NetworkRepository,
    @IoDispatcher private val io: CoroutineDispatcher
) {
    suspend operator fun invoke(): RequestState<List<Movie>> {
        return withContext(io) {
            runCatching {
                RequestState.Loading
                val response = repository.getPopularMovies().results?.map { it.toMovie() }.orEmpty()
                if (response.isEmpty()) {
                    RequestState.Error("Something went wrong")
                } else {
                    RequestState.Success(response.shuffled())
                }
            }.getOrElse {
                RequestState.Error(it.localizedMessage ?: "Something went wrong")
            }
        }
    }
}