package com.example.fitnessapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ActivityRetrievalScreen(navController: NavController) {
    // Mock data for logged activities
    val activities = remember {
        mutableStateOf(
            listOf(
                Activity("Running", "30", "300"),
                Activity("Cycling", "45", "400"),
                Activity("Swimming", "60", "500")
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Logged Activities",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(activities.value) { activity ->
                ActivityCard(activity = activity)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ActivityCard(activity: Activity) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = activity.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Duration: ${activity.duration} minutes",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Calories Burned: ${activity.calories}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

data class Activity(
    val name: String,
    val duration: String,
    val calories: String
)