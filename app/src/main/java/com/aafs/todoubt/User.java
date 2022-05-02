package com.aafs.todoubt;

public class User {
    private String userId,nombre, contrasenia,email;

    public User(){}

    public User(String userId, String nombre, String contrasenia, String email) {
        this.userId = userId;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
