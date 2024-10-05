package inc.anticbyte.moviepedia.domain.usecase

import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.model.MovieDetail
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: NetworkRepository,
    @IoDispatcher private val io: CoroutineDispatcher
) {
    suspend operator fun invoke(movieId: String, onLoading: () -> Unit = {}): Result<MovieDetail> {
        return withContext(io) {
            onLoading()
            runCatching {
                val response = repository.getMovieDetails(movieId).toMovieDetail()
                val movieKeyWords =
                    repository.getMovieKeyWords(movieId).getOrDefault(emptyList()).map { it.name }

                val movieCasts = repository.getMovieCredit(movieId).getOrDefault(emptyList())
                response.copy(movieKeywords = movieKeyWords, movieCasts = movieCasts)
            }
        }
    }
}

/*val movieCasts = if (repository.getMovieCredit(movieId).size > 10) {
    repository.getMovieCredit(movieId).map { it.name }.subList(0,9)
}else{
    repository.getMovieCredit(movieId).map { it.name } }*/

/*if (response.movieId == 0) {
//                    RequestState.Error("Something went wrong")
              } else {
                  Result.success(response.copy(movieKeywords = movieKeyWords, movieCasts = movieCasts))
                  *//*RequestState.Success(
                        response.copy(
                            movieKeywords = movieKeyWords,
                            movieCasts = movieCasts
                        )
                    )*//*
                }*/

/*catch (exp: Exception) {
               Result.failure(exception = exp)
           }*/