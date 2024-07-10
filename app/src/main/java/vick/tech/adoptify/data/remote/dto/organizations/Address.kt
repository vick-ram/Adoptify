package vick.tech.adoptify.data.remote.dto.organizations

data class Address(
    val address1: String?,
    val address2: Any?,
    val city: String,
    val state: String,
    val postcode: String,
    val country: String,
)