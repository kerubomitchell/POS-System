package com.example.possystem.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.possystem.ui.theme.screens.login.LoginScreen
import com.example.possystem.ui.theme.screens.register.RegisterScreen
import com.example.possystem.ui.theme.screens.dashboard.DashboardScreen
import com.example.possystem.navigation.ROUTE_LOGIN
import com.example.possystem.navigation.ROUTE_REGISTER
import com.example.possystem.navigation.ROUTE_DASHBOARD
import com.example.possystem.ui.theme.screens.product.AddProductScreen
import com.example.possystem.ui.theme.screens.product.ProductListScreen
import com.example.possystem.ui.theme.screens.product.UpdateProductScreen
import com.example.possystem.ui.theme.screens.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH
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
        composable(ROUTE_PRODUCT_LIST) {
            ProductListScreen(navController)
        }
        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }
        composable(
            ROUTE_UPDATE_PRODUCT,
            arguments = listOf(
                navArgument("productId") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val productId = backStackEntry.arguments?.getString("productId")!!
            UpdateProductScreen(navController, productId)
        }


    }
}