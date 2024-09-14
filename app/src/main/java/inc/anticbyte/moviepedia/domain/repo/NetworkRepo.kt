package inc.anticbyte.moviepedia.domain.repo

import inc.anticbyte.moviepedia.data.remote.ShowDto
import inc.anticbyte.moviepedia.utils.RequestState

interface NetworkRepo {
    suspend fun getAllShow(): RequestState<List<ShowDto>>
    suspend fun getShowById(id: Int): RequestState<ShowDto>
}