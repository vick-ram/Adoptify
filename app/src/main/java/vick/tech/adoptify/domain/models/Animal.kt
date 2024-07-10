package vick.tech.adoptify.domain.models

/**
 * Model class to represent animal data on UI
 * mapped from room entity
 */
data class Animal(
    val id: Int,
    val organizationId: String,
    val url: String,
    val type: String,
    val species: String,
    val breeds: Breeds,
    val colors: Colors,
    val age: String,
    val gender: String,
    val size: String,
    val coat: String?,
    val name: String,
    val description: String?,
    val photos: List<Photo>,
    val videos: List<Video>,
    val status: String,
    val attributes: Attributes,
    val environment: Environment,
    val tags: List<String>,
    val contact: Contact,
    val publishedAt: String,
    val distance: Double
)