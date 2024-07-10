package vick.tech.adoptify.data.remote.dto.animals

import vick.tech.adoptify.data.local.entities.ContactEntity

data class ContactDto(
    val email: String,
    val phone: String,
    val address: AddressDto,
) {
    fun toContactEntity() = ContactEntity(
        email = email,
        phone = phone,
        address = address.toAddressEntity()
    )
}