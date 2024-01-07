package com.example.swiftglide.navigation.ui.about

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.swiftglide.R
import com.example.swiftglide.navigation.Screen
import com.example.swiftglide.navigation.data.model.User
import com.example.swiftglide.navigation.ui.auth.HomeViewModel
import com.example.swiftglide.navigation.ui.auth.LoginScreen
import com.example.swiftglide.navigation.ui.navbar.Navbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AboutScreen(navController: NavController, homeViewModel : HomeViewModel) {

    val coroutineScope = rememberCoroutineScope()

    homeViewModel.getAllUsers()

    val userList: List<User> by homeViewModel.userList.observeAsState(initial = listOf())
    Log.d("AboutScreen", "AboutScreen: ${userList}")
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
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .width(350.dp)
                .height(75.dp)
                .background(color = Color(0xFF163949), shape = RoundedCornerShape(20.dp))
        ) {
            //avatar
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )
            }

            Box(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Column() {
                    if(userList.isNotEmpty()) {
                        Text(text = userList[0].role,  color = Color.White, fontWeight = FontWeight.ExtraBold)

                        Text(text = userList[0].email, color = Color.White)
                    }
                }
                
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {
                        //logout

                        homeViewModel.deleteUser(userList[0])

                        coroutineScope.launch {
                            delay(1000)
                            navController.navigate(Screen.LoginScreen.route)
                        }


                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_logout),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        var role = "Player"
        if (userList.isNotEmpty()) {
            role = userList[0].role
        }

        Navbar(navController, "Info", role)
    }
}


/*
@Composable
@Preview
fun HomeScreenPreview() {
    val navController = rememberNavController()
    AboutScreen(navController = navController)
}*/