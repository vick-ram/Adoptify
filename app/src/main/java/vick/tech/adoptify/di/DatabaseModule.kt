package vick.tech.adoptify.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import vick.tech.adoptify.data.local.db.AdoptifyDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AdoptifyDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AdoptifyDatabase::class.java,
            name = "adoptify.db"
        ).setQueryCallback({ sqlQuery, bindArgs ->
            Log.d("RoomSql", "SQL Query: $sqlQuery SQL Args: $bindArgs")
        }, Runnable::run)
            .fallbackToDestructiveMigration(true)
            .build()
    }
}