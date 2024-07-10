package vick.tech.adoptify.data.remote.dto.organizations

import com.google.gson.annotations.SerializedName

data class Organization(
    val id: String,
    val name: String,
    val email: String,
    val phone: String?,
    val address: Address,
    val hours: Hours,
    val url: String,
    val website: String?,
    @SerializedName("mission_statement")
    val missionStatement: String?,
    val adoption: Adoption,
    @SerializedName("social_media")
    val socialMedia: SocialMedia,
    val photos: List<Photo>,
    val distance: Any?,
    @SerializedName("_links")
    val links: Links,
)