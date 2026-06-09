package com.example.clase3

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

const val TAG = "consola"

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

        // Referencias a los componentes de la interfaz
        val btnIngresar = findViewById<Button>(R.id.button)
        val etUsername = findViewById<TextInputEditText>(R.id.et_username)
        val etPassword = findViewById<TextInputEditText>(R.id.et_password)

        // Configuración del evento click
        btnIngresar.setOnClickListener {
            // Extraer datos y convertir a String
            val email = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Mostrar un Toast con la información
            Toast.makeText(this, "$email $password", Toast.LENGTH_SHORT).show()
        }

        /* 
           --- NOTAS DE LA CLASE: SINTAXIS DE KOTLIN ---
           
           // Variables
           val edad = 23 // Inmutable (val)
           var correo = "juan@gmail.com" // Mutable (var)
           correo = "rosa@gmail.com"
           
           // Tipos de datos e inferencia
           // String, Int, Double, Boolean, Char, Any
           
           // Interpolación
           Log.e(TAG, "Hola $correo")
           Log.e(TAG, "Mayúsculas: ${correo.uppercase()}")

           // For y rangos
           for (i in 0..5) { Log.e(TAG, "i: $i") }
           
           // Listas
           val edades = listOf(12, 23, 32)
           val indices = mutableListOf<Int>()
           indices.add(1)

           // When (Switch)
           val letra = 'A'
           when (letra) {
               'A' -> Log.e(TAG, "Es la A")
               else -> Log.e(TAG, "No conocida")
           }
        */
    }

    /*
    // Funciones
    fun sumar(a: Int, b: Int): Int = a + b

    fun imprimir(msg: String? = "-") {
        // Operador Elvis para nulos ?:
        Log.e(TAG, "Msg: ${msg ?: "Nulo"}")
    }

    // Lambdas / Callbacks
    fun customOP(a: Int, b: Int, op: (Int, Int) -> Int) {
        val result = op(a, b)
        Log.e(TAG, "Resultado: $result")
    }
    */
}
