package com.example.faceitoff

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.Toast

class SuggestionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestions)

        val mood = intent.getStringExtra("MOOD")

        val btnApps = findViewById<ImageButton>(R.id.btn_apps)
        val btnMusic = findViewById<ImageButton>(R.id.btn_music)
        val btnActivities = findViewById<ImageButton>(R.id.btn_activities)

        btnApps.setOnClickListener {
            openApps(mood)
        }

        btnMusic.setOnClickListener {
            openMusic(mood)
        }

        btnActivities.setOnClickListener {
            openActivities(mood)
        }


    }

    private fun openApps(mood: String?) {
        val intent = Intent(this, AppsActivity::class.java)
        intent.putExtra("MOOD", mood)
        startActivity(intent)
    }

    private fun openMusic(mood: String?) {
        val intent = Intent(this, MusicActivity::class.java)
        intent.putExtra("MOOD", mood)
        startActivity(intent)
    }

    private fun openActivities(mood: String?) {
        val intent = Intent(this, ActivitiesActivity::class.java)
        intent.putExtra("MOOD", mood)
        startActivity(intent)
    }
}
