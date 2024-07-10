package vick.tech.adoptify.data.remote.dto.animals

import vick.tech.adoptify.data.local.entities.EnvironmentEntity

data class EnvironmentDto(
    val children: Boolean,
    val dogs: Boolean?,
    val cats: Boolean?,
) {
    fun toEnvironmentEntity() = EnvironmentEntity(
        children, dogs, cats
    )
}