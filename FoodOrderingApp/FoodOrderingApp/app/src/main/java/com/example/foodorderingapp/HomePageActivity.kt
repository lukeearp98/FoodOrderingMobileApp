package com.example.foodorderingapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_homepage.*


class HomePageActivity : AppCompatActivity()
{
    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        var showOnMapBtn: Button = findViewById(R.id.showOnMap)
        var logoutBtn: Button = findViewById(R.id.logoutBtn)
        var viewMenuBtn: Button = findViewById(R.id.viewMenuBtn)
        var viewOrderBtn: Button = findViewById(R.id.viewOrderBtn)
        var loggedInUser = mAuth.currentUser

        getSupportActionBar()?.setTitle("Home");

        if(loggedInUser == null)
        {
            var intent = Intent(this@HomePageActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            finish()
            this@HomePageActivity.startActivity(intent)
        }

        showOnMapBtn.setOnClickListener {
            showOnMap()
        }
        logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut();
            var intent = Intent(this@HomePageActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            finish()
            this@HomePageActivity.startActivity(intent)
        }
        viewMenuBtn.setOnClickListener{
            var intent = Intent(this@HomePageActivity, FoodMenuListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            this@HomePageActivity.startActivity(intent)
        }
        viewOrderBtn.setOnClickListener{
            var viewOrderIntent = Intent(this@HomePageActivity, CurrentOrderActivity::class.java)
            viewOrderIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            this@HomePageActivity.startActivity(viewOrderIntent)
        }
    }
    fun showOnMap(){
        val location: String = locationName.text.toString()
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("geo:0,0?q=" + location)
        }

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent)
        } else {
            Log.d("No Location Found ", "Can't handle maps")
        }
    }
}