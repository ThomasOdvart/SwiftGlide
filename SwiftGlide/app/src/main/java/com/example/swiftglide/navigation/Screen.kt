package com.example.swiftglide.navigation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object HomeScreen : Screen("home_screen")
    object SignupScreen : Screen("signup_screen")
    object ScheduleScreen : Screen("schedule_screen")
    object AboutScreen : Screen("about_screen")
    object CreateScreen : Screen("create_screen")
    object TeamDetailScreen : Screen("teamDetail_screen")


    fun withArgs(vararg args: Int) : String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}