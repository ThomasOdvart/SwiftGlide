package com.example.swiftglide.navigation.ui.navbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.swiftglide.R
import com.example.swiftglide.navigation.Screen

@Composable
fun Navbar(navController: NavController, currentPage: String, role: String) {

    val activePage by remember { mutableStateOf(currentPage) }
    val interactionSource = remember { MutableInteractionSource() }

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFF0E2A3C))
    ) {
        Box (
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    navController.navigate(Screen.HomeScreen.route)
                }
        ) {

            if(activePage == "Home") {
                Box(
                    modifier = Modifier
                        .width(45.dp)
                        .height(45.dp)
                        .background(Color(0xFF3F8AC9), shape = CircleShape)
                        .align(Alignment.Center)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_home_filled),
                        contentDescription = null,
                        modifier = Modifier
                            .height(30.dp)
                            .align(Alignment.Center))
                }
            } else {
                Image(
                    painter = painterResource(id = R.drawable.icon_home_outline),
                    contentDescription = null,
                    modifier = Modifier
                        .height(30.dp)
                        .align(Alignment.Center))
            }
        }

        Box (
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    navController.navigate(Screen.ScheduleScreen.route)
                }
        ) {

            if(activePage == "Schedule") {
                Box(
                    modifier = Modifier
                        .width(45.dp)
                        .height(45.dp)
                        .background(Color(0xFF3F8AC9), shape = CircleShape)
                        .align(Alignment.Center)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_schedule_filled),
                        contentDescription = null,
                        modifier = Modifier
                            .height(30.dp)
                            .align(Alignment.Center))
                }
            }

            Image(
                painter = painterResource(id = R.drawable.icon_schedule_outline),
                contentDescription = null,
                modifier = Modifier
                    .height(30.dp)
                    .align(Alignment.Center))
        }

        if(role == "Manager") {
            Box (
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        navController.navigate(Screen.CreateScreen.route)
                    }
            ) {

                if(activePage == "Add") {
                    Box(
                        modifier = Modifier
                            .width(45.dp)
                            .height(45.dp)
                            .background(Color(0xFF3F8AC9), shape = CircleShape)
                            .align(Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.icon_add_outlined),
                            contentDescription = null,
                            modifier = Modifier
                                .height(30.dp)
                                .align(Alignment.Center))
                    }
                }

                Image(
                    painter = painterResource(id = R.drawable.icon_add_outlined),
                    contentDescription = null,
                    modifier = Modifier
                        .height(30.dp)
                        .align(Alignment.Center))
            }
        }

        Box (
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    navController.navigate(Screen.AboutScreen.route)
                }
        ) {
            if(activePage == "Info") {
                Box(
                    modifier = Modifier
                        .width(45.dp)
                        .height(45.dp)
                        .background(Color(0xFF3F8AC9), shape = CircleShape)
                        .align(Alignment.Center)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_burgermenu_filled),
                        contentDescription = null,
                        modifier = Modifier
                            .height(30.dp)
                            .align(Alignment.Center))
                }
            }

            Image(
                painter = painterResource(id = R.drawable.icons_burgermenu_outline),
                contentDescription = null,
                modifier = Modifier
                    .height(30.dp)
                    .align(Alignment.Center))
        }
    }



}

@Composable
@Preview
fun NavbarPreview() {
    val navController = rememberNavController()
    Navbar(navController = navController, "Add", "Manager")
}