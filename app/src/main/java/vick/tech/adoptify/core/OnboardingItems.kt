package vick.tech.adoptify.core

import vick.tech.adoptify.R

enum class OnboardingItems(
    val image: Int,
    val title: String,
    val description: String
) {
    SCREEN1(
        image = R.drawable.onboarding1,
        title = "Find Your Forever Friend",
        description = "Adoptify is your gateway to finding a loving companion. Browse profiles of adoptable animals, connect with shelters, and discover the perfect match for you and your family."
    ),
    SCREEN2(
        image = R.drawable.onboarding2,
        title = "Give a Pet a Loving Home",
        description = "Every animal deserves a loving home. Help us make a difference by providing a safe and happy haven for a rescued pet in need."
    ),
    SCREEN3(
        image = R.drawable.onboarding3,
        title = "Find the Perfect Fit",
        description = "Adoptify isn't just about finding your pet; it's about supporting you throughout your journey. We offer resources, tips, and community connections to ensure a smooth transition."
    )
}