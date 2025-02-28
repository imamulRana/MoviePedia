package inc.anticbyte.moviepedia.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import inc.anticbyte.moviepedia.domain.repo.LocalRepository
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json


class LocalRepositoryImpl(private val context: Context) : LocalRepository {
    private val Context.moviePediaStore by preferencesDataStore(context.packageName)
    private val movieIdList = stringPreferencesKey("movie_Ids")

    override suspend fun storeWatchList(movieIds: MutableSet<String>) {
        context.moviePediaStore.edit {
            it.clear()
            it[movieIdList] = Json.encodeToString(movieIds)
        }
    }

    override suspend fun getWatchList(): MutableSet<String> {
        return Json.decodeFromString(context.moviePediaStore.data.first()[movieIdList] ?: "[]")
    }
}