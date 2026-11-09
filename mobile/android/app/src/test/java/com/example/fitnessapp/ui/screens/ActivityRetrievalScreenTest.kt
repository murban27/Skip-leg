package com.example.fitnessapp.ui.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityRetrievalScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun activityRetrievalScreen_DisplaysActivities() {
        composeTestRule.setContent {
            ActivityRetrievalScreen(navController = mock(NavController::class.java))
        }

        composeTestRule.onNodeWithText("Running").assertExists()
        composeTestRule.onNodeWithText("Duration: 30 minutes").assertExists()
        composeTestRule.onNodeWithText("Calories Burned: 300").assertExists()
    }
}