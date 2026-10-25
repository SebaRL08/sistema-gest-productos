
package com.tienda.modelo;

import java.math.BigDecimal;

public class producto {

    private final String codigo;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int cantidad;

    public producto(String codigo, String nombre, String descripcion,
                    BigDecimal precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo
                + " | Nombre: " + nombre
                + " | Descripcion: " + descripcion
                + " | Precio: $" + precio
                + " | Cantidad: " + cantidad;
    }
}
