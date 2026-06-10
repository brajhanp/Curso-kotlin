package com.example.clase5

import android.util.Log

/**
 * Clase padre que representa un componente genérico.
 */
open class Componente(
    val marca: String,
    protected val precioCompra: Double,
    val precioVenta: Double,
) {
    companion object {
        const val TAG = "SimpleTakeNotes"
    }

    /**
     * Muestra la información básica del componente en el Logcat.
     */
    open fun mostrarInformacion() {
        Log.d(TAG, "Componente - Marca: $marca, Precio Compra: $precioCompra, Precio Venta: $precioVenta")
    }
}
