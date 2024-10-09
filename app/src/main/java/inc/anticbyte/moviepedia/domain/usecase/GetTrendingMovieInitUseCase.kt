package inc.anticbyte.moviepedia.domain.usecase

import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.model.Movie
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import inc.anticbyte.moviepedia.utils.RequestState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTrendingMovieInitUseCase @Inject constructor(
    private val repository: NetworkRepository,
    @IoDispatcher private val io: CoroutineDispatcher
) {
    suspend operator fun invoke(timeWindow: String): RequestState<List<Movie>> =
        withContext(io) {
            runCatching {
                RequestState.Loading
                val response =
                    repository.getTrendingMovies(timeWindow = timeWindow, page = 1).results?.map { it.toMovie() }
                        .orEmpty()
                if (response.isEmpty()) {
                    RequestState.Error("Internal Error")
                } else {
                    RequestState.Success(response)
                }

            }.getOrElse {
                RequestState.Error(it.localizedMessage ?: "Internal Error")
            }
        }
}




/*
class GetTrendingMovieUseCase @Inject constructor(
    private val repository: PagingRepository,
    @IoDispatcher private val io: CoroutineDispatcher
) {
    suspend operator fun invoke(
        timeWindow: String = "day",
    ): RequestState<Flow<PagingData<Movie>>> =
        withContext(io) {
            runCatching {
                RequestState.Success(repository.getTrendingMovies(timeWindow = timeWindow))
            }.getOrElse {
                RequestState.Error(it.localizedMessage ?: "Something went wrong")
            }
        }
}*/
