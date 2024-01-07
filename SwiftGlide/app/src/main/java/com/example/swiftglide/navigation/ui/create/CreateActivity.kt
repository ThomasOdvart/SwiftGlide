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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.navigation.compose.rememberNavController
import com.example.swiftglide.R
import com.example.swiftglide.navigation.Screen
import com.example.swiftglide.navigation.data.model.Team
import com.example.swiftglide.navigation.ui.navbar.Navbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen(navController: NavController, createViewModel: CreateViewModel) {
    var wantsToCreateTeam by remember { mutableStateOf(false) }

    var isTeamNameEmpty by remember { mutableStateOf(false) }

    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var teamName by remember { mutableStateOf("") }

    var fakeTeams by remember {
        mutableStateOf(mutableStateListOf(
        Team(2, "Team 1", 20),
        ))
    }

    val coroutineScope = rememberCoroutineScope()

    if (wantsToCreateTeam) {

        Column(
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

            Title("Add Team")

            Spacer(modifier = Modifier.height(20.dp))

            TeamNameInput(teamName) { newTeamName ->
                teamName = newTeamName
                isTeamNameEmpty = false
            }

            if (isTeamNameEmpty) {
                Text(text = "Team name must not be empty", color = Color.Red)
            }

            TeamNameButtons(
                onTeamCreationClick = {
                      if (teamName.isEmpty()) {
                          isTeamNameEmpty = true
                      } else {
                          //create team

                          coroutineScope.launch {
                              createViewModel.createTeam(teamName, 0, "thomas.odvart@gmail.com")
                              Log.d("lauch", "CreateViewModle called")

                              delay(2000)

                              createViewModel.createTeamResponse.collect { createTeamResponse ->
                                  Log.d("response", "Signupscreen: $createTeamResponse")
                              }

                          }

                          // Reset variables or perform any other necessary actions
                          teamName = ""
                          wantsToCreateTeam = false
                      }
                },
                onCancelClick = { wantsToCreateTeam = false }
            )

            Spacer(modifier = Modifier.weight(1f))

            Navbar(navController, "Add", "Manager")

        }

    } else {

        Scaffold(
            content = {
                Column(
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
                        .padding(it) // Apply contentPadding
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp)
                    ) {


                        Title("Your Teams")

                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp, 0.dp)
                    ) {
                        TeamList(fakeTeams, navController)
                    }
                    Navbar(navController, "Add", "Manager")

                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        // Handle the click event
                        wantsToCreateTeam = true
                    },
                    modifier = Modifier
                        .padding(0.dp, 70.dp),
                    containerColor = Color(0xFF3F8AC9),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_add_outlined),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
        )

    }
}

@Composable
fun Title(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
    ) {


        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .height(50.dp)
        ) {
            Text(
                text = title,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.ExtraBold,
            )

            Surface(
                color = Color(0xFF3F8AC9),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(175.dp)
                    .height(5.dp)
                    .align(Alignment.BottomCenter)
            ) {

            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TeamNameInput(teamName: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = teamName,
            onValueChange = onValueChange,
            label = null,
            placeholder = {
                Text(
                    text = "Team name",
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

@Composable
private fun TeamNameButtons(onTeamCreationClick: () -> Unit, onCancelClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onTeamCreationClick,
            modifier = Modifier
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
        ) {
            Text(text = "Create team")
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

@Composable
private fun TeamList(teams: List<Team>, navController: NavController) {
    Box {
        LazyColumn(

        ) {
            items(teams, key = { team -> team.name}) { team ->
                TeamListItem(team, navController)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun TeamListItem(team: Team, navController: NavController) {
    Row(
       modifier = Modifier
           .fillMaxWidth()
           .height(75.dp)
           .clip(RoundedCornerShape(10.dp))
           .background(Color(0xFF163949))
           .clickable {
               navController.navigate(Screen.TeamDetailScreen.withArgs(team.id))
           },

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
                Text(
                    text = team.name,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_avatar),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    text = team.amountOfPlayers.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    )
            }
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_arrow),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

/*
@Composable
@Preview
fun HomeScreenPreview() {
    val navController = rememberNavController()
    CreateScreen(navController = navController)
}*/