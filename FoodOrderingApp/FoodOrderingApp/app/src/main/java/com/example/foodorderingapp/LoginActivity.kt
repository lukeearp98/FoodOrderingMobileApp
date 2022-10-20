package com.example.foodorderingapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var loggedInUser = mAuth.currentUser
        getSupportActionBar()?.hide();

        var regBtn: Button = findViewById(R.id.btnRegister)
        var loginBtn: Button = findViewById(R.id.btnLogin)

        regBtn.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            register()
        }
        loginBtn.setOnClickListener {
            // clearing user_name and password edit text views on reset button click
            login()
        }
    }
    private fun register()
    {
            var emailField: EditText = findViewById(R.id.emailField)
            var passwordField: EditText = findViewById(R.id.passwordField)

            var email = emailField.text.toString()
            var password = passwordField.text.toString()

            if (!email.isEmpty() && !password.isEmpty()) {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var homePageIntent = Intent(this@LoginActivity, HomePageActivity::class.java)
                        homePageIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                        finish();
                        this@LoginActivity.startActivity(homePageIntent)
                        Toast.makeText(this, "Account has been registered successfully.", Toast.LENGTH_LONG).show()
                    }else {
                        Toast.makeText(this, "Error registering, try again later.", Toast.LENGTH_LONG).show()
                    }
                })
            }
            else
            {
                Toast.makeText(this,"Please enter a value in both username and password.", Toast.LENGTH_LONG).show()
            }
    }
    private fun login () {
        var emailField: EditText = findViewById(R.id.emailField)
        var passwordField: EditText = findViewById(R.id.passwordField)

        var email = emailField.text.toString()
        var password = passwordField.text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {
            this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener ( this, OnCompleteListener<AuthResult> { task ->
                if (task.isSuccessful) {
                    var homePageIntent = Intent(this@LoginActivity, HomePageActivity::class.java)
                    homePageIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    finish()
                    this@LoginActivity.startActivity(homePageIntent)
                    Toast.makeText(this, "Successfully logged in.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error Logging in.", Toast.LENGTH_SHORT).show()
                }
            })

        }else {
            Toast.makeText(this,"Please enter a value in both username and password.", Toast.LENGTH_LONG).show()
        }
    }
}