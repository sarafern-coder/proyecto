package com.loeches.proyectogrupo3_tienda.modelos;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String telefono;
    private String nombreUsuario;
    private String contrasena;

    public Cliente(int idCliente, String nombre, String telefono, String nombreUsuario, String contrasena) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public Cliente(String nombre, String telefono, String nombreUsuario, String contrasena) {
        this(-1, nombre, telefono, nombreUsuario, contrasena);
    }

    // Getters y setters
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}

