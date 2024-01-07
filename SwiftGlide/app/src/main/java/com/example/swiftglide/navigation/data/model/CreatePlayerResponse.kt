package com.example.swiftglide.navigation.data.model

data class CreatePlayerResponse (
    val isCreated: Boolean,
    val message: String,
    val player : Player,
)