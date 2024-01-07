package com.example.swiftglide

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.lifecycle.Lifecycle
import com.example.swiftglide.navigation.MainActivity
import junit.framework.TestCase.assertTrue
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test

class Tests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun loginScreen_emailValidation() {
        // Perform actions
        composeTestRule.onNodeWithText("LOGIN").performClick()

        // Assert that the error message is displayed for an empty email
        composeTestRule
            .onNodeWithText("Email must not be empty")
            .assertIsDisplayed()
    }

    @Test
    fun loginScreen_successfulLogin() {
        // Perform actions for a successful login (e.g., entering valid email and password)

        // For example, enter valid email and password
        composeTestRule.onNodeWithTag("emailField").performTextInput("user@example.com")
        composeTestRule.onNodeWithTag("passwordField").performTextInput("password")

        // Click the login button
        composeTestRule.onNodeWithText("LOGIN").performClick()

        composeTestRule
            .onNodeWithText("Welcome to HomeScreen") // Replace with the actual text or UI element on HomeScreen
            .assertExists()
    }

}