package org.example.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Empleado {
    private String nombre;
    private String apellido;
    private int telefono;
    private String email;
    private List<Rol> rol;

    public Empleado(String nombre, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.rol = new ArrayList();
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return this.telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String obtenerEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean esResponsable() {
        Iterator var2 = this.rol.iterator();

        while(var2.hasNext()) {
            Rol rol = (Rol)var2.next();
            if (rol.esResponsable()) {
                return true;
            }
        }
        return false;
    }

    public void agregarRol(Rol rol) {
        if (!this.rol.contains(rol)) {
            this.rol.add(rol);
        }

    }

    public List<Rol> getRoles() {
        return this.rol;
    }
}
