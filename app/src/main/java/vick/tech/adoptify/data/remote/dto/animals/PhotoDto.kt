package vick.tech.adoptify.data.remote.dto.animals

import vick.tech.adoptify.data.local.entities.PhotoEntity

data class PhotoDto(
    val small: String,
    val medium: String,
    val large: String,
    val full: String,
) {
    fun toPhotoEntity() = PhotoEntity(
        small, medium, large, full
    )
}