package com.example.fitnessapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.ui.screens.LoginScreen

@Composable
fun FitnessAppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("activity_logging") {
            ActivityLoggingScreen(navController = navController)
        }
        composable("activity_retrieval") {
            ActivityRetrievalScreen(navController = navController)
        }
        composable("real_time_updates") {
            RealTimeUpdatesScreen(navController = navController, webSocketManager = WebSocketManager())
        }
    }
}