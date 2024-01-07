package com.example.swiftglide.navigation.ui.home

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.swiftglide.R
import com.example.swiftglide.navigation.data.model.User
import com.example.swiftglide.navigation.ui.auth.HomeViewModel
import com.example.swiftglide.navigation.ui.auth.LoginScreen
import com.example.swiftglide.navigation.ui.navbar.Navbar

@Composable
fun HomeScreen(navController: NavController, homeViewModel : HomeViewModel) {

    homeViewModel.getAllUsers()

    val userList: List<User> by homeViewModel.userList.observeAsState(initial = listOf())
    Log.d("Homescreen", "HomeScreen: ${userList}")
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    0.0f to Color(0xFF476370),
                    500.0f to Color(0xFF163949),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
    ) {
        Text(text = "This is the Home screen")



        Spacer(modifier = Modifier.weight(1f))
        var role = "Player"
        if (userList.isNotEmpty()) {
            role = userList[0].role
        }

        Navbar(navController, "Home", role)
    }
}

/*
@Composable
@Preview
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}*/