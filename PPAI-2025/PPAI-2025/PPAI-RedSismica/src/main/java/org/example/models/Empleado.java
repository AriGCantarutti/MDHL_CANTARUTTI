package org.example.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Empleado {
    private String nombre;
    private String email;
    private List<Rol> roles;

    public Empleado(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.roles = new ArrayList();
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String obtenerEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean esResponsable() {
        Iterator var2 = this.roles.iterator();

        while(var2.hasNext()) {
            Rol rol = (Rol)var2.next();
            if (rol.esResponsable()) {
                return true;
            }
        }

        return false;
    }

    public void agregarRol(Rol rol) {
        if (!this.roles.contains(rol)) {
            this.roles.add(rol);
        }

    }

    public List<Rol> getRoles() {
        return this.roles;
    }
}
