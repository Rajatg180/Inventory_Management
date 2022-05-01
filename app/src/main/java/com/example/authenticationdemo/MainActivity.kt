package com.example.authenticationdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textview1=findViewById<TextView>(R.id.tv_user_id)
        val textview2=findViewById<TextView>(R.id.tv_email_id)
        val logout=findViewById<Button>(R.id.btn_logout)
        val userId=intent.getStringExtra("user_id")
        val emailId=intent.getStringExtra("email_id")
        textview1.text="User ID :: $userId"
        textview2.text="Email ID :: $emailId"
        logout.setOnClickListener {
            //logout from app
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity,LoginActivity::class.java))

            finish()
        }

    }
}