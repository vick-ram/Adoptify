package vick.tech.adoptify.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vick.tech.adoptify.data.local.dao.AnimalsDao
import vick.tech.adoptify.data.local.entities.AnimalEntity

/**
 * The Room database for the Adoptify application.
 *
 * This abstract class serves as the main access point to the persisted data regarding animals
 * managed by the application. It includes the definition of all entities and their corresponding DAOs
 * (Data Access Objects) that are used for accessing the database.
 *
 * @Database marks the class as a Room Database with a table (entity) of AnimalEntity and a version number.
 * @TypeConverters allows for additional type converters that Room can use for database operations.
 * Currently, it includes a single entity, [AnimalEntity], which represents the animals available for adoption.
 * The database version is set to 2, and exportSchema is false to avoid keeping schema version history backups.
 *
 * The abstract function [animalsDao] provides an instance of [AnimalsDao], which contains methods
 * to perform read/write operations on the [AnimalEntity] table.
 */
@Database(
    entities = [AnimalEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AdoptifyDatabase : RoomDatabase() {
    abstract fun animalsDao(): AnimalsDao
}