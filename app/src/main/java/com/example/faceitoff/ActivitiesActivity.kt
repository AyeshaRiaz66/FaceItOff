package com.example.faceitoff

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivitiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        val mood = intent.getStringExtra("MOOD") ?: "neutral"
        val tvActivities = findViewById<TextView>(R.id.tvActivitiesList)

        val activitiesMap = mapOf(
            "happy" to "🏃 Workout\n🎨 Painting\n🎮 Gaming",
            "sad" to "🚶 Walk\n📓 Journaling\n😴 Rest",
            "neutral" to "🧹 Organize\n📚 Read\n🧘 Stretch",
            "angry" to "🏃 Go for a run\n🥊 Punch a pillow\n💪 Do a workout",
            "relaxed" to "📚 Read a book\n🧘 Meditate\n🎨 Enjoy a hobby",
            "stressed" to "🌬️ Deep breathing\n🧍 Stretch or do yoga\n🚶 Take a short walk",
        )

        tvActivities.text = activitiesMap[mood]
    }
}
