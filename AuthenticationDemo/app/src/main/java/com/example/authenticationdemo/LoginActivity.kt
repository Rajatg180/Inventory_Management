package com.example.authenticationdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val register=findViewById<TextView>(R.id.tv_register)
        register.setOnClickListener{
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
            finish()
        }
        val edittexte=findViewById<EditText>(R.id.et_login_email)
        val edittextp=findViewById<EditText>(R.id.et_login_password)
    }
}