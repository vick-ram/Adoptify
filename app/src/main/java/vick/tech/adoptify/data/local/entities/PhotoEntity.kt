package vick.tech.adoptify.data.local.entities

import vick.tech.adoptify.domain.models.Photo

data class PhotoEntity(
    val small: String,
    val medium: String,
    val large: String,
    val full: String
) {
    fun toPhotos() = Photo(
        small, medium, large, full
    )
}