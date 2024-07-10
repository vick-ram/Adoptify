package vick.tech.adoptify.data.local.entities

import vick.tech.adoptify.domain.models.Video

data class VideoEntity(
    val embed: String
) {
    fun toVideo() = Video(
        embed = embed
    )
}