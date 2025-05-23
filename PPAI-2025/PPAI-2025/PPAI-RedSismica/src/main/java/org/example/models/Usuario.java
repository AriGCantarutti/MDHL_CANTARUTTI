package org.example.models;

public class Usuario {
    private String usuario;
    private int password;

    public Usuario(String usuario, int password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public int getPassword() {
        return this.password;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void obtenerEmpleado() {
    }
}

