package inc.anticbyte.moviepedia.domain.usecase

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.model.MovieWatchList
import inc.anticbyte.moviepedia.domain.repo.LocalRepository
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieWatchListUseCase @Inject constructor(
    private val repository: NetworkRepository,
    @IoDispatcher private val io: CoroutineDispatcher,
    private val localRepository: LocalRepository,
) {
    suspend operator fun invoke(onLoading: () -> Unit): Result<List<MovieWatchList>> {
        return withContext(io) {
            runCatching {
                onLoading()
                val response =
                    repository.getWatchListMovies().results?.map { it.toMovieWatchList() }.orEmpty()
                if (response.isNotEmpty()) {
                    localRepository.storeWatchList(response.map { it.movieId.toString() }.toMutableSet())
                    response
                } else {
                    throw Exception("Empty List")
                }
            }
        }
    }
}
