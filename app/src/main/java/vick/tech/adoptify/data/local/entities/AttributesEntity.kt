package vick.tech.adoptify.data.local.entities

import vick.tech.adoptify.domain.models.Attributes


data class AttributesEntity(
    val spayedNeutered: Boolean,
    val houseTrained: Boolean,
    val declawed: Boolean,
    val specialNeeds: Boolean,
    val shotsCurrent: Boolean
) {
    fun toAttributes() = Attributes(
        spayedNeutered, houseTrained, declawed, specialNeeds, shotsCurrent
    )
}