package inc.anticbyte.moviepedia.domain.usecase

import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostMovieStatusToWatchListUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
    @IoDispatcher private val io: CoroutineDispatcher
) {
    suspend operator fun invoke(mediaId: Int, watchlist: Boolean, onLoading: () -> Unit) =
        withContext(io) {
            runCatching {
                onLoading()
                networkRepository.addOrRemoveMovieToWatchList(mediaId, watchlist)
                Result.success(Unit)
            }.getOrElse {
                Result.failure(it)
            }
        }
}