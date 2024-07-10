package vick.tech.adoptify.domain.models

/**
 * Attributes UI model characterized by the animal
 */
data class Attributes(
    val spayedNeutered: Boolean,
    val houseTrained: Boolean,
    val declawed: Boolean,
    val specialNeeds: Boolean,
    val shotsCurrent: Boolean
)