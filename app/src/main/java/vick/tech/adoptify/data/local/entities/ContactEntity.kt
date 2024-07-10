package vick.tech.adoptify.data.local.entities

import androidx.room.Embedded
import vick.tech.adoptify.domain.models.Contact

data class ContactEntity(
    val email: String?,
    val phone: String?,
    @Embedded val address: AddressEntity
) {
    fun toContact() = Contact(
        email = email,
        phone = phone,
        address = address.toAddress()
    )
}