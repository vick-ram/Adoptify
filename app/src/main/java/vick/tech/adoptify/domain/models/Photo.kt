package vick.tech.adoptify.domain.models

/**
 * Photo model class to represent UI different image
 * sizes
 */
data class Photo(
    val small: String,
    val medium: String,
    val large: String,
    val full: String
)