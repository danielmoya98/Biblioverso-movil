package com.example.library.ui.onboarding
import androidx.annotation.DrawableRes

data class OnboardingPage(
    @DrawableRes val imageRes: Int,
    val title: String,
    val subtitle: String
)