package com.example.ecodrops.ui.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecodrops.R

@Composable
fun InfoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Banner Image
        Image(
            painter = painterResource(id = R.drawable.info),
            contentDescription = "Rainwater harvesting illustration",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Crop
        )

        // Intro Text
        Text(
            text = "Rainwater harvesting is one of the most effective ways to conserve water — simple methods like rooftop collection, recharge pits, and rain barrels can save thousands of liters per season while reducing dependence on municipal supply",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tips Section Header
        Text(
            text = "Tips for Rainwater Harvesting",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(2.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary,   // background color
                contentColor = MaterialTheme.colorScheme.onPrimary    // text/icon color
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("• Rooftop Collection & Filtration: ")
                        }
                        append(" Install rooftop pipes connected to a drum or tank. Use layers of charcoal, sand, and gravel to filter impurities.") // ✅ normal part
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("• Recharge Pits: ")
                        }
                        append(" Dig a small pit filled with gravel, stones, and sand in your backyard or driveway. Connect rooftop pipes to the pit so rainwater percolates into the soil.")
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp
                )
                Spacer(Modifier.height(8.dp))

                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("• Rain Barrels: ")
                        }
                        append(" Place plastic or metal barrels under downspouts. Cover with fine mesh to prevent debris and mosquitoes.")
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp
                )
                Spacer(Modifier.height(8.dp))

                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("• Ground-Level Harvesting: ")
                        }
                        append(" Use percolation tanks, check dams, or soak pits in larger plots. Helps reduce runoff and recharge aquifers.")
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 20.sp
                )
            }
        }


    }
}
@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    InfoScreen() // no navController or viewModel needed
}

