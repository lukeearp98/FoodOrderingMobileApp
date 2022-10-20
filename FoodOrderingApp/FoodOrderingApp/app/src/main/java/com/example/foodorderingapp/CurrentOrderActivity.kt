package com.example.foodorderingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*

class CurrentOrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_order)
        Paper.init(this);
        var menuList = OrderHandler.getCart()
        var clearOrderBtn: Button = findViewById(R.id.clearOrderBtn)
        getSupportActionBar()?.setTitle("Current Order");

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = OrderItemAdapter(this, menuList)

        clearOrderBtn.setOnClickListener{
            OrderHandler.clearCart()
            menuList = OrderHandler.getCart()
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = OrderItemAdapter(this, menuList)
        }
    }
}
