package vick.tech.adoptify.data.remote.dto.animals

data class AnimalsResponse(
    val animals: List<AnimalDto>,
    val pagination: PaginationDto
)