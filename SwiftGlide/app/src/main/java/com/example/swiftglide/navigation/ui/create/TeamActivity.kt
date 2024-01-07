package com.example.swiftglide.navigation.ui.create

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.swiftglide.navigation.data.model.Team
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TeamDetailScreen(teamId: Int, createViewModel: CreateViewModel) {

    var team by remember { mutableStateOf(Team(0, "", emptyList())) }

    LaunchedEffect(Unit) {
        coroutineScope {
            launch {

                createViewModel.getTeamById(teamId)

                delay(5000)
                createViewModel.getTeamResponse.collect { response ->
                    team = response // Update the state with the received teams
                    Log.d("teams", "CreateScreen: $team")
                }
            }
        }
    }


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
        Title(team.name)

        Log.d("playerlist", "TeamDetailScreen: ${team.playerList}")
    }
}

/*
@Preview
@Composable
fun TeamDetailScreenPreview() {
    TeamDetailScreen(1)
}*/