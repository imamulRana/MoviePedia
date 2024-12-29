package inc.anticbyte.moviepedia.domain.usecase

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.model.MovieWatchList
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import inc.anticbyte.moviepedia.utils.storeWatchList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieWatchListUseCase @Inject constructor(
    private val repository: NetworkRepository,
    @IoDispatcher private val io: CoroutineDispatcher,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(onLoading: () -> Unit): Result<List<MovieWatchList>> {
        return withContext(io) {
            runCatching {
                onLoading()
                val response =
                    repository.getWatchListMovies().results?.map { it.toMovieWatchList() }.orEmpty()
                if (response.isNotEmpty()) {
                    storeWatchList(response.map { it.movieId.toString() }.toMutableSet(), context)
                    response
                } else {
                    throw Exception("Empty List")
                }
            }
        }
    }
}
