package com.ventasbroncaapp.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ventasbroncaapp.ui.screens.Screen
import com.ventasbroncaapp.ui.screens.ComprarScreen
import com.ventasbroncaapp.ui.screens.CarritoScreen
import com.ventasbroncaapp.ui.screens.YoScreen
import com.ventasbroncaapp.ui.cart.CartViewModel

@Composable
fun NavGraph(navController: NavHostController, cartViewModel: CartViewModel = viewModel()) {
    NavHost(navController, startDestination = Screen.Comprar.route) {
        composable(Screen.Comprar.route) { ComprarScreen(cartViewModel) }
        composable(Screen.Carrito.route) { CarritoScreen(cartViewModel) }
        composable(Screen.Yo.route) { YoScreen() }
    }
}