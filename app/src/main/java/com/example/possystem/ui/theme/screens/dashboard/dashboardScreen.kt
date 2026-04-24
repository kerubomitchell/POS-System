package com.example.possystem.ui.theme.screens.dashboard

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.possystem.data.AuthViewModel
import com.example.possystem.navigation.ROUTE_ADD_PRODUCT
import com.example.possystem.navigation.ROUTE_PRODUCT_LIST
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*

// ── Theme colours (shared across app) ────────────────────────────────────────
private val BgDark      = Color(0xFF0F0F1A)
private val SurfaceDark = Color(0xFF1A1A2E)
private val PurplePrim  = Color(0xFF6C63FF)
private val PurpleSec   = Color(0xFFB06AFF)
private val TextMuted   = Color(0xFF9E9EC8)
private val BorderIdle  = Color(0xFF2E2E4E)

private val GradientBrush = Brush.linearGradient(listOf(PurplePrim, PurpleSec))

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController) {

    var selectedItem by remember { mutableStateOf(0) }
    val authViewModel: AuthViewModel = viewModel()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
    ) {
        // Decorative glows
        Box(
            modifier = Modifier
                .size(280.dp)
                .offset(x = (-80).dp, y = (-80).dp)
                .background(
                    Brush.radialGradient(listOf(Color(0x336C63FF), Color.Transparent)),
                    CircleShape
                )
        )
        Box(
            modifier = Modifier
                .size(220.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 60.dp, y = 60.dp)
                .background(
                    Brush.radialGradient(listOf(Color(0x33B06AFF), Color.Transparent)),
                    CircleShape
                )
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(GradientBrush, RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.PointOfSale,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                "POS Dashboard",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                    actions = {
                        IconButton(onClick = { authViewModel.logout(navController, context) }) {
                            Icon(
                                Icons.Default.ExitToApp,
                                contentDescription = "Logout",
                                tint = TextMuted
                            )
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = SurfaceDark,
                    tonalElevation = 0.dp,
                    modifier = Modifier.border(
                        width = 1.dp,
                        color = BorderIdle,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
                ) {
                    val navItems = listOf(
                        Triple("Home",     Icons.Default.Home,      0),
                        Triple("Products", Icons.Default.Inventory,  1),
                        Triple("Stock",    Icons.Default.BarChart,   2)
                    )
                    navItems.forEach { (label, icon, index) ->
                        NavigationBarItem(
                            selected = selectedItem == index,
                            onClick = { selectedItem = index },
                            icon = {
                                Icon(
                                    icon,
                                    contentDescription = label,
                                    modifier = Modifier.size(22.dp)
                                )
                            },
                            label = { Text(label, fontSize = 11.sp) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor   = PurplePrim,
                                selectedTextColor   = PurplePrim,
                                unselectedIconColor = TextMuted,
                                unselectedTextColor = TextMuted,
                                indicatorColor      = PurplePrim.copy(alpha = 0.15f)
                            )
                        )
                    }
                }
            }
        ) { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                // ── Header ────────────────────────────────────────────────────
                Column {
                    Text(
                        "Business Overview",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text("Track your sales and inventory", fontSize = 14.sp, color = TextMuted)
                }

                // ── Revenue card ──────────────────────────────────────────────
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(GradientBrush)
                        .clickable { navController.navigate(ROUTE_PRODUCT_LIST) }
                        .padding(20.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.TrendingUp,
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.8f),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Today's Revenue", color = Color.White.copy(alpha = 0.85f), fontSize = 14.sp)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "KES 12,500",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("↑ 12% from yesterday", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                    }
                    Icon(
                        Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.6f),
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(28.dp)
                    )
                }

                // ── Quick action cards ────────────────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    QuickActionCard(
                        title = "New Product",
                        icon = Icons.Default.AddBox,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate(ROUTE_ADD_PRODUCT) }
                    )
                    QuickActionCard(
                        title = "Products",
                        icon = Icons.Default.Inventory,
                        modifier = Modifier.weight(1f),
                        onClick = { navController.navigate(ROUTE_PRODUCT_LIST) }
                    )
                    QuickActionCard(
                        title = "Stock",
                        icon = Icons.Default.BarChart,
                        modifier = Modifier.weight(1f)
                    )
                }

                // ── Stats row ─────────────────────────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StatCard("48", "Orders", Icons.Default.ShoppingCart, Modifier.weight(1f))
                    StatCard("12", "Low Stock", Icons.Default.Warning, Modifier.weight(1f), warnColor = true)
                }

                // ── Charts ────────────────────────────────────────────────────
                Text(
                    "Analytics",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ChartCard("Sales Mix", Modifier.weight(1f)) {
                        PieChartView(Modifier.fillMaxWidth())
                    }
                    ChartCard("Weekly Sales", Modifier.weight(1f)) {
                        BarChartView(Modifier.fillMaxWidth())
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

// ── Quick action card ─────────────────────────────────────────────────────────
@Composable
fun QuickActionCard(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceDark)
            .border(1.dp, BorderIdle, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(PurplePrim.copy(alpha = 0.15f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = PurplePrim, modifier = Modifier.size(22.dp))
        }
        Text(title, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}

// ── Stat card ─────────────────────────────────────────────────────────────────
@Composable
fun StatCard(
    value: String,
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    warnColor: Boolean = false
) {
    val accent = if (warnColor) Color(0xFFFFB74D) else PurpleSec
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceDark)
            .border(1.dp, BorderIdle, RoundedCornerShape(16.dp))
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(accent.copy(alpha = 0.15f), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = accent, modifier = Modifier.size(22.dp))
        }
        Column {
            Text(value, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold)
            Text(label, color = TextMuted, fontSize = 12.sp)
        }
    }
}

