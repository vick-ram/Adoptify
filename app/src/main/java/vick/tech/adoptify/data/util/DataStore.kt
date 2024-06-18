package vick.tech.adoptify.data.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
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


    companion object {
        val onboarding = booleanPreferencesKey(Constants.ONBOARDING_KEY)
    }
}