package com.example.swiftglide.navigation.data.model

data class LoginResponse (
    val validated: Boolean,
    val message: String,
    val role: String,
)