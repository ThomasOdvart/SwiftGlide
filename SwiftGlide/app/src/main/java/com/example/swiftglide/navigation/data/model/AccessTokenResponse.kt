package com.example.swiftglide.navigation.data.model

data class AccessTokenResponse (
    val authenticated: Boolean,
    val accessToken: String,
    val expiresIn: Long,
    val tokenType: String
)