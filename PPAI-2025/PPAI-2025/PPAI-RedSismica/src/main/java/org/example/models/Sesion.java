package org.example.models;

public class Sesion {
    private String usuario;

    public Sesion(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void obtenerUsuario() {
    }
}
