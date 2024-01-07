package com.example.swiftglide.navigation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.swiftglide.navigation.ui.auth.AuthViewModel
import com.example.swiftglide.navigation.ui.auth.HomeViewModel
import com.example.swiftglide.navigation.ui.create.CreateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel
    private lateinit var createViewModel: CreateViewModel
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authViewModel = ViewModelProvider(this, AuthViewModel.createFactory()).get(AuthViewModel::class.java)
        createViewModel = ViewModelProvider(this, CreateViewModel.createFactory()).get(CreateViewModel::class.java)

        setContent {
            Navigation(authViewModel=authViewModel, createViewModel=createViewModel, homeViewModel=homeViewModel)
        }
    }

}