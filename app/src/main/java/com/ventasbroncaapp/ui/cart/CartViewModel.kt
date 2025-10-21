package com.ventasbroncaapp.ui.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.ventasbroncaapp.ui.screens.Product

class CartViewModel : ViewModel() {
    private val _items = mutableStateListOf<Product>()
    val items: SnapshotStateList<Product> = _items

    fun add(product: Product) {
        _items.add(product)
    }

    fun remove(product: Product) {
        _items.remove(product)
    }

    fun clear() {
        _items.clear()
    }
}

