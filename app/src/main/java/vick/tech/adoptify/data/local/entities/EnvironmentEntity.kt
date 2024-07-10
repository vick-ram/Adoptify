package vick.tech.adoptify.data.local.entities

import vick.tech.adoptify.domain.models.Environment

data class EnvironmentEntity(
    val children: Boolean?,
    val dogs: Boolean?,
    val cats: Boolean?
) {
    fun toEnvironment() = Environment(
        children, dogs, cats
    )
}