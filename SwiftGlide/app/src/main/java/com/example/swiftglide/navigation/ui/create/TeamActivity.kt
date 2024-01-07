package com.example.swiftglide.navigation.ui.create

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.swiftglide.R
import com.example.swiftglide.navigation.Screen
import com.example.swiftglide.navigation.data.model.ListTeam
import com.example.swiftglide.navigation.data.model.Player
import com.example.swiftglide.navigation.data.model.Team
import com.example.swiftglide.navigation.data.model.User
import com.example.swiftglide.navigation.ui.auth.HomeViewModel
import com.example.swiftglide.navigation.ui.navbar.Navbar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TeamDetailScreen(teamId: Int, createViewModel: CreateViewModel, navController: NavController, homeViewModel: HomeViewModel) {

    var team by remember { mutableStateOf(Team(0, "", emptyList())) }

    var playerEmail by remember { mutableStateOf("") }
    var playerPassword by remember { mutableStateOf("") }

    var wantsToCreatePlayer by remember { mutableStateOf(false) }

    var isPlayerNameEmpty by remember { mutableStateOf(false) }
    var isPlayerPassEmpty by remember { mutableStateOf(false) }
    var isErrorInPlayerCreation by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()

    homeViewModel.getAllUsers()
    val userList: List<User> by homeViewModel.userList.observeAsState(initial = listOf())

    LaunchedEffect(Unit) {
        coroutineScope {
            launch {
                createViewModel.getTeamById(teamId)

                delay(5000)

                createViewModel.getTeamResponse.collect { response ->
                    team = response // Update the state with the received team details
                    Log.d("wors", "TeamDetailScreen: ${response}")
                }
            }
        }
    }

    // Compose UI
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
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

        if (wantsToCreatePlayer) {

            Title("Add Player")

            Spacer(modifier = Modifier.height(20.dp))

            PlayerEmailInput(playerEmail) { newPlayerEmail ->
                playerEmail = newPlayerEmail
                isPlayerNameEmpty = false
            }

            if (isPlayerNameEmpty) {
                Text(text = "Player email must not be empty", color = Color.Red)
            }

            Spacer(modifier = Modifier.height(20.dp))

            PlayerPasswordInput(playerPassword) { newPlayerPassword ->
                playerPassword = newPlayerPassword
                isPlayerPassEmpty = false
            }

            if (isPlayerPassEmpty) {
                Text(text = "Player password must not be empty", color = Color.Red)
            }

            PlayerButtons(
                onPlayerCreationClick = {
                    if (playerEmail.isEmpty()) {
                        isPlayerNameEmpty = true
                    } else {
                        if (playerPassword.isEmpty()) {
                            isPlayerPassEmpty = true
                        } else {
                            createViewModel.createPlayer(team.id, playerEmail, playerPassword)


                            coroutineScope.launch {
                                delay(2000)
                                createViewModel.createPlayerResponse.collect { createPlayerResponse ->
                                    if(createPlayerResponse.isCreated) {
                                        //
                                        isErrorInPlayerCreation = false
                                        errorMessage = createPlayerResponse.message
                                    } else {
                                        //display error
                                        isErrorInPlayerCreation = true
                                        errorMessage = createPlayerResponse.message
                                    }
                                }
                            }
                            // Reset variables or perform any other necessary actions
                            playerEmail = ""
                            playerPassword= ""
                            wantsToCreatePlayer = false

                            coroutineScope.launch {
                                createViewModel.getTeamById(teamId)

                                delay(5000)

                                createViewModel.getTeamResponse.collect { response ->
                                    team = response // Update the state with the received team details
                                }
                            }
                        }
                    }
                },
                onCancelClick = { wantsToCreatePlayer = false }
            )

            Spacer(modifier = Modifier.weight(1f))

            var role = "Player"
            if (userList.isNotEmpty()) {
                role = userList[0].role
            }

            Navbar(navController, "Add", role)


        } else {
            Title(team.name)

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp, 0.dp)
            ) {
                PlayerList(team.playerList, navController)
            }

            // Floating action button for some action (e.g., adding a player)
            FloatingActionButton(
                onClick = {
                    isErrorInPlayerCreation = false
                    wantsToCreatePlayer = true
                },
                modifier = Modifier
                    .padding(16.dp),
                containerColor = Color(0xFF3F8AC9),
                contentColor = Color.White,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_add_outlined),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }

            var role = "Player"
            if (userList.isNotEmpty()) {
                role = userList[0].role
            }

            Navbar(navController, "Add", role)
        }


    }
}


@Composable
private fun PlayerList(players: List<Player>, navController: NavController) {
    Box {
        LazyColumn(

        ) {
            items(players, key = { player -> player.email}) { player ->
                PlayerItem(player, navController)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun PlayerItem(player: Player, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF163949))

        ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_avatar),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(10f)
                .fillMaxHeight()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = player.email,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                )
            }
        }
    }
}

@Composable
private fun PlayerButtons(onPlayerCreationClick: () -> Unit, onCancelClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onPlayerCreationClick,
            modifier = Modifier
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
        ) {
            Text(text = "Create player")
        }

        Button(
            onClick = onCancelClick,
            modifier = Modifier
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336)),
        ) {
            Text(text = "Cancel")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlayerEmailInput(playerEmail: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = playerEmail,
            onValueChange = onValueChange,
            label = null,
            placeholder = {
                Text(
                    text = "Player email",
                    color = Color.White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(22, 57, 73),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            ),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            modifier = Modifier
                .height(50.dp)
                .border(
                    1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(0.dp)
                .width(300.dp),
            shape = RoundedCornerShape(10.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlayerPasswordInput(playerPassword: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = playerPassword,
            onValueChange = onValueChange,
            label = null,
            placeholder = {
                Text(
                    text = "Player password",
                    color = Color.White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(22, 57, 73),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            ),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            modifier = Modifier
                .height(50.dp)
                .border(
                    1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(0.dp)
                .width(300.dp),
            shape = RoundedCornerShape(10.dp)
        )
    }
}

/*
@Preview
@Composable
fun TeamDetailScreenPreview() {
    TeamDetailScreen(1)
}*/