package vick.tech.adoptify.data.local.entities

import vick.tech.adoptify.domain.models.Breeds

data class BreedsEntity(
    val primary: String,
    val secondary: String?,
    val mixed: Boolean,
    val unknown: Boolean
) {
    fun toBreeds() = Breeds(
        primary, secondary, mixed, unknown
    )
}
