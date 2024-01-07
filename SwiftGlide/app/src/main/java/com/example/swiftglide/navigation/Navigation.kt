package com.example.swiftglide.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.swiftglide.navigation.data.model.Team
import com.example.swiftglide.navigation.ui.about.AboutScreen
import com.example.swiftglide.navigation.ui.auth.AuthViewModel
import com.example.swiftglide.navigation.ui.auth.HomeViewModel
import com.example.swiftglide.navigation.ui.auth.LoginScreen
import com.example.swiftglide.navigation.ui.auth.SignupScreen
import com.example.swiftglide.navigation.ui.create.CreateScreen
import com.example.swiftglide.navigation.ui.create.CreateViewModel
import com.example.swiftglide.navigation.ui.create.TeamDetailScreen
import com.example.swiftglide.navigation.ui.home.HomeScreen
import com.example.swiftglide.navigation.ui.schedule.ScheduleScreen

/**
 * Composable function responsible for setting up the navigation within the app using Jetpack Compose.
 *
 * @param authViewModel ViewModel for authentication-related operations.
 * @param createViewModel ViewModel for team creation and retrieval operations.
 * @param homeViewModel ViewModel for handling home screen-related operations.
 */
@Composable
fun Navigation(authViewModel: AuthViewModel, createViewModel: CreateViewModel, homeViewModel: HomeViewModel) {
    // Create a NavController to navigate between composables
    val navController = rememberNavController()

    // Set up the navigation using NavHost with specified start destination
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        // Composable for the Login screen
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController, authViewModel = authViewModel, homeViewModel)
        }

        // Composable for the Home screen
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController, homeViewModel)
        }

        // Composable for the Schedule screen
        composable(route = Screen.ScheduleScreen.route) {
            ScheduleScreen(navController)
        }

        // Composable for the About screen
        composable(route = Screen.AboutScreen.route) {
            AboutScreen(navController, homeViewModel)
        }

        // Composable for the Signup screen
        composable(route = Screen.SignupScreen.route) {
            SignupScreen(navController = navController, authViewModel = authViewModel, homeViewModel=homeViewModel)
        }

        // Composable for the Create screen
        composable(route = Screen.CreateScreen.route) {
            CreateScreen(navController = navController, createViewModel=createViewModel, homeViewModel=homeViewModel)
        }

        // Composable for the TeamDetail screen with teamId as a parameter
        composable(
            route = Screen.TeamDetailScreen.route + "/{teamId}",
            arguments = listOf(
                navArgument("teamId") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val teamId = entry.arguments?.getInt("teamId")

            // Pass the teamId to your TeamDetailScreen
            teamId?.let {
                TeamDetailScreen(teamId=teamId, createViewModel=createViewModel, navController=navController, homeViewModel=homeViewModel)
            } ?: run {
                // Handle the case when teamId is null
            }
        }
    }
}