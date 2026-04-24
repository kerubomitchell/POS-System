package com.example.possystem.ui.theme.screens.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.possystem.navigation.ROUTE_LOGIN
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    // Auto-navigate after 3 seconds
    LaunchedEffect(Unit) {
        delay(3000L)
        navController.navigate(ROUTE_LOGIN) {
            popUpTo("splash") { inclusive = true }
        }
    }

    // ── Entry animations ───────────────────────────────────────────────────
    val logoScale    = remember { Animatable(0.4f) }
    val logoAlpha    = remember { Animatable(0f) }
    val titleAlpha   = remember { Animatable(0f) }
    val taglineAlpha = remember { Animatable(0f) }
    val dotsAlpha    = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Logo bounces in from small
        logoScale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )
        )
        logoAlpha.animateTo(1f, animationSpec = tween(400))
        delay(120)
        // Title fades in
        titleAlpha.animateTo(1f, animationSpec = tween(500))
        delay(200)
        // Tagline + divider fade in
        taglineAlpha.animateTo(1f, animationSpec = tween(500))
        delay(200)
        // Loading dots appear
        dotsAlpha.animateTo(1f, animationSpec = tween(400))
    }

    // ── Infinite animations ────────────────────────────────────────────────
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")

    // Logo ring gentle pulse
    val ringScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.07f,
        animationSpec = infiniteRepeatable(
            animation = tween(1300, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ringScale"
    )

    // Ambient orb glow breathe
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.15f,
        targetValue = 0.38f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    // Bouncing dots — same animation, staggered via StartOffset
    val dot1 by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = -10f,
        animationSpec = infiniteRepeatable(
            animation = tween(420, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(0)
        ), label = "dot1"
    )
    val dot2 by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = -10f,
        animationSpec = infiniteRepeatable(
            animation = tween(420, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(150)
        ), label = "dot2"
    )
    val dot3 by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = -10f,
        animationSpec = infiniteRepeatable(
            animation = tween(420, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(300)
        ), label = "dot3"
    )

    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color(0xFF6C63FF), Color(0xFFB06AFF))
    )

    // ── UI ─────────────────────────────────────────────────────────────────
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F0F1A)),
        contentAlignment = Alignment.Center
    ) {

        // Top-left ambient orb (breathing glow)
        Box(
            modifier = Modifier
                .size(260.dp)
                .align(Alignment.TopStart)
                .offset(x = (-70).dp, y = (-70).dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF6C63FF).copy(alpha = glowAlpha),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        // Bottom-right ambient orb (breathing glow)
        Box(
            modifier = Modifier
                .size(220.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 60.dp, y = 60.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFFB06AFF).copy(alpha = glowAlpha * 0.85f),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        // Decorative concentric background rings
        Box(
            modifier = Modifier
                .size(320.dp)
                .align(Alignment.Center)
                .clip(CircleShape)
                .background(Color(0xFF6C63FF).copy(alpha = 0.05f))
        )
        Box(
            modifier = Modifier
                .size(220.dp)
                .align(Alignment.Center)
                .clip(CircleShape)
                .background(Color(0xFF6C63FF).copy(alpha = 0.07f))
        )

        // Main content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {

            // Logo — entry scale × infinite ring pulse combined
            Box(
                modifier = Modifier
                    .scale(logoScale.value * ringScale)
                    .alpha(logoAlpha.value)
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF1A1A2E))
                    .border(width = 2.dp, brush = gradientBrush, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                    tint = Color(0xFF6C63FF),
                    modifier = Modifier.size(56.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // App name
            Text(
                text = "POS PRO",
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 6.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(titleAlpha.value)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tagline
            Text(
                text = "Point of Sale System",
                fontSize = 13.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 3.sp,
                color = Color(0xFF9E9EC8),
                textAlign = TextAlign.Center,
                modifier = Modifier.alpha(taglineAlpha.value)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Gradient accent divider
            Box(
                modifier = Modifier
                    .alpha(taglineAlpha.value)
                    .width(55.dp)
                    .height(3.dp)
                    .clip(RoundedCornerShape(50))
                    .background(gradientBrush)
            )

            Spacer(modifier = Modifier.height(56.dp))

            // Bouncing loading dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.alpha(dotsAlpha.value)
            ) {
                listOf(dot1, dot2, dot3).forEachIndexed { index, offset ->
                    Box(
                        modifier = Modifier
                            .offset(y = offset.dp)
                            .size(if (index == 1) 10.dp else 8.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == 1) Color(0xFF7F77DD)
                                else Color(0xFF534AB7)
                            )
                    )
                }
            }
        }

        // Version label
        Text(
            text = "v1.0.0",
            fontSize = 11.sp,
            color = Color(0xFF9E9EC8).copy(alpha = 0.4f),
            letterSpacing = 2.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 36.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}