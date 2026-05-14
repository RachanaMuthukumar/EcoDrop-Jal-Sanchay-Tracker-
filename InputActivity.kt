package com.example.ecodrops.ui.input

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ecodrops.viewmodel.SharedViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun InputScreen(navController: NavController, viewModel: SharedViewModel) {

    var area by remember { mutableStateOf("") }
    var areaError by remember { mutableStateOf(false) }
    var tank by remember { mutableStateOf("") }
    var tankError by remember { mutableStateOf(false) }
    var rainfallError by remember { mutableStateOf(false) }
    var rainfall by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, top = 100.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = "Hello User, Welcome!\nPlease enter the details",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = area,
            onValueChange = {
                area = it
                areaError = it.toFloatOrNull() == null
            },
            label = { Text("Enter Roof Area (sq.m)") },
            isError = areaError,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )

        if (areaError) {
            Text(
                text = "Please enter a number",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }

        TextField(
            value = tank,
            onValueChange = {
                tank = it
                tankError = it.toIntOrNull() == null
            },
            label = { Text("Enter Tank Capacity (liters)") },
            isError = tankError,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )
        if (tankError) {
            Text("Please enter a number", color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        TextField(
            value = rainfall,
            onValueChange = {
                rainfall = it
                rainfallError = it.toFloatOrNull() == null
            },
            label = { Text("Enter Rainfall (mm)") },
            isError = rainfallError,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )
        if (rainfallError) {
            Text("Please enter a number", color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }
        val isFormValid = !areaError && !tankError && !rainfallError &&
                area.isNotEmpty() && tank.isNotEmpty() && rainfall.isNotEmpty()

        Button(
            onClick = {
                val areaValue = area.toFloatOrNull() ?: 0f
                val tankValue = tank.toFloatOrNull() ?: 0f
                val rainfallValue = rainfall.toFloatOrNull() ?: 0f

                viewModel.addRain(areaValue, tankValue, rainfallValue)
                navController.navigate("progress")
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }


    }
}



