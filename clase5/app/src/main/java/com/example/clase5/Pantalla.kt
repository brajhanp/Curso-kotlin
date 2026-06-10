package com.example.clase5

import android.util.Log

/**
 * Clase que representa una Pantalla, hereda de Componente.
 */
class Pantalla(
    val id: Int,
    val tamanoPulgadas: Double,
    marca: String,
    precioCompra: Double,
    precioVenta: Double,
) : Componente(marca, precioCompra, precioVenta) {

    /**
     * Sobrescribe mostrarInformacion para mostrar detalles específicos de la Pantalla.
     */
    override fun mostrarInformacion() {
        Log.d(TAG, "Pantalla [ID: $id] - Marca: $marca, Pulgadas: $tamanoPulgadas, Precio Compra: $precioCompra, Precio Venta: $precioVenta")
    }
}
