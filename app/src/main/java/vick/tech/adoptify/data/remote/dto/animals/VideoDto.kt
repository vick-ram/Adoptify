package vick.tech.adoptify.data.remote.dto.animals

import vick.tech.adoptify.data.local.entities.VideoEntity

data class VideoDto(
   val embed: String
) {
    fun toVideoEntity() = VideoEntity(
        embed
    )
}