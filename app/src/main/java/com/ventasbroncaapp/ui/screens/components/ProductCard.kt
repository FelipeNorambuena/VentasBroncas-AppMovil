package com.ventasbroncaapp.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.ventasbroncaapp.ui.screens.Product

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier, onAddToCart: (Product) -> Unit = {}) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(170.dp), // ligeramente mayor para dar espacio al botón
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            // Images as a horizontal scroller inside the card, con botón superpuesto en top-end
            Box(modifier = Modifier.fillMaxWidth()) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ) {
                    items(product.imageRes) { resId ->
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = "product image",
                            modifier = Modifier
                                .size(80.dp)
                                .background(Color.LightGray),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                // botón superpuesto pequeño en la esquina superior derecha
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .size(36.dp)
                        .background(Color(0xFF5C3E9B), shape = CircleShape),
                ) {
                    IconButton(
                        onClick = { onAddToCart(product) },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(36.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_input_add),
                            contentDescription = "Agregar",
                            tint = Color.White
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = product.price,
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF2E7D32),
                modifier = Modifier.padding(top = 2.dp)
            )

            Text(
                text = product.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Botón para agregar al carrito: color explícito y shape para visibilidad
            Button(
                onClick = { onAddToCart(product) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5C3E9B), // morado oscuro contrastante
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Agregar")
            }
        }
    }
}
