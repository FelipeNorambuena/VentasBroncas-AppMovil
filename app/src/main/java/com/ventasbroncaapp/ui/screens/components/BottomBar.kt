package com.ventasbroncaapp.ui.screens.components

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ventasbroncaapp.ui.cart.CartViewModel
import com.ventasbroncaapp.ui.screens.Screen

@Composable
fun BottomBar(navController: NavHostController, cartViewModel: CartViewModel = viewModel()) {
    val screens = listOf(Screen.Comprar, Screen.Carrito, Screen.Yo)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    // usar el ViewModel pasado para mostrar la cantidad
    val cartCount = cartViewModel.items.size

    NavigationBar {
        screens.forEach { screen ->
            val isCart = screen is Screen.Carrito
            val icon = if (isCart) {
                // mostrar badge con la cantidad
                @Composable {
                    BadgedBox(badge = {
                        if (cartCount > 0) {
                            Badge { Text(cartCount.toString()) }
                        }
                    }) {
                        Icon(painter = painterResource(id = screen.iconRes), contentDescription = screen.label)
                    }
                }
            } else {
                @Composable { Icon(painter = painterResource(id = screen.iconRes), contentDescription = screen.label) }
            }

            NavigationBarItem(
                icon = icon,
                label = { Text(screen.label) },
                selected = currentDestination == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}