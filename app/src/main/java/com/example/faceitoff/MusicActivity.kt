package com.example.faceitoff

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MusicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        val mood = intent.getStringExtra("MOOD") ?: "neutral"
        val tvMusic = findViewById<TextView>(R.id.tvMusicList)

        val musicSuggestions = mapOf(
            "happy" to "🎶 Pop Hits\n🎶 Dance Beats\n🎶 Party Playlist",
            "sad" to "🎧 Calm Piano\n🎧 Lo-Fi Beats\n🎧 Nature Sounds",
            "neutral" to "🎼 Chillhop\n🎼 Acoustic\n🎼 Soft Jazz",
            "angry" to "🎸 Rock Anthems\n🎸 Heavy Metal\n🎸 Workout Beats",
            "relaxed" to "🎷 Smooth Jazz\n🎷 Acoustic Chill\n🎷 Lofi Vibes",
            "stressed" to "🧘 Meditation Music\n🧘 Nature Sounds\n🧘 Slow Instrumental"
        )

        tvMusic.text = musicSuggestions[mood]
    }
}
