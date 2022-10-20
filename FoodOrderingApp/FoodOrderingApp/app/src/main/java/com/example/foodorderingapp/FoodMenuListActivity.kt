package com.example.foodorderingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderingapp.R.id
import kotlinx.android.synthetic.main.activity_main.*


class FoodMenuListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuList = MenuItem.getMenuItems("menuItems.json", this)
        getSupportActionBar()?.setTitle("Menu");

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MenuItemAdapter(this, menuList)
    }
}
