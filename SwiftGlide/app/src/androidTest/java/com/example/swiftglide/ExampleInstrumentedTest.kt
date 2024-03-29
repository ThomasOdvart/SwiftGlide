package com.example.swiftglide

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.swiftglide", appContext.packageName)
    }

    @get:Rule
    val composeTestRule = createComposeRule()

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