package com.example.swiftglide.navigation.data.network

import com.example.swiftglide.navigation.data.model.CreateTeamResponse
import com.example.swiftglide.navigation.data.model.LoginResponse
import com.example.swiftglide.navigation.data.model.SignupResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("signup")
    suspend fun signup(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("organizationName") organization: String
    ): SignupResponse

    @FormUrlEncoded
    @POST("createTeam")
    suspend fun createTeam(
        @Field("name") name: String,
        @Field("amountOfPlayers") amountOfPlayers: Int,
        @Field("email") email: String,
    ): CreateTeamResponse

}