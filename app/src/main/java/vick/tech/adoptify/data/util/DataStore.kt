package vick.tech.adoptify.data.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStore @Inject constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun setOnboarding(completed: Boolean) {
        dataStore.edit { pref ->
            pref[onboarding] = completed
        }
    }

    suspend fun onboardingCompleted(): Boolean {
        val completed = dataStore.data.distinctUntilChanged().first()
        return completed[onboarding] ?: false
    }

    suspend fun saveAccessToken(token: String, expiry: Long) {
        dataStore.edit { prefs ->
            prefs[accessToken] = setOf(token)
            prefs[expiryTime] = expiry
        }
    }

   suspend fun readAccessToken(): Pair<String, Long> {
       val pref = dataStore.data.distinctUntilChanged().first()
       val token = pref[accessToken]?.firstOrNull() ?: ""
       val expiry = pref[expiryTime] ?: 0L
       return Pair(token, expiry)
    }

    suspend fun clearAccessToken() {
        dataStore.edit { prefs ->
            prefs.remove(accessToken)
            prefs.remove(expiryTime)
        }
    }

    val access_token: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[accessToken]?.firstOrNull()
        }

    val expiresIn: Flow<Long?> = dataStore.data
        .map { preferences ->
            preferences[expiryTime]
        }

    companion object {
        val onboarding = booleanPreferencesKey(Constants.ONBOARDING_KEY)
        val accessToken = stringSetPreferencesKey(Constants.ACCESS_TOKEN_KEY)
        val expiryTime = longPreferencesKey(Constants.EXPIRY_TIME_KEY)
    }

}