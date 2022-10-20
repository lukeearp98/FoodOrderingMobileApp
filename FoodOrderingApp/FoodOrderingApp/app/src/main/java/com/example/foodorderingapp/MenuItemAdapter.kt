package com.example.foodorderingapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_list_menuitems.view.*

class MenuItemAdapter(private val context: FoodMenuListActivity, private val menuItems: ArrayList<MenuItem>) :
    RecyclerView.Adapter<MenuItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_list_menuitems, parent, false))
    }
    override fun getItemCount(): Int {
        return menuItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.foodName.text = menuItems.get(position).name
        holder.foodCategory.text = menuItems.get(position).category
        holder.foodDescription.text = menuItems.get(position).description
        holder.foodPrice.text = menuItems.get(position).price

        holder.itemView.setOnClickListener {
            val bookViewIntent: Intent =
                Intent(context, SingleItemActivity::class.java)
            bookViewIntent.putExtra("ITEM_NAME", menuItems.get(position).name)
            bookViewIntent.putExtra("ITEM_CATEGORY", menuItems.get(position).category)
            bookViewIntent.putExtra("ITEM_DESCRIPTION", menuItems.get(position).description)
            bookViewIntent.putExtra("ITEM_PRICE", menuItems.get(position).price)
            context.startActivity(bookViewIntent)
        }
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val foodName = view.txtItemName
        val foodCategory = view.txtItemCategory
        val foodDescription = view.txtItemDescription
        val foodPrice = view.txtItemPrice
    }
}