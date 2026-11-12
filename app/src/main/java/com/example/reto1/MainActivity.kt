package com.example.reto1

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Función checkbox
        val cbAndroid: android.widget.CheckBox = findViewById(R.id.cbAndroid)
        val cbReact: android.widget.CheckBox = findViewById(R.id.cbReact)
        val cbFlutter: android.widget.CheckBox = findViewById(R.id.cbFlutter)
        val tvResultado: android.widget.TextView = findViewById(R.id.tvResultado)

        val checkListener = android.widget.CompoundButton.OnCheckedChangeListener { _, _ ->
            val seleccion = mutableListOf<String>()
            if (cbAndroid.isChecked) seleccion.add("Android")
            if (cbReact.isChecked) seleccion.add("React")
            if (cbFlutter.isChecked) seleccion.add("Flutter")

            tvResultado.text = if (seleccion.isNotEmpty()) {
                "Tecnologías: ${seleccion.joinToString(", ")}"
            } else {
                "Ninguna tecnología seleccionada"
            }
        }

        cbAndroid.setOnCheckedChangeListener(checkListener)
        cbReact.setOnCheckedChangeListener(checkListener)
        cbFlutter.setOnCheckedChangeListener(checkListener)

        //Función switch
        val swNotificaciones: android.widget.Switch = findViewById(R.id.swNotificaciones)

        swNotificaciones.setOnCheckedChangeListener { _, isChecked ->
            val mensaje = if (isChecked) "Notificaciones ACTIVADAS" else "Notificaciones DESACTIVADAS"
            android.widget.Toast.makeText(this, mensaje, android.widget.Toast.LENGTH_SHORT).show()
        }

        //Botón de Guardar + Nombre
        val btnGuardar: android.widget.Button = findViewById(R.id.btnGuardar1)
        val etNombre: android.widget.EditText = findViewById(R.id.etNombre)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            if (nombre.isNotEmpty()) {
                tvResultado.text = "¡Hola $nombre! Datos guardados."
                android.widget.Toast.makeText(this, "Guardado con éxito",
                    android.widget.Toast.LENGTH_SHORT).show()
            } else {
                etNombre.error = "Introduce tu nombre"
            }
        }

        //Función para cargar la página web
        val myWebView: WebView = findViewById(R.id.wvWeb)
        myWebView.webViewClient = WebViewClient()
        myWebView.settings.javaScriptEnabled = true
        myWebView.loadUrl("https://developer.android.com")
    }
}


