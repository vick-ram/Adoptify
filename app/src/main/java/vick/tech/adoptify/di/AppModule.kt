package vick.tech.adoptify.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import vick.tech.adoptify.data.local.db.AdoptifyDatabase
import vick.tech.adoptify.data.remote.api.ApiService
import vick.tech.adoptify.data.remote.repository.AdoptifyMediator
import vick.tech.adoptify.data.remote.repository.AdoptifyRepositoryImpl
import vick.tech.adoptify.data.util.Constants
import vick.tech.adoptify.domain.repository.AdoptifyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.SETTINGS)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideAdoptifyRepository(
        apiService: ApiService,
        database: AdoptifyDatabase
    ) : AdoptifyRepository {
        return AdoptifyRepositoryImpl(apiService, database)
    }

    @Provides
    fun provideAdoptifyMediator(
        apiService: ApiService,
        adoptifyDatabase: AdoptifyDatabase
    ): AdoptifyMediator {
        return AdoptifyMediator(apiService, adoptifyDatabase)
    }
}