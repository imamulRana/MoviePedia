package inc.anticbyte.moviepedia.data.repo

import inc.anticbyte.moviepedia.data.remote.ShowDto
import inc.anticbyte.moviepedia.di.IoDispatcher
import inc.anticbyte.moviepedia.domain.repo.NetworkRepo
import inc.anticbyte.moviepedia.utils.RequestState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkRepoImpl @Inject constructor(
    @IoDispatcher private val io: CoroutineDispatcher,
    private val ktorClient: HttpClient
) : NetworkRepo {
    override suspend fun getShow(): RequestState<List<ShowDto>> {
        return withContext(io) {
            try {
                val response = ktorClient.get("shows")
                if (response.status.isSuccess()) {
                    RequestState.Success(response.body())
                } else {
                    RequestState.Error(response.status.description)
                }
            } catch (e: Exception) {
                RequestState.Error(e.message.toString())
            }
        }
    }
}