package com.example.ecodrops.ui.navigation
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecodrops.ui.input.InputScreen
import com.example.ecodrops.ui.home.HomeScreen
import com.example.ecodrops.ui.info.InfoScreen
import com.example.ecodrops.ui.progress.ProgressScreen
import com.example.ecodrops.ui.reports.ReportsScreen
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.TopAppBarDefaults
import com.example.ecodrops.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(viewModel: SharedViewModel) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    "Home",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("home")
                            scope.launch { drawerState.close() }
                        }
                        .padding(16.dp)
                )
                Text(
                    "Input",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("input")
                            scope.launch { drawerState.close() }
                        }
                        .padding(16.dp)
                )

                Text(
                    "progress",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("progress")
                            scope.launch { drawerState.close() }
                        }
                        .padding(16.dp)
                )
                Text(
                    "Reports",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("reports")
                            scope.launch { drawerState.close() }
                        }
                        .padding(16.dp)
                )
                Text(
                    "Info/Tips",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("info")
                            scope.launch { drawerState.close() }
                        }
                        .padding(16.dp)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("EcoDrop") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    )
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("home") { HomeScreen(navController) }
                composable("input") { InputScreen( navController,viewModel) }
                composable("progress") { ProgressScreen( navController,viewModel) }
                composable("reports") { ReportsScreen(navController,viewModel,) }
                composable("info") { InfoScreen( )}
            }
        }
    }
}

