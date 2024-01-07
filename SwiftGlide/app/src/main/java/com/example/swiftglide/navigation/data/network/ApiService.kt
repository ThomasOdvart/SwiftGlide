package com.example.swiftglide.navigation.data.network

import com.example.swiftglide.navigation.data.model.CreatePlayerResponse
import com.example.swiftglide.navigation.data.model.CreateTeamResponse
import com.example.swiftglide.navigation.data.model.ListTeam
import com.example.swiftglide.navigation.data.model.LoginResponse
import com.example.swiftglide.navigation.data.model.SignupResponse
import com.example.swiftglide.navigation.data.model.Team
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Retrofit service interface for making API calls related to authentication, team creation, and team management.
 */
interface ApiService {

    /**
     * Attempts user login using email and password.
     *
     * @param email User's email.
     * @param password User's password.
     * @return [LoginResponse] containing login result.
     */
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    /**
     * Attempts user signup with email, password, and organization name.
     *
     * @param email User's email.
     * @param password User's password.
     * @param organization User's organization name.
     * @return [SignupResponse] containing signup result.
     */
    @FormUrlEncoded
    @POST("signup")
    suspend fun signup(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("organizationName") organization: String
    ): SignupResponse

    /**
     * Creates a new team.
     *
     * @param name Team name.
     * @param email Team owner's email.
     * @return [CreateTeamResponse] containing team creation result.
     */
    @FormUrlEncoded
    @POST("createTeam")
    suspend fun createTeam(
        @Field("name") name: String,
        @Field("email") email: String,
    ): CreateTeamResponse

    /**
     * Retrieves a list of teams associated with an organization.
     *
     * @param email User's email.
     * @return List of [ListTeam] containing team information.
     */
    @FormUrlEncoded
    @POST("getTeamsByOrganization")
    suspend fun getTeamsByOrganization(
        @Field("email") email: String,
    ): List<ListTeam>

    /**
     * Creates a player in a team.
     *
     * @param teamId Team ID.
     * @param email Player's email.
     * @param password Player's password.
     */
    @FormUrlEncoded
    @POST("CreatePlayerinTeam")
    suspend fun createPlayerInTeam(
        @Field("teamId") teamId: Int,
        @Field("email") email: String,
        @Field("password") password: String,
    ) : CreatePlayerResponse

    /**
     * Retrieves a team by its ID.
     *
     * @param id Team ID.
     * @return [Team] containing team information.
     */
    @FormUrlEncoded
    @POST("getTeamById")
    suspend fun getTeamById(
        @Field("id") id: Int,
    ): Team
}