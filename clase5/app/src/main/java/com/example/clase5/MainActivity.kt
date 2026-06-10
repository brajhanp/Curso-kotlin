package com.example.clase5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clase5.ui.theme.Clase5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // --- Implementación de POO con nomenclatura específica ---

        // Instancia de Pantalla
        val pantallaAsus = Pantalla(
            id = 1,
            tamanoPulgadas = 24.5,
            marca = "Asus",
            precioCompra = 120.0,
            precioVenta = 199.99,
        )

        // Instancia de Teclado con lista de accesorios
        val tecladoLogitech = Teclado(
            tipoIdioma = "Español",
            listaAccesorios = listOf("Extractor de teclas", "Cable USB-C"),
            marca = "Logitech",
            precioCompra = 40.0,
            precioVenta = 85.0,
        )

        // Se llama al método mostrarInformacion() en ambos objetos
        pantallaAsus.mostrarInformacion()
        tecladoLogitech.mostrarInformacion()

        // Atajo en Android Studio para formatear código: Control + Alt + L

        setContent {
            Clase5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Clase5Theme {
        Greeting("Android")
    }
}