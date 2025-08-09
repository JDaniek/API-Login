package org.example.models;

public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String contrasena;

    // 🔹 Constructor vacío (obligatorio para Jackson o frameworks que crean objetos)
    public Usuario() {}

    // 🔹 Constructor que necesitas para el registro (nombre, correo, contraseña)
    public Usuario(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    // 🔹 Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
