package vick.tech.adoptify.domain.models

/**
 * The breed type model class representation for UI
 */
data class Breeds(
    val primary: String,
    val secondary: String?,
    val mixed: Boolean,
    val unknown: Boolean
)