package vick.tech.adoptify.data.remote.dto.animals

import vick.tech.adoptify.data.local.entities.ColorsEntity

data class ColorsDto(
    val primary: String?,
    val secondary: String?,
    val tertiary: String?,
) {
    fun toColorEntity() = ColorsEntity(
        primary, secondary, tertiary
    )
}