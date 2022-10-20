package com.example.foodorderingapp

import android.content.Context
import android.util.Log
import android.widget.Toast
import io.paperdb.Paper
import kotlin.math.log

class OrderHandler {

    companion object {
        fun addItem(cartItem: OrderItem) {
            val cart = OrderHandler.getCart()
            println(cart)
            cart.add(cartItem)
            OrderHandler.saveCart(cart)
        }

        fun saveCart(cart: MutableList<OrderItem>) {
            Paper.book().write("cart", cart)
        }

        fun getCart(): MutableList<OrderItem> {
            return Paper.book().read("cart", mutableListOf())
        }
        fun clearCart(){
            val cart = OrderHandler.getCart()
            cart.clear();
            OrderHandler.saveCart(cart)
        }
    }

}