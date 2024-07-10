package vick.tech.adoptify.data.remote.dto.animals

import vick.tech.adoptify.data.local.entities.BreedsEntity

data class BreedsDto(
    val primary: String,
    val secondary: String?,
    val mixed: Boolean,
    val unknown: Boolean,
) {
    fun toBreedEntity() = BreedsEntity(
        primary, secondary, mixed, unknown
    )
}