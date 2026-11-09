package com.example.fitnessapp.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginScreen_ValidInput_NavigatesToActivityLogging() {
        val navController = mock(NavController::class.java)
        composeTestRule.setContent {
            LoginScreen(navController = navController)
        }

        composeTestRule.onNodeWithText("Email").performTextInput("test@example.com")
        composeTestRule.onNodeWithText("Password").performTextInput("password")
        composeTestRule.onNodeWithText("Login").performClick()

        verify(navController).navigate("activity_logging")
    }

    @Test
    fun loginScreen_InvalidInput_ShowsError() {
        composeTestRule.setContent {
            LoginScreen(navController = mock(NavController::class.java))
        }

        composeTestRule.onNodeWithText("Login").performClick()
        composeTestRule.onNodeWithText("Invalid email or password").assertExists()
    }
}