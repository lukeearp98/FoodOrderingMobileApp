package com.example.foodorderingapp

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

data class MenuItem(
   val name: String = "",  val category: String = "", val description: String = "",
    val price: String = "") : Serializable {

    companion object {
        fun getMenuItems(filename: String, context: Context): ArrayList<MenuItem> {
            //create ArrayList of Book objects
            val menuItemList = ArrayList<MenuItem>()

            try {
                //read json file
                val inputStream = context.assets.open(filename)
                val buffer = ByteArray(inputStream.available())
                inputStream.read(buffer)
                inputStream.close()

                //convert input to JSON
                val json = JSONObject(String(buffer, Charsets.UTF_8))
                val menuItems = json.getJSONArray("menuItems")

                //extract strings from the JSON objects
                //create new Book objects and add them to the List
                for (i in 0..(menuItems.length() - 1)) {
                    menuItemList.add(MenuItem(
                        menuItems.getJSONObject(i).getString("Name"),
                        menuItems.getJSONObject(i).getString("Category"),
                        menuItems.getJSONObject(i).getString("Description"),
                        menuItems.getJSONObject(i).getString("Price")))
                }

            } catch (e: JSONException) {
                e.printStackTrace()
            }

            //return the List of Book objects
            return menuItemList
        }
    }
}

