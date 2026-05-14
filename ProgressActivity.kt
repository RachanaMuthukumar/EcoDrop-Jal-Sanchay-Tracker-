package com.example.ecodrops.ui.progress

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecodrops.viewmodel.SharedViewModel


@Composable
fun ProgressScreen(navController: NavController, viewModel: SharedViewModel) {
    // Collect StateFlow values from ViewModel
    val progress by viewModel.progress.collectAsState()
   // val tankCapacity by viewModel.tankCapacity.collectAsState()
    //val currentWater by viewModel.currentWater.collectAsState()

    // Calculate water days (example: simple division)
    val days = viewModel.getWaterDays()// adjust divisor as per your logic

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tank Progress",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Tank visualization
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(300.dp)
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                .border(3.dp, Color.Gray, RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(progress) // reactive fill
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Cyan, Color.Blue)
                        )
                    )
                    .align(Alignment.BottomCenter)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Filled: ${(progress * 100).toInt()}%", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Water Days: $days", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.navigate("reports") }) {
            Text("View Reports")
        }
        Button(onClick = { viewModel.resetCurrentWater() }) {
            Text("Empty Tank")
        }

    }
}

