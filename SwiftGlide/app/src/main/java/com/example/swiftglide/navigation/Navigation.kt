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

@Composable
fun Navigation(authViewModel: AuthViewModel, createViewModel: CreateViewModel, homeViewModel: HomeViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController, authViewModel = authViewModel, homeViewModel)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController, homeViewModel)
        }
        composable(route = Screen.ScheduleScreen.route) {
            ScheduleScreen(navController)
        }
        composable(route = Screen.AboutScreen.route) {
            AboutScreen(navController)
        }
        composable(route = Screen.SignupScreen.route) {
            SignupScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(route = Screen.CreateScreen.route) {
            CreateScreen(navController = navController, createViewModel=createViewModel, homeViewModel=homeViewModel)
        }
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
                TeamDetailScreen(teamId, createViewModel)
            } ?: run {
                // Handle the case when teamId is null
            }
        }
    }
}