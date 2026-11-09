package com.example.fitnessapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitnessapp.R

@Composable
fun ActivityLoggingScreen(navController: NavController) {
    val activityName = remember { mutableStateOf("") }
    val activityDuration = remember { mutableStateOf("") }
    val activityCalories = remember { mutableStateOf("") }
    val successMessage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Log Activity",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = activityName.value,
            onValueChange = { activityName.value = it },
            label = { Text("Activity Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = activityDuration.value,
            onValueChange = { activityDuration.value = it },
            label = { Text("Duration (minutes)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = activityCalories.value,
            onValueChange = { activityCalories.value = it },
            label = { Text("Calories Burned") },
            modifier = Modifier.fillMaxWidth()
        )

        if (successMessage.value.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = successMessage.value,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (activityName.value.isNotEmpty() && activityDuration.value.isNotEmpty()) {
                    // TODO: Save activity logic
                    successMessage.value = "Activity logged successfully!"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log Activity")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("activity_retrieval")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Logged Activities")
        }
    }
}