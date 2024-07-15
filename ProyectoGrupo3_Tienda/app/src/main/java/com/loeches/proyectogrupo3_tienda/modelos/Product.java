package com.loeches.proyectogrupo3_tienda.modelos;

public class Product {
    private String nombre;
    private double precio;
    private int imagen; // Puedes usar int para el recurso de imagen o String para la URL de la imagen
    private String definicion;

    public Product(String nombre, double precio, int imagen, String definicion) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.definicion = definicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

    public String getPrecioFormateado(){ return String.format("%.2f€", precio);}
}
