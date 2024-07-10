package vick.tech.adoptify.data.local.entities

import androidx.room.ColumnInfo
import vick.tech.adoptify.domain.models.Colors

data class ColorsEntity(
    @ColumnInfo("primary_color") val primary: String?,
    @ColumnInfo("secondary_color") val secondary: String?,
    val tertiary: String?
) {
    fun toColors() = Colors(
        primary, secondary, tertiary
    )
}