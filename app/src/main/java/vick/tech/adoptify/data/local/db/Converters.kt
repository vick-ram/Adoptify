package vick.tech.adoptify.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import vick.tech.adoptify.data.local.entities.PhotoEntity
import vick.tech.adoptify.data.local.entities.VideoEntity

/**
 * A collection of @TypeConverter methods for the Adoptify application's Room database.
 *
 * This class provides custom converters for Room to handle complex data types that are not natively supported
 * in SQLite databases. It uses Gson to serialize and deserialize objects to and from JSON strings, allowing
 * for the storage of complex data types like lists of custom objects in the database.
 *
 * Methods included:
 * - fromPhotoList and toPhotoList: Convert between a list of PhotoEntity objects and its JSON representation.
 * - fromVideoList and toVideoList: Convert between a list of VideoEntity objects and its JSON representation.
 * - fromTagsList and toTagsList: Convert between a list of Strings (tags) and its JSON representation.
 *
 * These converters ensure that complex data types related to the Adoptify application's functionality,
 * such as photos, videos, and tags associated with animals, can be stored and retrieved from the database
 * in a structured manner.
 */
class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromPhotoList(photos: List<PhotoEntity>?): String? {
        return gson.toJson(photos)
    }

    @TypeConverter
    fun toPhotoList(json: String?): List<PhotoEntity>? {
        val type = object : TypeToken<List<PhotoEntity>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromVideoList(videos: List<VideoEntity>?): String? {
        return gson.toJson(videos)
    }

    @TypeConverter
    fun toVideoList(json: String?): List<VideoEntity>? {
        val type = object : TypeToken<List<VideoEntity>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun fromTagsList(tags: List<String>?): String? {
        return gson.toJson(tags)
    }

    @TypeConverter
    fun toTagsList(json: String?): List<String>? {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type)
    }
}
