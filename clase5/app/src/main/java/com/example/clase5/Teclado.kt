package com.example.clase5

import android.util.Log

/**
 * Clase que representa un Teclado, hereda de Componente.
 */
class Teclado(
    val tipoIdioma: String,
    val listaAccesorios: List<String>,
    marca: String,
    precioCompra: Double,
    precioVenta: Double,
) : Componente(marca, precioCompra, precioVenta) {

    /**
     * Sobrescribe mostrarInformacion para mostrar detalles específicos del Teclado.
     */
    override fun mostrarInformacion() {
        val accesorios = listaAccesorios.joinToString(", ")
        Log.d(TAG, "Teclado - Idioma: $tipoIdioma, Marca: $marca, Precio Venta: $precioVenta, Accesorios: $accesorios, (Costo interno: $precioCompra)")
    }
}
