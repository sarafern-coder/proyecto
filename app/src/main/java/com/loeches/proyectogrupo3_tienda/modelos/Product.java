package com.loeches.proyectogrupo3_tienda.modelos;

import java.io.Serializable;
import java.util.Locale;

public class Product implements Serializable {
    private String nombre;
    private double precio;
    private int imageResId;
    private String definicion;
    private int cantidad;

    public Product(String nombre, double precio, int imageResId, String definicion) {
        this.nombre = nombre;
        this.precio = precio;
        this.imageResId = imageResId;
        this.definicion = definicion;
        this.cantidad = 0;
    }
    public Product(String nombre, double precio, int imageResId) {
        this.nombre = nombre;
        this.precio = precio;
        this.imageResId = imageResId;
        this.cantidad = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDefinicion() {
        return definicion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void incrementarCantidad() {
        this.cantidad++;
    }

    public String getPrecioFormateado() {
        return String.format(Locale.getDefault(), "%.2f€", precio);
    }
}