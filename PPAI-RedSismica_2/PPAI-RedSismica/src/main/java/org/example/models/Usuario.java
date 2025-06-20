package org.example.models;

public class Usuario {
    private String usuario;
    private String password;
    private Empleado empleado;

    public Usuario(String usuario, String password, Empleado empleado) {
        this.usuario = usuario;
        this.password = password;
        this.empleado = empleado;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Empleado obtenerEmpleado() {
        return this.empleado;
    }
}

