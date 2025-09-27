package inc.anticbyte.moviepedia.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore


val Context.dataStore by preferencesDataStore(name = "movie_pedia_datastore")

suspend fun saveToken(token: String, context: Context) {
    context.dataStore.edit {
        it[stringPreferencesKey("token")] = token
    }
    context.dataStore.data.collect { apiKey ->
        println(apiKey[stringPreferencesKey("token")])
    }
}

suspend fun updateToken(token: String, context: Context) {
    context.dataStore.updateData { updateData ->
        updateData.toMutablePreferences().apply {
            this[stringPreferencesKey("token")] = token
        }
    }
    context.dataStore.data.collect {
        println(it[stringPreferencesKey("token")])
    }
}

suspend fun saveLoginStatus(status: Boolean, context: Context) {
    context.dataStore.edit {
        it[booleanPreferencesKey("login_status")] = status
    }
    context.dataStore.data.collect {
        it[booleanPreferencesKey("login_status")] == true
    }
}

suspend fun storeWatchList(movieIds: MutableSet<String>, context: Context) {
    context.dataStore.edit {
        it[stringPreferencesKey("watch_list")] = movieIds.joinToString(",")
    }
}

