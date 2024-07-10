package vick.tech.adoptify.data.remote.dto.animals

import com.google.gson.annotations.SerializedName

data class PaginationDto(
    @SerializedName("count_per_page")
    val countPerPage: Long,
    @SerializedName("total_count")
    val totalCount: Long,
    @SerializedName("current_page")
    val currentPage: Long,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("_links")
    val links: Links2Dto,
)