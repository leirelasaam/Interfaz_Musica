package com.example.interfaz_musica_xml

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var isPlaying = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn: ImageButton = findViewById(R.id.iconoPlayPause)

        // Verificar si hay un estado guardado y restaurar el estado
        if (savedInstanceState != null) {
            isPlaying = savedInstanceState.getBoolean("isPlaying", false)
        }

        updateButtonImage(btn)

        btn.setOnClickListener {
            isPlaying = !isPlaying
            updateButtonImage(btn)
        }
    }

    private fun updateButtonImage(btn: ImageButton) {
        if (isPlaying) {
            btn.setImageResource(android.R.drawable.ic_media_pause)
        } else {
            btn.setImageResource(android.R.drawable.ic_media_play)
        }
    }

    // Guardar el estado del botón cuando la actividad es destruida
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isPlaying", isPlaying)
    }
}