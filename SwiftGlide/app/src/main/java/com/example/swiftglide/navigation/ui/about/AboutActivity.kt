package com.example.swiftglide.navigation.ui.about

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.swiftglide.R
import com.example.swiftglide.navigation.ui.auth.LoginScreen
import com.example.swiftglide.navigation.ui.navbar.Navbar

@Composable
fun AboutScreen(navController: NavController) {
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
        Text(text = "This is the About screen")


        Spacer(modifier = Modifier.weight(1f))

        Navbar(navController, "Info", "Manager")
    }
}


@Composable
@Preview
fun HomeScreenPreview() {
    val navController = rememberNavController()
    AboutScreen(navController = navController)
}