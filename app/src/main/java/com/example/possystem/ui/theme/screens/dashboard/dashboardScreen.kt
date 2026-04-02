package com.example.possystem.ui.theme.screens.dashboard

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(){
    var selectedItem by remember { mutableStateOf(0) }
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "POS Dashboard",
            fontSize = 18.sp,
            color = Color.Blue,
            fontWeight = FontWeight.ExtraBold,) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black,
                titleContentColor = Color.White,
                )

        ) },

        bottomBar = { NavigationBar(containerColor = Color.Black) {
            NavigationBarItem(
                selected = selectedItem == 0,
                onClick = { selectedItem = 0},
                icon = { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = null) },
                label = {Text(text = "Home")}
            )
            NavigationBarItem(
                selected = selectedItem == 0,
                onClick = { selectedItem = 0},
                icon = { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = null) },
                label = {Text(text = "Home")}
            )
            NavigationBarItem(
                selected = selectedItem == 0,
                onClick = { selectedItem = 0},
                icon = { androidx.compose.material3.Icon(Icons.Default.Home, contentDescription = null) },
                label = {Text(text = "Home")}
            )
        } }
    )
    {padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text(text = "Business Overview",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp) ,
                colors = CardDefaults.cardColors(containerColor = Color.Black),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = "Today's Revenue", color = Color.White)


                    Text(
                        text = "KES 12,500",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White)
                }
                Spacer(modifier = Modifier.height(30.dp))
                    Text(text = "Remains" )

            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Black),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Text(
                        text = "New Product",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                Card(
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Black),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Text(
                        text = "Products",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
            }

            }

        }
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardPreview(){
    Dashboard()
}

