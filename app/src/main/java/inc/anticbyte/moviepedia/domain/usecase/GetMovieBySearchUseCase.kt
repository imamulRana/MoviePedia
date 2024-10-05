package inc.anticbyte.moviepedia.domain.usecase

import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.model.MovieWatchList
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieBySearchUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
    @IoDispatcher private val io: CoroutineDispatcher
) {
    suspend operator fun invoke(query: String, onLoading: () -> Unit = {}): Result<List<MovieWatchList>> {
        return withContext(io) {
            runCatching {
                onLoading()
                val networkResponse = networkRepository.getMovieBySearch(query)
                val response = networkResponse.results?.map { it.toMovieWatchList() }.orEmpty()
                response
            }.onFailure { exp ->
                throw Exception(exp.localizedMessage)
            }
        }
    }
}