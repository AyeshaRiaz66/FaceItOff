package com.example.faceitoff

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AppsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apps)

        val mood = intent.getStringExtra("MOOD") ?: "neutral"
        val tvApps = findViewById<TextView>(R.id.tvAppsList)

        val appsMap = mapOf(
            "happy" to "🎮 Fun Games\n📱 Social Apps\n🎨 Creative Apps",
            "sad" to "🧘 Meditation App\n📖 Inspirational Quotes\n😂 Funny Video Apps",
            "neutral" to "📚 News Apps\n🎧 Music Apps\n🎲 Puzzle Games",
            "angry" to "🏋️ Workout Apps\n🎵 Music Apps\n🎮 Action Games",
            "relaxed" to "📚 Reading Apps\n✈️ Travel Apps\n🌿 Mindfulness Apps",
            "stressed" to "⏰ Time Management Apps\n🧘 Meditation Apps\n🧩 Brain Games"
        )

        tvApps.text = appsMap[mood]
    }
}
