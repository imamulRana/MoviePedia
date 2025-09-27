package inc.anticbyte.moviepedia.domain.usecase

import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.model.CastDetail
import inc.anticbyte.moviepedia.domain.repo.NetworkRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieCastDetailUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
    @IoDispatcher private val io: CoroutineDispatcher
) {
    suspend operator fun invoke(personId: String, onLoading: () -> Unit): Result<CastDetail> {
        return withContext(io) {
            runCatching {
                onLoading()
                val castCreditDeferred = async {
                    networkRepository.getMovieCastCredits(personId).toCastDetail()
                }.await()
                val castDetailDeferred = async {
                    networkRepository.getMovieCastDetail(personId).toCastDetail()
                }.await()
                castDetailDeferred.copy(
                    id = castDetailDeferred.id,
                    personImage = castDetailDeferred.personImage,
                    personName = castDetailDeferred.personName,
                    personGender = castDetailDeferred.personGender,
                    personAge = castDetailDeferred.personAge,
                    movieCredit = castCreditDeferred.movieCredit
                )
            }.onFailure { exp ->
                throw Exception(exp.message)
            }
        }
    }
}