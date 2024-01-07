package com.example.swiftglide.navigation.data.model

data class CreateTeamResponse (
    val isCreated: Boolean,
    val message: String,
    val team : Team,
)