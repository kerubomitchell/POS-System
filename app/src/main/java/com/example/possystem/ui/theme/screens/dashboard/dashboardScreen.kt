package com.example.possystem.ui.theme.screens.dashboard

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.possystem.data.AuthViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {

    var selectedItem by remember { mutableStateOf(0) }

    val authViewModel: AuthViewModel = viewModel()
    val context = LocalContext.current
    val activity = context as? Activity

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "POS Dashboard",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        authViewModel.logout(navController, context)
                        // Optional: close app completely
                        // activity?.finishAffinity()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Logout",
                            tint = Color.White
                        )
                    }
                }
            )
        },

        bottomBar = {
            NavigationBar(containerColor = Color.Black) {

                NavigationBarItem(
                    selected = selectedItem == 0,
                    onClick = { selectedItem = 0 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = selectedItem == 1,
                    onClick = { selectedItem = 1 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Products") },
                    label = { Text("Products") }
                )

                NavigationBarItem(
                    selected = selectedItem == 2,
                    onClick = { selectedItem = 2 },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Stock") },
                    label = { Text("Stock") }
                )
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {

            Text(
                text = "Business Overview",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Revenue Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = "Today's Revenue", color = Color.White)
                    Text(
                        text = "KES 12,500",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Product Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProductCard("New Product", Modifier.weight(1f))
                ProductCard("Products", Modifier.weight(1f))
                ProductCard("Stock", Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Charts
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PieChartView(modifier = Modifier.weight(1f))
                BarChartView(modifier = Modifier.weight(1f))
            }
        }
    }
}


@Composable
fun ProductCard(title: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
    }
}



@Composable
fun PieChartView(modifier: Modifier = Modifier) {

    val entries = listOf(
        PieEntry(40f, "A"),
        PieEntry(30f, "B"),
        PieEntry(20f, "C"),
        PieEntry(10f, "D")
    )

    val dataSet = PieDataSet(entries, "").apply {
        colors = listOf(
            Color(0xFF1E88E5).toArgb(),
            Color(0xFFD32F2F).toArgb(),
            Color(0xFF388E3C).toArgb(),
            Color(0xFFFBC02D).toArgb()
        )
    }

    val data = PieData(dataSet)

    AndroidView(
        factory = { context ->
            PieChart(context).apply {
                this.data = data
                description.isEnabled = false
                legend.isEnabled = true
                invalidate()
            }
        },
        modifier = modifier.height(200.dp)
    )
}


@Composable
fun BarChartView(modifier: Modifier = Modifier) {

    val entries = listOf(
        BarEntry(0f, 100f),
        BarEntry(1f, 200f),
        BarEntry(2f, 150f),
        BarEntry(3f, 80f)
    )

    val dataSet = BarDataSet(entries, "").apply {
        colors = listOf(
            Color(0xFF1E88E5).toArgb(),
            Color(0xFFD32F2F).toArgb(),
            Color(0xFF388E3C).toArgb(),
            Color(0xFFFBC02D).toArgb()
        )
    }

    val data = BarData(dataSet)

    AndroidView(
        factory = { context ->
            BarChart(context).apply {
                this.data = data
                description.isEnabled = false
                legend.isEnabled = true
                invalidate()
            }
        },
        modifier = modifier.height(200.dp)
    )
}