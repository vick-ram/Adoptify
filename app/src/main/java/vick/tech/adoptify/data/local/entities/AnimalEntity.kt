package vick.tech.adoptify.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import vick.tech.adoptify.domain.models.Animal

/**
 * Represents an animal entity in the Adoptify application.
 *
 * This class models the detailed information of animals available for adoption. It includes comprehensive
 * details such as the animal's ID, type, species, and more specific attributes like breeds, colors, age,
 * gender, size, and coat. Additionally, it encapsulates multimedia information through photos and videos,
 * the adoption status, and contact details for the animal.
 *
 * The @Entity annotation marks this as a table within the Room database, with each instance representing
 * a row within the "animals" table. The primary key is the animal's ID.
 *
 * The embedded entities such as BreedsEntity, ColorsEntity, AttributesEntity, EnvironmentEntity, and ContactEntity
 * allow for a structured and normalized way to store complex information about each animal within the same table.
 *
 * The toAnimal function converts this database entity into a domain model [Animal], facilitating the separation
 * of database layer entities from the domain logic of the application.
 *
 * @property id The unique identifier for the animal.
 * @property organizationId The identifier for the organization that has listed the animal.
 * @property url The URL to the animal's profile.
 * @property type The type of animal (e.g., dog, cat).
 * @property species The species of the animal.
 * @property breeds The breeds information of the animal.
 * @property colors The color information of the animal.
 * @property age The age of the animal.
 * @property gender The gender of the animal.
 * @property size The size category of the animal.
 * @property coat The coat length of the animal, if applicable.
 * @property name The name of the animal.
 * @property description A description of the animal.
 * @property photos A list of photo entities associated with the animal.
 * @property videos A list of video entities associated with the animal.
 * @property status The adoption status of the animal.
 * @property attributes The attributes describing the animal's characteristics.
 * @property environment The environment preferences of the animal.
 * @property tags A list of tags associated with the animal.
 * @property contact The contact information for inquiring about the animal.
 * @property publishedAt The date and time when the animal was listed.
 * @property distance The distance of the animal from the adopter, if applicable.
 */
@Entity(tableName = "animals")
data class AnimalEntity(
    @PrimaryKey val id: Int,
    val organizationId: String,
    val url: String,
    val type: String,
    val species: String,
    @Embedded val breeds: BreedsEntity,
    @Embedded val colors: ColorsEntity,
    val age: String,
    val gender: String,
    val size: String,
    val coat: String?,
    val name: String,
    val description: String?,
    val photos: List<PhotoEntity>,
    val videos: List<VideoEntity>,
    val status: String,
    @Embedded val attributes: AttributesEntity,
    @Embedded val environment: EnvironmentEntity,
    val tags: List<String>,
    @Embedded val contact: ContactEntity,
    val publishedAt: String,
    val distance: Double
) {
    fun toAnimal() = Animal(
        id = id,
        organizationId = organizationId,
        url = url,
        type = type,
        species = species,
        breeds = breeds.toBreeds(),
        colors = colors.toColors(),
        age = age,
        gender = gender,
        size = size,
        coat = coat,
        name = name,
        description = description,
        photos = photos.map { it.toPhotos() },
        videos = videos.map { it.toVideo() },
        status = status,
        attributes = attributes.toAttributes(),
        environment = environment.toEnvironment(),
        tags = tags,
        contact = contact.toContact(),
        publishedAt = publishedAt,
        distance = distance
    )
}
