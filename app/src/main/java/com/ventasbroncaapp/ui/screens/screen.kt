package com.ventasbroncaapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items as gridItems
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import com.ventasbroncaapp.ui.screens.components.ProductCard
import com.ventasbroncaapp.ui.cart.CartViewModel

sealed class Screen(val route: String, val label: String, val iconRes: Int) {
    object Comprar : Screen("comprar", "Comprar", android.R.drawable.ic_menu_compass)
    object Carrito : Screen("carrito", "Carrito", android.R.drawable.ic_menu_view)
    object Yo : Screen("yo", "Yo", android.R.drawable.ic_menu_myplaces)
}

// Product model used by ProductCard
data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val description: String,
    val imageRes: List<Int>
)

// Sample products — cada uno tiene 1 o más imágenes
val sampleProducts = listOf(
    Product(1, "Camiseta Azul", "$19.990", "Cómoda camiseta de algodón.", listOf(android.R.drawable.ic_menu_camera, android.R.drawable.ic_menu_gallery)),
    Product(2, "Auriculares", "$49.990", "Auriculares con cancelación de ruido.", listOf(android.R.drawable.ic_menu_slideshow)),
    Product(3, "Zapatillas", "$79.990", "Zapatillas deportivas para uso diario.", listOf(android.R.drawable.ic_menu_compass, android.R.drawable.ic_menu_camera)),
    Product(4, "Mochila", "$39.990", "Mochila resistente y ligera.", listOf(android.R.drawable.ic_menu_agenda)),
    Product(5, "Reloj", "$129.990", "Reloj con funciones inteligentes.", listOf(android.R.drawable.ic_menu_recent_history)),
    Product(6, "Gafas de Sol", "$29.990", "Gafas con protección UV.", listOf(android.R.drawable.ic_menu_gallery)),
    Product(7, "Batería Externa", "$24.990", "Carga rápida para tu móvil.", listOf(android.R.drawable.ic_menu_call)),
    Product(8, "Botella", "$14.990", "Botella térmica de acero.", listOf(android.R.drawable.ic_menu_compass))
)

@Composable
fun ComprarScreen(cartViewModel: CartViewModel = viewModel()) {
    val context = LocalContext.current

    // Mostrar un grid de 2 columnas; con tarjetas de ~180dp de alto se ven al menos 4 productos (2x2) en pantalla típica
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        content = {
            gridItems(sampleProducts) { product ->
                ProductCard(product = product, onAddToCart = { p ->
                    cartViewModel.add(p)
                    Toast.makeText(context, "Agregado al carrito: ${p.name}", Toast.LENGTH_SHORT).show()
                })
            }
        }
    )
}

@Composable
fun CarritoScreen(cartViewModel: CartViewModel = viewModel()) {
    val context = LocalContext.current

    if (cartViewModel.items.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Tu carrito está vacío")
        }
        return
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cartViewModel.items) { product ->
                // mostrar nombre, precio y botón eliminar
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Text(text = product.name)
                    Text(text = product.price)
                    Spacer(modifier = Modifier.height(6.dp))
                    Button(onClick = {
                        cartViewModel.remove(product)
                        Toast.makeText(context, "Eliminado: ${product.name}", Toast.LENGTH_SHORT).show()
                    }) {
                        Text("Eliminar")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            cartViewModel.clear()
            Toast.makeText(context, "Carrito vaciado", Toast.LENGTH_SHORT).show()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Vaciar carrito")
        }
    }
}

@Composable
fun YoScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Pantalla Yo")
    }
}