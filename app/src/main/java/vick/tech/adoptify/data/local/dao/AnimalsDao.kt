package vick.tech.adoptify.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import vick.tech.adoptify.data.local.entities.AnimalEntity

@Dao
interface AnimalsDao {
    @Query("SELECT * FROM animals")
    fun loadAnimals(): PagingSource<Int, AnimalEntity>

    @Upsert
    suspend fun insertAll(animals: List<AnimalEntity>)

    @Query("DELETE FROM animals")
    suspend fun clearAnimals()
}