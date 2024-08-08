package inc.anticbyte.moviepedia.domain.repo

import inc.anticbyte.moviepedia.data.remote.UserDto
import inc.anticbyte.moviepedia.data.remote.show.ShowDto
import inc.anticbyte.moviepedia.utils.RequestState

interface NetworkRepo {
    suspend fun getShow(): RequestState<List<ShowDto>>
}