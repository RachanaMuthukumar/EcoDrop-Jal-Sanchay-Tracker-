package com.example.ecodrops.ui.reports

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecodrops.viewmodel.SharedViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun ReportsScreen(navController: NavController, viewModel: SharedViewModel) {
    val tankCapacity by viewModel.tankCapacity.collectAsState(initial = 0)
    val currentWater by viewModel.currentWater.collectAsState(initial = 0)
    val progress by viewModel.progress.collectAsState(initial = 0f)
    val dailySaved by viewModel.dailySaved.collectAsState(initial = 0f)
    val rainfallToday by viewModel.rainfallToday.collectAsState(initial = 0f)
    val totalSaved by viewModel.totalSaved.collectAsState(initial = 0f)

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Reports", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Tank section
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Tank Capacity: $tankCapacity L")
                Text("Current Water: $currentWater L")
                Text("Progress: ${(progress * 100).toInt()}%")
            }
        }

        // Today’s report section
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Today’s Report", style = MaterialTheme.typography.titleMedium)
                Text("Rainfall: $rainfallToday mm")
                Text("Liters Saved: ${dailySaved.toInt()} L")
            }
        }

        // Total report section
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Total Report", style = MaterialTheme.typography.titleMedium)
                Text("Total Liters Saved: ${totalSaved.toInt()} L")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate("progress") }) {
            Text("Back to Progress")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { viewModel.reset() }) {
            Text("Reset")
        }
    }
}
