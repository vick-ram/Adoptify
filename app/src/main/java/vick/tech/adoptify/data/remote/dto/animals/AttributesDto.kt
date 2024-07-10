package vick.tech.adoptify.data.remote.dto.animals

import com.google.gson.annotations.SerializedName
import vick.tech.adoptify.data.local.entities.AttributesEntity

data class AttributesDto(
    @SerializedName("spayed_neutered")
    val spayedNeutered: Boolean,
    @SerializedName("house_trained")
    val houseTrained: Boolean,
    val declawed: Boolean,
    @SerializedName("special_needs")
    val specialNeeds: Boolean,
    @SerializedName("shots_current")
    val shotsCurrent: Boolean,
) {
    fun toAttributeEntity() = AttributesEntity(
        spayedNeutered, houseTrained, declawed, specialNeeds, shotsCurrent
    )
}