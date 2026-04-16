package com.example.possystem.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.possystem.ui.theme.screens.login.LoginScreen
import com.example.possystem.ui.theme.screens.register.RegisterScreen
import com.example.possystem.ui.theme.screens.dashboard.DashboardScreen
import com.example.possystem.navigation.ROUTE_LOGIN
import com.example.possystem.navigation.ROUTE_REGISTER
import com.example.possystem.navigation.ROUTE_DASHBOARD
import com.example.possystem.ui.theme.screens.product.AddProductScreen


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_ADD_PRODUCT
) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }

        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }

        composable(ROUTE_DASHBOARD) {
            DashboardScreen(navController)
        }
        composable(ROUTE_ADD_PRODUCT) {
            AddProductScreen(navController)
        }
    }
}