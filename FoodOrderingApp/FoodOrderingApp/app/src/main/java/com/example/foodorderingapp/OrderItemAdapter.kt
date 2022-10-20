package com.example.foodorderingapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_list_menuitems.view.*
import kotlinx.android.synthetic.main.activity_list_menuitems.view.txtItemCategory
import kotlinx.android.synthetic.main.activity_list_menuitems.view.txtItemDescription
import kotlinx.android.synthetic.main.activity_list_menuitems.view.txtItemName
import kotlinx.android.synthetic.main.activity_list_menuitems.view.txtItemPrice
import kotlinx.android.synthetic.main.activity_list_orderitems.view.*

class OrderItemAdapter(private val context: CurrentOrderActivity, private val menuItems: MutableList<OrderItem>) :
    RecyclerView.Adapter<OrderItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_list_orderitems, parent, false))
    }
    override fun getItemCount(): Int {
        return menuItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.foodName.text = menuItems.get(position).menuItem.name
        holder.foodCategory.text = menuItems.get(position).menuItem.category
        holder.foodDescription.text = menuItems.get(position).menuItem.description
        holder.foodPrice.text = menuItems.get(position).menuItem.price
        holder.contact.text = menuItems.get(position).contact
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodName = view.txtItemName
        val foodCategory = view.txtItemCategory
        val foodDescription = view.txtItemDescription
        val foodPrice = view.txtItemPrice
        val contact = view.txtItemContact

    }
}