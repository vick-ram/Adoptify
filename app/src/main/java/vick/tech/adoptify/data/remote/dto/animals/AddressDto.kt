package vick.tech.adoptify.data.remote.dto.animals

import vick.tech.adoptify.data.local.entities.AddressEntity

data class AddressDto(
    val address1: String?,
    val address2: String?,
    val city: String,
    val state: String,
    val postcode: String,
    val country: String,
) {
    fun toAddressEntity() = AddressEntity(
        address1, address2, city, state, postcode, country
    )
}