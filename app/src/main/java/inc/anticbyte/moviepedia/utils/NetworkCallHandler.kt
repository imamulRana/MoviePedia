package inc.anticbyte.moviepedia.utils

import android.content.Context
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