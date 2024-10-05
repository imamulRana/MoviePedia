package inc.anticbyte.moviepedia.domain.usecase

import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.model.MovieWatchList
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import inc.anticbyte.moviepedia.utils.RequestState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieWatchListUseCase @Inject constructor(
    private val repository: NetworkRepository,
    @IoDispatcher private val io: CoroutineDispatcher
) {
    suspend operator fun invoke(): RequestState<List<MovieWatchList>> {
        return withContext(io) {
            try {
                RequestState.Loading
                val response =
                    repository.getWatchListMovies().results?.map { it.toMovieWatchList() }.orEmpty()
                if (response.isEmpty()) {
                    RequestState.Error("Something went wrong")
                } else {
                    RequestState.Success(response)
                }
            } catch (exp: Exception) {
                RequestState.Error(exp.localizedMessage ?: "Unknown Error")
            }
        }
    }
}