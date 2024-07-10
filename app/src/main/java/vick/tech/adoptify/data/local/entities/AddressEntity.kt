package vick.tech.adoptify.data.local.entities

import vick.tech.adoptify.domain.models.Address

/**
 * Represents an address entity in the Adoptify application.
 *
 * This data class models the address details of entities such as shelters or adopters within the application.
 * It encapsulates various components of an address, including optional address lines, city, state, postcode,
 * and country, making it versatile for representing addresses across different regions.
 *
 * The [toAddress] function converts this entity into a domain model [Address], facilitating the separation
 * of database layer entities from the domain logic of the application.
 *
 * @property address1 The first line of the address, which may include street number and name. Nullable.
 * @property address2 The second line of the address, which may include apartment or suite number. Nullable.
 * @property city The city part of the address.
 * @property state The state, province, or region part of the address.
 * @property postcode The postal or ZIP code part of the address.
 * @property country The country part of the address.
 */
data class AddressEntity(
    val address1: String?,
    val address2: String?,
    val city: String,
    val state: String,
    val postcode: String,
    val country: String
) {
    fun toAddress() = Address(
        address1, address2, city, state, postcode, country
    )
}
