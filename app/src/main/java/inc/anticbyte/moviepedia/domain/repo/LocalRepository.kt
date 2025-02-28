package inc.anticbyte.moviepedia.domain.repo

interface LocalRepository {
    suspend fun storeWatchList(movieIds: MutableSet<String>)
    suspend fun getWatchList(): MutableSet<String>
}