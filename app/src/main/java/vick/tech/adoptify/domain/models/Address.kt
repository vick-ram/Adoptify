package vick.tech.adoptify.domain.models

data class Address(
    val address1: String?,
    val address2: String?,
    val city: String,
    val state: String,
    val postcode: String,
    val country: String
)