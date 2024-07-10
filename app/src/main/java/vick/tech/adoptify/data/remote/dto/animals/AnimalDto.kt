package vick.tech.adoptify.data.remote.dto.animals

import com.google.gson.annotations.SerializedName
import vick.tech.adoptify.data.local.entities.AnimalEntity

data class AnimalDto(
    val id: Int,
    @SerializedName("organization_id")
    val organizationId: String,
    val url: String,
    val type: String,
    val species: String,
    val breeds: BreedsDto,
    val colors: ColorsDto,
    val age: String,
    val gender: String,
    val size: String,
    val coat: String?,
    val attributes: AttributesDto,
    val environment: EnvironmentDto,
    val tags: List<String>,
    val name: String,
    val description: String?,
    @SerializedName("organization_animal_id")
    val organizationAnimalId: String?,
    val photos: List<PhotoDto>,
    @SerializedName("primary_photo_cropped")
    val primaryPhotoCropped: PrimaryPhotoCroppedDto?,
    val videos: List<VideoDto>,
    val status: String,
    @SerializedName("status_changed_at")
    val statusChangedAt: String,
    @SerializedName("published_at")
    val publishedAt: String,
    val distance: Double,
    val contact: ContactDto,
    @SerializedName("_links")
    val links: LinksDto,
) {
    fun toAnimalEntity(): AnimalEntity {
        return  AnimalEntity(
            id = id,
            organizationId = organizationId,
            url = url,
            type = type,
            species = species,
            breeds = breeds.toBreedEntity(),
            colors = colors.toColorEntity(),
            age = age,
            gender = gender,
            size = size,
            coat = coat,
            name = name,
            description = description,
            photos = photos.map { it.toPhotoEntity() },
            videos = videos.map { it.toVideoEntity() },
            status = status,
            attributes = attributes.toAttributeEntity(),
            environment = environment.toEnvironmentEntity(),
            tags = tags,
            contact = contact.toContactEntity(),
            publishedAt = publishedAt,
            distance = distance
        )
    }
}