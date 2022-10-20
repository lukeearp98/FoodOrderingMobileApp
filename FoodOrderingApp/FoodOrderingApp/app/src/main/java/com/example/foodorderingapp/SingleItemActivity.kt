package com.example.foodorderingapp

import android.Manifest
import android.R.layout
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_singleitem.*


class SingleItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singleitem)

        txtItemName.text = "Item Name: " + intent.getStringExtra("ITEM_NAME")
        txtItemCategory.text = "Item Category: " + intent.getStringExtra("ITEM_CATEGORY")
        txtItemDescription.text = "Item Description: " + intent.getStringExtra("ITEM_DESCRIPTION")
        txtItemPrice.text = "Item Price: " + intent.getStringExtra("ITEM_PRICE")
        getSupportActionBar()?.setTitle("Menu Item");
        loadContacts()
        var addToOrderBtn: Button = findViewById(R.id.addToOrderBtn)
        addToOrderBtn.setOnClickListener{
            val menuItem = MenuItem(intent.getStringExtra("ITEM_NAME"),
                intent.getStringExtra("ITEM_CATEGORY"), intent.getStringExtra("ITEM_DESCRIPTION"), intent.getStringExtra("ITEM_PRICE"))
            val itemText = contactSpinner.getSelectedItem() as String
            val orderItem = OrderItem(menuItem, itemText)
            Paper.init(this);
            OrderHandler.addItem(orderItem);
            Toast.makeText(this, menuItem.name + " has been added to your order.", Toast.LENGTH_SHORT).show();
        }

    }
    fun loadContacts() {
        //check whether permission granted to access contacts
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
            PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "No permission to contacts", Toast.LENGTH_LONG).show()
        }
        else
        {
            var contacts = ArrayList<String>(getContacts())

            val s = contactSpinner as Spinner
            val adapter = ArrayAdapter(
                this,
                layout.simple_spinner_item, contacts
            )
            s.adapter = adapter
        }
    }
    private fun getContacts(): ArrayList<String> {
        //set up which columns are to be retrieved
        val returnColumns: Array<String> = arrayOf(
            ContactsContract.Data.DISPLAY_NAME_PRIMARY
        )

        //retrieve all contacts with name starting with A (case sensitive)
        val selection = "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} LIKE ?"
        val selectionArguments = arrayOf<String>("%")

        //sort by ascending name
        val sortOrder = "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} ASC"

        //get all contacts that match criteria
        val theContacts = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            returnColumns,
            selection,
            selectionArguments,
            sortOrder)
        return processResults(theContacts)
    }
    private fun processResults(theContacts: Cursor?): ArrayList<String> {
        //create string for details
        val contacts = ArrayList<String>()

        //check whether any contacts have been matched
        if (theContacts != null) {
            when (theContacts.count) {
                null -> {
                    //error
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
                0 -> {
                    //no matching results
                    Toast.makeText(this, "No matches", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    //go through each entry that matches selection criteria
                    while (theContacts.moveToNext()) {
                        //use index for each column of interest to get value in that column
                        val name = theContacts.getString(
                            theContacts.getColumnIndex(ContactsContract.Data.DISPLAY_NAME_PRIMARY)
                        )

                        //if contact has a phone number get details
                        //use CommonDataKinds using a second query
                            //create a string containing all data
                        Log.d("Name: ", name)
                            contacts.add(name);
                            }
                        }
                    }
                }

            //close cursor to prevent memory leaks
        theContacts?.close()
        return contacts
    }
    //return string
}
