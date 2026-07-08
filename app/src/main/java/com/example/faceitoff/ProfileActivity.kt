package com.example.faceitoff

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val user = FirebaseAuth.getInstance().currentUser

        findViewById<TextView>(R.id.tvName).text = user?.displayName ?: "N/A"
        findViewById<TextView>(R.id.tvEmail).text = user?.email ?: "N/A"
    }
}
