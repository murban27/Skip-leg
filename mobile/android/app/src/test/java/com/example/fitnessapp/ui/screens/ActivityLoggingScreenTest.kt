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
class ActivityLoggingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun activityLoggingScreen_ValidInput_ShowsSuccessMessage() {
        composeTestRule.setContent {
            ActivityLoggingScreen(navController = mock(NavController::class.java))
        }

        composeTestRule.onNodeWithText("Activity Name").performTextInput("Running")
        composeTestRule.onNodeWithText("Duration (minutes)").performTextInput("30")
        composeTestRule.onNodeWithText("Log Activity").performClick()

        composeTestRule.onNodeWithText("Activity logged successfully!").assertExists()
    }

    @Test
    fun activityLoggingScreen_NavigatesToActivityRetrieval() {
        val navController = mock(NavController::class.java)
        composeTestRule.setContent {
            ActivityLoggingScreen(navController = navController)
        }

        composeTestRule.onNodeWithText("View Logged Activities").performClick()
        verify(navController).navigate("activity_retrieval")
    }
}