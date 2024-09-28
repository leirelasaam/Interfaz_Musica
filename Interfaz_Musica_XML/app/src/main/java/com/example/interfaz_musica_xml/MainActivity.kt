package com.example.interfaz_musica_xml

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Actividad principal de la aplicación de música.
 *
 */
class MainActivity : AppCompatActivity() {
    private var isPlaying = false;

    /**
     * Método llamado cuando la actividad se crea por primera vez.
     *
     * @param savedInstanceState Bundle que contiene el estado guardado de la actividad, si está disponible.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Verificar si hay un estado guardado y restaurar el estado
        if (savedInstanceState != null) {
            isPlaying = savedInstanceState.getBoolean("isPlaying", false)
        }

        val btn: ImageButton = findViewById(R.id.iconoPlayPause)
        actualizarImg(btn)

        // Añadir manejo del estado de reproducción del botón de reproducción/pausa
        btn.setOnClickListener {
            // Cambia el valor de la variable isPlaying
            isPlaying = !isPlaying
            // Actualiza la imagen del botón
            actualizarImg(btn)
        }
    }

    /**
     * Actualiza la imagen del botón dependiendo del valor de isPlaying.
     *
     * @param btn Botón en el cual se realiza el cambio de la imagen.
     */
    private fun actualizarImg(btn: ImageButton) {
        if (isPlaying) {
            btn.setImageResource(R.drawable.pausa)
        } else {
            btn.setImageResource(R.drawable.play)
        }
    }

    /**
     * Guarda el estado de la instancia de la actividad.
     *
     * @param outState El `Bundle` en el que se deben guardar los estados de la actividad.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isPlaying", isPlaying)
    }
}