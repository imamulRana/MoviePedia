package inc.anticbyte.moviepedia.domain.usecase

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.model.MovieDetail
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import inc.anticbyte.moviepedia.utils.storeWatchList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: NetworkRepository,
    @IoDispatcher private val io: CoroutineDispatcher,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(movieId: String, onLoading: () -> Unit = {}): Result<MovieDetail> {
        return withContext(io) {
            onLoading()
            runCatching {
                val response = repository.getMovieDetails(movieId).toMovieDetail()

                //get the movie keywords from the api
                val movieKeyWords =
                    repository.getMovieKeyWords(movieId).getOrDefault(emptyList()).map { it.name }

                //get the movie cast from the api
                val movieCasts = repository.getMovieCredit(movieId).getOrDefault(emptyList())

                //call the movie watchlist api and map the id to string
                val movieWatchList =
                    repository.getWatchListMovies().results?.map { it.toMovieWatchList() }.orEmpty()
                        .map { it.movieId.toString() }

                //store the actual movie id in the shared preference
                storeWatchList(movieIds = movieWatchList.map { it }.toMutableSet(), context)

                response.copy(
                    movieKeywords = movieKeyWords,
                    movieCasts = movieCasts,
                    //checks if the movie is in the watchlist or not
                    isMovieInWatchList = response.movieId.toString() in movieWatchList
                )
            }
        }
    }
}