// ── Chart wrapper card ────────────────────────────────────────────────────────
@Composable
fun ChartCard(title: String, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(SurfaceDark)
            .border(1.dp, BorderIdle, RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        Text(title, color = TextMuted, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

// ── Pie chart ─────────────────────────────────────────────────────────────────
@Composable
fun PieChartView(modifier: Modifier = Modifier) {
    val entries = listOf(
        PieEntry(40f, "A"), PieEntry(30f, "B"),
        PieEntry(20f, "C"), PieEntry(10f, "D")
    )
    val dataSet = PieDataSet(entries, "").apply {
        colors = listOf(
            PurplePrim.toArgb(), PurpleSec.toArgb(),
            Color(0xFF4FC3F7).toArgb(), Color(0xFFB39DDB).toArgb()
        )
        valueTextColor = Color.White.toArgb()
    }
    AndroidView(
        factory = { ctx ->
            PieChart(ctx).apply {
                data = PieData(dataSet)
                description.isEnabled = false
                legend.isEnabled = false
                setBackgroundColor(android.graphics.Color.TRANSPARENT)
                setHoleColor(android.graphics.Color.TRANSPARENT)
                holeRadius = 36f
                invalidate()
            }
        },
        modifier = modifier.height(160.dp)
    )
}

// ── Bar chart ─────────────────────────────────────────────────────────────────
@Composable
fun BarChartView(modifier: Modifier = Modifier) {
    val entries = listOf(
        BarEntry(0f, 100f), BarEntry(1f, 200f),
        BarEntry(2f, 150f), BarEntry(3f, 80f)
    )
    val dataSet = BarDataSet(entries, "").apply {
        colors = listOf(
            PurplePrim.toArgb(), PurpleSec.toArgb(),
            Color(0xFF4FC3F7).toArgb(), Color(0xFFB39DDB).toArgb()
        )
        valueTextColor = Color.White.toArgb()
    }
    AndroidView(
        factory = { ctx ->
            BarChart(ctx).apply {
                data = BarData(dataSet)
                description.isEnabled = false
                legend.isEnabled = false
                setBackgroundColor(android.graphics.Color.TRANSPARENT)
                xAxis.textColor = android.graphics.Color.WHITE
                axisLeft.textColor = android.graphics.Color.WHITE
                axisRight.isEnabled = false
                invalidate()
            }
        },
        modifier = modifier.height(160.dp)
    )
}

// ── Legacy ProductCard alias (kept for nav compatibility) ─────────────────────
@Composable
fun ProductCard(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    QuickActionCard(
        title = title,
        icon = Icons.Default.Inventory,
        modifier = modifier,
        onClick = onClick
    )
}